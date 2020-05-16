package org.mycode.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "security_users")
@NamedEntityGraph(name = "User.detail", attributeNodes = @NamedAttributeNode("authorities"))
public class UserSecurity {
    @Id
    private String username;
    private String password;
    private int enabled;
    @ManyToMany
    @JoinTable(
            name = "security_user_authority",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority")
    )
    private Set<AuthoritySecurity> authorities;
    @Column(name = "identity_info")
    private String identityInfo;

    public UserSecurity() {
    }

    public UserSecurity(String username, String password, int enabled, Set<AuthoritySecurity> authorities, String identityInfo) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.identityInfo = identityInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<AuthoritySecurity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthoritySecurity> authorities) {
        this.authorities = authorities;
    }

    public String getIdentityInfo() {
        return identityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
    }

    @Override
    public String toString() {
        return "UserSecurity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", identityInfo='" + identityInfo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSecurity that = (UserSecurity) o;
        return enabled == that.enabled &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }
}
