package org.api.repository;

import java.util.Optional;

import org.api.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserWrapper, Integer> {
	Optional<UserWrapper> findByUserName(String userName);
}
