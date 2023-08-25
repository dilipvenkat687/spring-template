package com.User.repositary;


import com.User.entity.Role;
import com.User.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRoleByNameAndStatus(String name, UserStatus status);

}
