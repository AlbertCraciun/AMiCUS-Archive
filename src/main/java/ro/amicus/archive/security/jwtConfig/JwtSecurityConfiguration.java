//package ro.amicus.archive.security.jwtConfig;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//import java.util.Optional;
//
//@EnableWebSecurity
//@Slf4j
//public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final JwtConfiguration jwtConfiguration;
//    private final ObjectMapper objectMapper;
//    private final CustomUserDetailsService userService;
//
//    @Value("${ro.activ.tours.security.cors.allowedOrigins}")
//    private List<String> corsAllowedOrigins;
//
//    public JwtSecurityConfiguration(JwtConfiguration jwtConfiguration, ObjectMapper objectMapper, CustomUserDetailsService userService) {
//        this.jwtConfiguration = jwtConfiguration;
//        this.objectMapper = objectMapper;
//        this.userService = userService;
//    }
//
//    public static String getUserFromContext() {
//        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//    }
//
//    public static String getActiveRoleFromContext() {
//        Optional<? extends GrantedAuthority> existingAuthority =
//                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst();
//        return existingAuthority.isPresent() ? existingAuthority.get().getAuthority() : "";
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//        tokenRepository.setCookiePath("/");
//
//        http.authorizeRequests()
//                .antMatchers(jwtConfiguration.getRegistrationUrl(),
//                        jwtConfiguration.getLoginUrl(),
//                        "/sitemap**",
//                        "/public/**",
//                        "/actuator/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .cors()
//                .configurationSource(request -> {
//                    var cors = new CorsConfiguration();
//                    cors.setAllowedOrigins(this.corsAllowedOrigins);
//                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                    cors.setAllowedHeaders(List.of("*"));
//                    cors.setAllowCredentials(true);
//                    return cors;
//                })
//                .and()
//                .csrf().ignoringAntMatchers(jwtConfiguration.getRegistrationUrl(),
//                        jwtConfiguration.getLoginUrl(),
//                        "/sitemap**",
//                        "/public/**",
//                        "/actuator/**")
//                .and()
//                .csrf().csrfTokenRepository(tokenRepository)
//                .and()
//                .addFilter(new JWTAuthenticationFilter(objectMapper, userService, jwtConfiguration))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtConfiguration, userService))
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//}