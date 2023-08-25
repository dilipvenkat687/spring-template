package com.User.repositary;


import java.util.List;

import com.User.entity.Permission;
import com.User.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findPermissionByName(String name);

    List<Permission> findPermissionByStatus(UserStatus status);
}
