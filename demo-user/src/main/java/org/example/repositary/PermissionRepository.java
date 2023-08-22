package org.example.repositary;



import java.util.List;

import org.example.entity.Permission;
import org.example.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    org.example.entity.Permission findPermissionByName(String name);

    List<org.example.entity.Permission> findPermissionByStatus(UserStatus status);
}