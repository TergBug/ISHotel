package org.mycode.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "security_authorities")
@NamedEntityGraph(name = "Authority.detail", attributeNodes = @NamedAttributeNode("users"))
public class AuthoritySecurity {
    @Id
    private String authority;
    @ManyToMany(mappedBy = "authorities")
    private Set<UserSecurity> users;

    public AuthoritySecurity() {
    }

    public AuthoritySecurity(String authority, Set<UserSecurity> users) {
        this.authority = authority;
        this.users = users;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<UserSecurity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserSecurity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "AuthoritySecurity{" +
                "authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthoritySecurity that = (AuthoritySecurity) o;
        return Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
