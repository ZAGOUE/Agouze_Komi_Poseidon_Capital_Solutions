package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementation of Spring Security's UserDetails interface using the application's User entity.
 * This class is used by Spring Security to perform authentication and authorization.
 */
public class MyUserDetails implements UserDetails {

    private final User user;


    /**
     * Constructs a new MyUserDetails object using a User entity.
     *
     * @param user the User entity representing the authenticated user
     */
    public MyUserDetails(User user) {
        this.user = user;
    }


    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection containing the user's role as a SimpleGrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }


    /**
     * Returns the username used to authenticate the user.
     *
     * @return the user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true (account is non-expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    /**
     * Indicates whether the user's account is locked.
     *
     * @return true (account is non-locked)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return true (credentials are non-expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true (user is enabled)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
