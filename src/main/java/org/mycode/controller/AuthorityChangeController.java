package org.mycode.controller;

import org.mycode.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorityChangeController {
    private AuthorityService authorityService;

    @Autowired
    public AuthorityChangeController(AuthorityService authorityService) {
        this.authorityService = authorityService;
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
