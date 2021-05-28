package org.api.repository;

import org.api.wrapper.generic.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserWrapper, Integer> {

}
