package org.mycode.service.impl;

import org.mycode.dto.UserSecurityDto;
import org.mycode.exceptions.CannotSignUpException;
import org.mycode.model.AuthoritySecurity;
import org.mycode.model.UserSecurity;
import org.mycode.repository.AuthoritySecurityRepository;
import org.mycode.repository.CustomerRepository;
import org.mycode.repository.EmployeeRepository;
import org.mycode.repository.UserSecurityRepository;
import org.mycode.service.SignUpService;
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
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public SignUpServiceImpl(UserSecurityRepository userSecurityRepository,
                             AuthoritySecurityRepository authoritySecurityRepository,
                             PasswordEncoder passwordEncoder,
                             CustomerRepository customerRepository,
                             EmployeeRepository employeeRepository) {
        this.userSecurityRepository = userSecurityRepository;
        this.authoritySecurityRepository = authoritySecurityRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void signUp(UserSecurityDto userDto) {
        Optional<AuthoritySecurity> authority = authoritySecurityRepository.findById(userDto.getAuthority());
        if (authority.isPresent() && !userSecurityRepository.findById(userDto.getUsername()).isPresent()) {
            userSecurityRepository.save(new UserSecurity(userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()), 1,
                    Arrays.stream(new AuthoritySecurity[]{authority.get()}).collect(Collectors.toSet()),
                    userDto.getIdentityInfo()));
        } else {
            throw new CannotSignUpException();
        }
    }

    @Override
    public boolean verifyCustomerByPassport(String passport) {
        return customerRepository.findCustomerByPassport(passport) != null;
    }

    @Override
    public boolean verifyEmployeeByEIN(String ein) {
        return employeeRepository.findEmployeeByEin(ein) != null;
    }
}
