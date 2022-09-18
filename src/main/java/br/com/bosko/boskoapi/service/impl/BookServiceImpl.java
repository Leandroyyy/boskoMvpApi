package br.com.bosko.boskoapi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bosko.boskoapi.entity.Book;
import br.com.bosko.boskoapi.exception.BookNotFoundException;
import br.com.bosko.boskoapi.repository.BookRepository;
import br.com.bosko.boskoapi.repository.UserRepository;
import br.com.bosko.boskoapi.service.BookService;

@Service
public class BookServiceImpl implements BookService{

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Book create(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Page<Book> findAll(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }

  @Override
  public Book findById(Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
  }

  @Override
  public Page<Book> findAllByCompany(Pageable pageable, String company) {
    return bookRepository.findAllByCompanyName(pageable, company);
  }

  @Override
  public Book update(Long id, Book book) {
    var bookUpdate = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());

    BeanUtils.copyProperties(book, bookUpdate);
    bookUpdate.setId(id);

    bookRepository.save(bookUpdate);
    return bookUpdate;
  }

  @Override
  public void delete(Long id) {
    var book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
    bookRepository.delete(book);
  }

  // @Override
  // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  //   return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
  // }
}
