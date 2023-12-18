package ru.pln.products.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pln.products.domains.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where lower(p.title) like lower(concat(:title, '%'))")
    List<Product> searchTest(@Param("title") String title);

    @Query("select p from Product p where lower(p.title) like lower(concat(:title, '%'))")
    Page<Product> search(@Param("title") String title, Pageable pageable);
}
