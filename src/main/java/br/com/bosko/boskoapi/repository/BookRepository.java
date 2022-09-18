package br.com.bosko.boskoapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bosko.boskoapi.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
  @Query("SELECT b FROM Book b WHERE b.companyName = :company")
  Page<Book> findAllByCompanyName(Pageable pageable, @Param("company") String company);
}
