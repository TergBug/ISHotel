package org.mycode.service.impl;

import org.mycode.dto.UserSecurityDto;
import org.mycode.exceptions.CannotSignUpException;
import org.mycode.model.AuthoritySecurity;
import org.mycode.model.UserSecurity;
import org.mycode.repository.AuthoritySecurityRepository;
import org.mycode.repository.UserSecurityRepository;
import org.mycode.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SignUpServiceImpl implements SignUpService {
    private UserSecurityRepository userSecurityRepository;
    private AuthoritySecurityRepository authoritySecurityRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(UserSecurityRepository userSecurityRepository,
                             AuthoritySecurityRepository authoritySecurityRepository, PasswordEncoder passwordEncoder) {
        this.userSecurityRepository = userSecurityRepository;
        this.authoritySecurityRepository = authoritySecurityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserSecurityDto userDto) {
        Optional<AuthoritySecurity> authority = authoritySecurityRepository.findById("USER");
        if (authority.isPresent()) {
            userSecurityRepository.save(new UserSecurity(userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()), 1,
                    Arrays.stream(new AuthoritySecurity[]{authority.get()}).collect(Collectors.toSet())));
        } else {
            throw new CannotSignUpException();
        }
    }
}
