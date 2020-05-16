package org.mycode.controller.rest;

import org.mycode.dto.RoomDto;
import org.mycode.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/rooms")
public class RoomController {
    private RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public ResponseEntity<RoomDto> getById(@PathVariable long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('EMP') or hasRole('ADMIN')")
    public ResponseEntity<List<RoomDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@RequestBody RoomDto room) {
        service.create(room);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody RoomDto room) {
        service.update(room);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
