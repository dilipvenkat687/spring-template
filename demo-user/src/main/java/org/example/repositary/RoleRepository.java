package org.example.repositary;


import java.util.List;

import org.example.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<org.example.entity.Role, Long> {

    List<org.example.entity.Role> findRoleByNameAndStatus(String name, UserStatus status);

}