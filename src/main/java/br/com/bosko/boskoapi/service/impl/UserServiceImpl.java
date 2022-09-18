package br.com.bosko.boskoapi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bosko.boskoapi.dto.request.UserRequestDto;
import br.com.bosko.boskoapi.entity.User;
import br.com.bosko.boskoapi.exception.EmailAlreadyExistsException;
import br.com.bosko.boskoapi.exception.PhoneAlreadyExistsException;
import br.com.bosko.boskoapi.exception.UserNotFoundException;
import br.com.bosko.boskoapi.repository.UserRepository;
import br.com.bosko.boskoapi.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User create(UserRequestDto request) {
    var emailAlreadyExists = userRepository.findByEmail(request.email());
    if (!emailAlreadyExists.isEmpty())
      throw new EmailAlreadyExistsException();

    var phoneAlreadyExists = userRepository.findByPhone(request.phone());
    if (!phoneAlreadyExists.isEmpty())
      throw new PhoneAlreadyExistsException();

    var user = User.of(request);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return user;
  }

  @Override
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
  }

  @Override
  public User update(Long id, UserRequestDto request) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    var emailAlreadyExists = userRepository.findByEmail(request.email());
    if (!emailAlreadyExists.isEmpty() && user.getId() != emailAlreadyExists.get().getId())
      throw new EmailAlreadyExistsException();

    var phoneAlreadyExists = userRepository.findByPhone(request.phone());
    if (!phoneAlreadyExists.isEmpty() && user.getId() != phoneAlreadyExists.get().getId())
      throw new PhoneAlreadyExistsException();

    BeanUtils.copyProperties(request, user);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setId(id);

    userRepository.save(user);

    return user;
  }

  @Override
  public void delete(Long id) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    userRepository.deleteById(user.getId());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
  }
}
