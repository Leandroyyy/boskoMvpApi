package br.com.bosko.boskoapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.bosko.boskoapi.service.BookService;
import br.com.bosko.boskoapi.service.ProgressService;
import br.com.bosko.boskoapi.service.UserService;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  ProgressService progressService;

  @Autowired
  BookService bookService;

  @Override
  public void run(String... args) throws Exception {

    // bookService.create(
    //     new Book("pequeno principe", "positivo"));

    // userService.create(
    //     new UserRequestDto(
    //         "Leandro",
    //         "Cavallari",
    //         11973138753L,
    //         "leandro.cavallari@gmail.com",
    //         "teste",
    //         Gender.MALE,
    //         1022986800L,
    //         0l,
    //         0));
  };

}
