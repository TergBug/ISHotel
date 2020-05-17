package org.mycode.controller;

import org.mycode.service.ReportsForEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("menu")
public class EmployeeMenuController {
    private ReportsForEmployeeService reportsForEmployeeService;

    @Autowired
    public EmployeeMenuController(ReportsForEmployeeService reportsForEmployeeService) {
        this.reportsForEmployeeService = reportsForEmployeeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public String menu() {
        return "empMenu";
    }

    @GetMapping("home")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public String home() {
        return "index";
    }

    @GetMapping("rooms")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public ResponseEntity<byte[]> reportRooms() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        reportsForEmployeeService.freeRooms().write(baos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/msword"));
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("customers")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public ResponseEntity<byte[]> reportCustomers() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        reportsForEmployeeService.customers().write(baos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/msword"));
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("facilities")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public ResponseEntity<byte[]> reportFacilities() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        reportsForEmployeeService.facilities().write(baos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/msword"));
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}
