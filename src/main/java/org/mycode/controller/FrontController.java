package org.mycode.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FrontController {
    @GetMapping("/")
    public String viewIndexPage() {
        GrantedAuthority grantedAuthority = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().toArray(new GrantedAuthority[]{})[0];
        if (grantedAuthority.getAuthority().equals("ROLE_CUSTOMER")) {
            return "redirect:/order";
        }
        return "redirect:/menu";
    }

    @GetMapping("logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("up", HttpStatus.OK);
    }
}
