package ro.amicus.archive.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public static final String ACTIVE_ROLE = "activeRole";
    private final JwtConfiguration jwtConfiguration;
    private final CustomUserDetailsService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JwtConfiguration jwtConfiguration, CustomUserDetailsService userService) {
        super(authManager);
        this.jwtConfiguration = jwtConfiguration;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) {
        String header = req.getHeader(jwtConfiguration.getJwtHeaderName());

        if (header == null || !header.startsWith(jwtConfiguration.getJwtPrefix())) {
            try {
                chain.doFilter(req, res);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtConfiguration.getJwtHeaderName());

        if (token != null) {
            // parse the token.
            DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC512(jwtConfiguration.getJwtSecret().getBytes()))
                    .build()
                    .verify(token.replace(jwtConfiguration.getJwtPrefix(), ""));

            if (decodedJwt != null) {
                // This impacts performance as get user requests are made at each authorization call
                UserDetails userEntity = userService.loadUserByUsername(decodedJwt.getSubject());
                if (userEntity.isEnabled() == Boolean.FALSE) {
                    throw new RuntimeException("User not found");
                }
                String activeRole = decodedJwt.getClaim(ACTIVE_ROLE).asString();
                return new UsernamePasswordAuthenticationToken(decodedJwt.getSubject(), null,
                        List.of(new SimpleGrantedAuthority(activeRole)));
            }

            return null;
        }

        return null;
    }
}
