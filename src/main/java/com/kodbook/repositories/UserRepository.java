package com.kodbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entities.User;

public interface UserRepository extends JpaRepository<User, Long>
{
  User findByUsername(String username);
  List<User> findByUsernameContainingIgnoreCase(String username);
  User findByEmail(String email);
}
