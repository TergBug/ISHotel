package org.mycode.service.impl;

import org.mycode.exceptions.NoSuchEntryException;
import org.mycode.model.AuthoritySecurity;
import org.mycode.model.UserSecurity;
import org.mycode.repository.AuthoritySecurityRepository;
import org.mycode.repository.UserSecurityRepository;
import org.mycode.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private UserSecurityRepository userSecurityRepository;
    private AuthoritySecurityRepository authoritySecurityRepository;

    @Autowired
    public AuthorityServiceImpl(UserSecurityRepository userSecurityRepository,
                                AuthoritySecurityRepository authoritySecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
        this.authoritySecurityRepository = authoritySecurityRepository;
    }

    @Override
    public void setAuthority(String username, String authorityName) {
        UserSecurity user = userSecurityRepository.findUserSecurityByUsername(username);
        AuthoritySecurity authority = authoritySecurityRepository.findAuthoritySecurityByAuthority(authorityName);
        if (user != null && authority != null) {
            user.getAuthorities().add(authority);
            userSecurityRepository.save(user);
        } else {
            throw new NoSuchEntryException();
        }
    }

    @Override
    public void deleteAuthority(String username, String authorityName) {
        UserSecurity user = userSecurityRepository.findUserSecurityByUsername(username);
        AuthoritySecurity authority = authoritySecurityRepository.findAuthoritySecurityByAuthority(authorityName);
        if (user != null && authority != null) {
            user.getAuthorities().remove(authority);
            userSecurityRepository.save(user);
        } else {
            throw new NoSuchEntryException();
        }
    }

    @Override
    public void deleteUser(String username) {
        UserSecurity user = userSecurityRepository.findUserSecurityByUsername(username);
        if (user != null) {
            userSecurityRepository.delete(user);
        } else {
            throw new NoSuchEntryException();
        }
    }

    @Override
    public List<String> getAllAuthoritiesOfUser(String username) {
        UserSecurity user = userSecurityRepository.findUserSecurityByUsername(username);
        if (user != null) {
            return user.getAuthorities().stream().map(AuthoritySecurity::getAuthority).collect(Collectors.toList());
        } else {
            throw new NoSuchEntryException();
        }
    }

    @Override
    public String getIdentityInfoOfUser(String username) {
        UserSecurity user = userSecurityRepository.findUserSecurityByUsername(username);
        if (user != null) {
            return user.getIdentityInfo();
        } else {
            throw new NoSuchEntryException();
        }
    }
}
