package com.truetrack.sharing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truetrack.sharing.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String username);

}
