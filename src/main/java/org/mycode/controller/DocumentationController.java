package org.mycode.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class DocumentationController {
    @Value("classpath:documentation/doc.json")
    private Resource docFile;
    @Value("classpath:documentation/doc-admin.json")
    private Resource docFileAdmin;

    @GetMapping("documentation")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public String viewDocPage() {
        return "doc";
    }

    @GetMapping("doc")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public ResponseEntity<String> viewDocJSON() {
        GrantedAuthority grantedAuthority = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().toArray(new GrantedAuthority[]{})[0];
        Resource resourceFile = docFile;
        if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
            resourceFile = docFileAdmin;
        }
        try (InputStream fr = resourceFile.getInputStream()) {
            StringBuilder jsonStr = new StringBuilder();
            int c;
            while ((c = fr.read()) != -1) {
                jsonStr.append((char) c);
            }
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonStr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("no-doc", HttpStatus.NOT_FOUND);
    }
}
