package com.airline.airline.Security.repositories;

import com.airline.airline.Security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(
            value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)",
            nativeQuery = true
    )
    List<Role> getUserNotRoles(Integer userId);
}
