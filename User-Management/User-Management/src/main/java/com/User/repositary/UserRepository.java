package com.User.repositary;


import com.User.entity.Role;
import com.User.entity.User;
import com.User.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    List<User> findUserByUserType(UserType userType);

    User findUserByUsername(String userName);

    List<User> findUserByRoles(Role role);



}
