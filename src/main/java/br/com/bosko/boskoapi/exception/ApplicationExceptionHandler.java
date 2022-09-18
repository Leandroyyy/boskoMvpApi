package br.com.bosko.boskoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found!")
  @ExceptionHandler(UserNotFoundException.class)
  public void handleException(UserNotFoundException e) {
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email already exists!")
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public void handleException(EmailAlreadyExistsException e) {
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Phone already exists!")
  @ExceptionHandler(PhoneAlreadyExistsException.class)
  public void handleException(PhoneAlreadyExistsException e) {
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book not found!")
  @ExceptionHandler(BookNotFoundException.class)
  public void handleException(BookNotFoundException e) {
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Progress not found!")
  @ExceptionHandler(ProgressNotFoundException.class)
  public void handleException(ProgressNotFoundException e) {
  }
}
