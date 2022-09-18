package br.com.bosko.boskoapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bosko.boskoapi.entity.Book;

public interface BookService {
  
  Book create(Book book);

  Page<Book> findAll(Pageable pageable);

  Book findById(Long id);

  Page<Book> findAllByCompany(Pageable pageable, String company);

  Book update(Long id, Book book);

  void delete(Long id);
}
