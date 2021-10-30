package com.sshhiinn.freewings.service;

import com.sshhiinn.freewings.common.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceTest {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Testov");
        user.setUserRole(UserRole.ADMIN);
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
