package com.example.pantrypal_backend.user;

import com.example.pantrypal_backend.db.UserRepository;
import com.example.pantrypal_backend.db.entity.UserEntity;
import com.example.pantrypal_backend.model.UserBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsManager userDetailsManager;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsManager userDetailsManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsManager = userDetailsManager;
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Transactional
    public void register(String username, String password) throws UserAlreadyExistException {
        UserEntity existingUser = userRepository.findUserByUsername(username);
        logger.info(username + ", " + password);
        if (existingUser != null) {
            logger.info("User " + username + " already exists!");
//            return;
            throw new UserAlreadyExistException("Username already exists");
        }
        String encodedPw = passwordEncoder.encode(password);
        UserDetails user = User.builder().username(username)
                .password(encodedPw)
                .roles("USER")     // not setting role results in error
                .build();
        userDetailsManager.createUser(user);
        logger.info(user.toString());
        userRepository.save(new UserBuilder().setUsername(username).build());
    }
}
