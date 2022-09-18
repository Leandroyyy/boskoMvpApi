package br.com.bosko.boskoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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

import br.com.bosko.boskoapi.entity.Book;
import br.com.bosko.boskoapi.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
  }

  @GetMapping
  public ResponseEntity<Page<Book>> findAll(@PageableDefault(size = 10) Pageable pageable,
      @Param("company") String company) {
    if (company != null)
      return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByCompany(pageable, company));

    return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> findOne(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
    return ResponseEntity.status(HttpStatus.OK).body(bookService.update(id, book));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    bookService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
