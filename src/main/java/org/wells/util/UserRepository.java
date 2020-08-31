package org.wells.util;

import org.wells.models.User;

import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    
    User save(User entity);

	User findByEmail(String email);

	User findByMobile(String mobile);
}