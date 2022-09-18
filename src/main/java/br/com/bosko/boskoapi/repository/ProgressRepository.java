package br.com.bosko.boskoapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bosko.boskoapi.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress,Long>{
  @Query("SELECT u.progresses FROM User u WHERE u.email = :username")
  Page<Progress> findAllUserProgresses(Pageable pageable, @Param("username") String username);
}
