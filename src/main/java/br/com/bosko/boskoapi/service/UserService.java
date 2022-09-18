package br.com.bosko.boskoapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bosko.boskoapi.dto.request.UserRequestDto;
import br.com.bosko.boskoapi.entity.User;

public interface UserService {
  
  User create(UserRequestDto request);

  Page<User> findAll(Pageable pageable);

  User findById(Long id);

  User update(Long id, UserRequestDto request);

  void delete(Long id);
}
