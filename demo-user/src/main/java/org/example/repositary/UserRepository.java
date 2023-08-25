package org.example.repositary;

import java.util.List;

import org.example.entity.Role;
import org.example.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<org.example.entity.User, Long> {

    org.example.entity.User findUserByEmail(String email);

    List<org.example.entity.User> findUserByUserType(UserType userType);

    org.example.entity.User findUserByUsername(String userName);

    List<org.example.entity.User> findUserByRoles(Role role);



}