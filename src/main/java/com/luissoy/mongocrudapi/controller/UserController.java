package com.luissoy.mongocrudapi.controller;

import com.luissoy.mongocrudapi.dto.UserDto;
import com.luissoy.mongocrudapi.exception.DataIntegrityException;
import com.luissoy.mongocrudapi.exception.DataNotFoundException;
import com.luissoy.mongocrudapi.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll(
            Pageable pageable)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(
            @Parameter(example = "606d1b91df256d34e0a44a35")
            @PathVariable("id")
            String id)
            throws DataNotFoundException
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody UserDto userDto)
            throws DataIntegrityException
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Parameter(example = "606d1b91df256d34e0a44a35")
            @PathVariable("id")
            String id
            , @RequestBody
            UserDto userDto)
            throws DataNotFoundException, DataIntegrityException
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.update(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @Parameter(example = "606d1b91df256d34e0a44a35")
            @PathVariable("id")
            String id)
            throws DataNotFoundException, DataIntegrityException
    {
        userService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
