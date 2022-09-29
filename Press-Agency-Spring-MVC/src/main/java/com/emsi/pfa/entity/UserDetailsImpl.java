package com.emsi.pfa.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -2613661939383105307L;
    private String email;
    private String password;
    private String role;

    public UserDetailsImpl(Journaliste journaliste) {
        super();
        this.email=journaliste.getEmail();
        this.password=journaliste.getPassword();
        this.role="ROLE_JOURNALISTE";
    }
    public UserDetailsImpl(RedacteurEnChef redacteurEnChef) {
        super();
        this.email=redacteurEnChef.getEmail();
        this.password=redacteurEnChef.getPassword();
        this.role="ROLE_CHEF";
    }
    public UserDetailsImpl(Lecteur lecteur) {
        super();
        this.email=lecteur.getEmail();
        this.password=lecteur.getPassword();
        this.role="ROLE_LECTEUR";
    }
    public UserDetailsImpl(Administrateur administrateur) {
        super();
        this.email=administrateur.getEmail();
        this.password=administrateur.getPassword();
        this.role="ROLE_ADMINISTRATEUR";
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
