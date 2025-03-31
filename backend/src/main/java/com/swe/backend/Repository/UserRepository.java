package com.swe.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swe.backend.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}