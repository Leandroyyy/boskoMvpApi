package br.com.bosko.boskoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bosko.boskoapi.dto.request.UserRequestDto;
import br.com.bosko.boskoapi.entity.User;
import br.com.bosko.boskoapi.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private UserService userService;


  @PostMapping
  public ResponseEntity<User> create(@RequestBody UserRequestDto request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
  }

  @GetMapping
  @PreAuthorize("permiteAll()")
  public ResponseEntity<Page<User>> findAll(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findOne(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserRequestDto request) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
