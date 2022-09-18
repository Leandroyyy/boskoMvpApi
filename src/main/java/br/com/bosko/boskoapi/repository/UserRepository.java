package br.com.bosko.boskoapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.bosko.boskoapi.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
  
  Optional<User> findByEmail(String username);
  
  Optional<User> findByPhone(Long phone);
}
