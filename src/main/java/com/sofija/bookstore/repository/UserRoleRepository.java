package com.sofija.bookstore.repository;

import com.sofija.bookstore.model.UserRole;
import com.sofija.bookstore.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
