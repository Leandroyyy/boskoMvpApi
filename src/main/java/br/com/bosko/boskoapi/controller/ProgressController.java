package br.com.bosko.boskoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bosko.boskoapi.dto.request.ProgressRequestDto;
import br.com.bosko.boskoapi.entity.Progress;
import br.com.bosko.boskoapi.service.ProgressService;

@RestController
@RequestMapping("/api/user/progress")
public class ProgressController{

  @Autowired
  private ProgressService progressService;

  @GetMapping
  public ResponseEntity<Page<Progress>> findAll(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(progressService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Progress> findById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(progressService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Progress> addProgress(@RequestBody ProgressRequestDto progress) {
    return ResponseEntity.status(HttpStatus.OK).body(progressService.addProgressToUser(progress));
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Progress> editProgress(@PathVariable Long id,@RequestBody ProgressRequestDto progress) {
    return ResponseEntity.status(HttpStatus.OK).body(progressService.editUserProgress(id ,progress));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Progress> deleteProgress(@PathVariable Long id) {
    progressService.removeUserProgress(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}