package com.tpo.fizio.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Tadej Delopst
 */
public class Authentication extends AbstractAuthenticationToken {

    private final User principal;
    private final Object detail;
    private final Set<GrantedAuthority> authorities;

    public Authentication(
            User principal,
            Object detail,
            Set<SimpleGrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.detail = detail;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return principal.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getDetails() {
        return detail;
    }
}

