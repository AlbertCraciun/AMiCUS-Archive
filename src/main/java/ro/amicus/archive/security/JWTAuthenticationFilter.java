package ro.amicus.archive.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService userService;
    private final JwtConfiguration jwtConfiguration;

    public JWTAuthenticationFilter(
            ObjectMapper objectMapper,
            CustomUserDetailsService userService, JwtConfiguration jwtConfiguration) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.jwtConfiguration = jwtConfiguration;
        setFilterProcessesUrl(jwtConfiguration.getLoginUrl());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        UserCredentialsRequestBody userCredentialsRequestBody;
        try {
            userCredentialsRequestBody = objectMapper.readValue(req.getInputStream(), UserCredentialsRequestBody.class);
            validateRequestBody(userCredentialsRequestBody);
        } catch (IOException e) {
            throw new RuntimeException("Invalid request body");
        }

        return authenticateUserByEmail(userCredentialsRequestBody);
    }

    private void validateRequestBody(UserCredentialsRequestBody externalActor) {
        // TODO: user request body validation
    }

    private UsernamePasswordAuthenticationToken authenticateUserByEmail(UserCredentialsRequestBody userCredentialsRequestBody) {
        if (!this.userService.validateUserCredentials(userCredentialsRequestBody)) {
            throw new RuntimeException("Invalid credentials");
        }
        UserDetails user = this.userService.loadByUsernameOrPhoneNo(userCredentialsRequestBody);
        if (user.isEnabled() == Boolean.FALSE) {
            throw new RuntimeException("User not found");
        }
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        TokenResponse tokenResponse = this.userService.generateJwtToken((String) auth.getPrincipal());

        res.setContentType("application/json");
        try {
            res.getWriter().write(objectMapper.writeValueAsString(tokenResponse));
            res.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
