package org.wells.util;

import org.wells.models.UserDao;

import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
    
    UserDao save(UserDao entity);
}