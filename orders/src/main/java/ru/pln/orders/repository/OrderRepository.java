package ru.pln.orders.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.pln.orders.domains.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where :number is null or o.number = :number")
    List<Order> searchTest(@Param("number") Integer number);

    @Query("select o from Order o where :number is null or o.number = :number")
    Page<Order> search(@Param("number") Integer number, Pageable pageable);
}
