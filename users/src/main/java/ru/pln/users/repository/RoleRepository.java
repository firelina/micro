package ru.pln.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pln.users.domains.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
