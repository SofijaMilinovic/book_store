package com.sofija.bookstore.service;

import com.sofija.bookstore.model.Role;
import com.sofija.bookstore.model.User;
import com.sofija.bookstore.model.UserRole;
import com.sofija.bookstore.model.UserRoleId;
import com.sofija.bookstore.repository.RoleRepository;
import com.sofija.bookstore.repository.UserRepository;
import com.sofija.bookstore.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    public User register(User user) throws Exception {
        if (!userRepository.findByEmail(user.getEmail()).isEmpty()) {
            throw new Exception("User already exists with the given email");
        }

        User registeredUser = userRepository.save(user);
        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER");
        addCustomerRoleToUser(customerRole, registeredUser);
        registeredUser.setRoles(Collections.singletonList(customerRole));
        return registeredUser;
    }

    private void addCustomerRoleToUser(Role customerRole, User registeredUser) {
        UserRoleId userRoleId = new UserRoleId(registeredUser.getId(), customerRole.getId());
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(userRoleId);
        userRoleRepository.save(userRole);
    }
}
