package ru.pln.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pln.users.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
