package org.mycode.service;

import java.util.List;

public interface AuthorityService {
    void setAuthority(String username, String authorityName);

    void deleteAuthority(String username, String authorityName);

    void deleteUser(String username);

    List<String> getAllAuthoritiesOfUser(String username);

    String getIdentityInfoOfUser(String username);
}
