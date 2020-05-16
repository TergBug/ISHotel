package org.mycode.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserSecurityDto {
    private String username;
    private String password;
    private String authority;
    private String identityInfo;

    public UserSecurityDto() {
    }

    public UserSecurityDto(String authority) {
        this.authority = authority;
    }

    public UserSecurityDto(String username, String password, String authority, String identityInfo) {
        this.username = username;
        this.password = password;
        this.authority = authority;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getIdentityInfo() {
        return identityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        this.identityInfo = identityInfo;
    }

    @Override
    public String toString() {
        return "UserSecurityDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", identityInfo='" + identityInfo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSecurityDto that = (UserSecurityDto) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, authority);
    }
}
