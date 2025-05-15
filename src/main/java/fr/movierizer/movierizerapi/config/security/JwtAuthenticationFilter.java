package fr.movierizer.movierizerapi.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import fr.movierizer.movierizerapi.services.JwtService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver; // Use to handle exception during the filter

    private final JwtService jwtService; // Use to manipulate the JWT token
    private final UserDetailsService userDetailsService; // Use to load user information

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /**
     * Filters incoming HTTP requests to authenticate users based on JWT tokens.
     * If the request contains a valid "Authorization" header with a Bearer token,
     * it extracts the token, validates it, and sets the corresponding user
     * authentication in the security context. If the token is invalid or missing,
     * the request is forwarded without authentication. Exceptions during the
     * process are resolved using the defined exception handler.
     *
     * @param request the HTTP request to filter
     * @param response the HTTP response to filter
     * @param filterChain the filter chain to continue the processing
     * @throws ServletException if an error occurs during filtering
     * @throws IOException if an input or output error is detected
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) { // this condition is to let the OPTION request to passed the filter chain
            log.info("OPTIONS REQUEST");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("NO AUTH HEADER OR NO BEARER TOKEN");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String jwt = authHeader.substring(7); // Extract the authorization header 
            final String userUsername = jwtService.extractUsername(jwt); // Extract the useername of the token 
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
            if (userUsername != null && authentication == null) { // Check if the user is already authentificate if he's not we continue in the if 
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userUsername);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}