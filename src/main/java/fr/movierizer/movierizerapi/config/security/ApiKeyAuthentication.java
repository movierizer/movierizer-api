package fr.movierizer.movierizerapi.config.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {
    private final String apiKey;

    public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    /**
     * The credentials of the principal. In this case, the API key itself is
     * used as the principal, so the credentials are null.
     *
     * @return null
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Returns the API key used for authentication.
     *
     * @return the API key
     */
    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}