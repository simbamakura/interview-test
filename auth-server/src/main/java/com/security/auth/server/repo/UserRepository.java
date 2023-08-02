package com.security.auth.server.repo;

import com.security.auth.server.domain.User;
import com.sim.data.repo.api.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends BaseRepo<User> {

    @Query("select u from User u where u.email LIKE CONCAT('%',:query,'%') or " +
            "u.firstName LIKE CONCAT('%',:query,'%') or u.lastName LIKE CONCAT('%',:query,'%') ")
    Page<User> searchUser(@Param("query") String query, Pageable pageable);

    User findByEmail(String username);

}