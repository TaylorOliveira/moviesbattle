package br.com.letscode.moviesbattle.api.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"credentials", "authorities", "authenticated", "details"})
public class LoggedInUser extends UsernamePasswordAuthenticationToken implements UserDetails, Serializable {

    private Long id;

    private String username;

    @JsonCreator
    public LoggedInUser(@JsonProperty("username") final String username) {
        super(username, null, Collections.EMPTY_LIST);
        this.username  = username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
