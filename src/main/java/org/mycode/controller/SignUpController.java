package org.mycode.controller;

import org.mycode.dto.UserSecurityDto;
import org.mycode.service.AuthorityService;
import org.mycode.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SignUpController {
    private SignUpService signUpService;
    private AuthorityService authorityService;

    @Autowired
    public SignUpController(SignUpService signUpService, AuthorityService authorityService) {
        this.signUpService = signUpService;
        this.authorityService = authorityService;
    }

    @GetMapping("signup")
    public String signUp(Model model) {
        model.addAttribute("user", new UserSecurityDto());
        return "signup";
    }

    @PostMapping("signup_proc")
    @ResponseStatus(code = HttpStatus.OK)
    public String signUpSubmission(@ModelAttribute("user") UserSecurityDto userSecurityDto) {
        signUpService.signUp(userSecurityDto);
        return "login";
    }

    @GetMapping("authchange/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<String>> getAuthoritiesOfUser(@PathVariable String username) {
        return new ResponseEntity<>(authorityService.getAllAuthoritiesOfUser(username), HttpStatus.OK);
    }

    @PostMapping("authchange/{username}/{authorityName}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void changeAuthority(@PathVariable String username, @PathVariable String authorityName) {
        authorityService.setAuthority(username, authorityName);
    }

    @DeleteMapping("authchange/{username}/{authorityName}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAuthority(@PathVariable String username, @PathVariable String authorityName) {
        authorityService.deleteAuthority(username, authorityName);
    }

    @DeleteMapping("authchange/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) {
        authorityService.deleteUser(username);
    }
}
