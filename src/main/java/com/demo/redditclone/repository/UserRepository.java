package com.demo.redditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.redditclone.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String username);
}
