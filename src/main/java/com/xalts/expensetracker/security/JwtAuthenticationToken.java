package com.xalts.expensetracker.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Custom authentication token for JWT-based authentication.
 * This class extends AbstractAuthenticationToken to handle JWT authentication.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;
    private final Object principal;
    private Object credentials;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public JwtAuthenticationToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.principal = null;
        this.credentials = null;
        setAuthenticated(false);
    }

    /**
     * Creates a token with the supplied array of authorities and principal.
     *
     * @param principal the principal (typically a UserDetails)
     * @param credentials the credentials that prove the identity of the Principal
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = null;
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getToken() {
        return token;
    }
} 