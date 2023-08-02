package com.security.auth.server.repo;


import com.security.auth.server.domain.Role;
import com.sim.data.repo.api.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends BaseRepo<Role> {

    @Query("select r from Role r where r.name LIKE CONCAT('%',:query,'%')")
    Page<Role> searchRole(@Param("query") String query, Pageable pageable);

    Role findByName(String name);
}
