package com.smartcontactmanager.app.services.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartcontactmanager.app.entities.User;
import com.smartcontactmanager.app.helpers.AppConstants;
import com.smartcontactmanager.app.helpers.ResourceNotFoundException;
import com.smartcontactmanager.app.repositories.UserRepo;
import com.smartcontactmanager.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass()); 

    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @Override
    public User saveUser(User user) {
        String userId = java.util.UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepo.save(user);
   }

    @Override
    public Optional<User> getUserById(String id) {
     return userRepo.findById(id);    
    }

    @Override
    public Optional<User> updateUser(User user) {
        User newUser = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user.getUserId()));
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setAbout(user.getAbout());
        newUser.setPassword(user.getPassword());    
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setProfilePic(user.getProfilePic());
        newUser.setEnabled(user.isEnabled());
        newUser.setEmailVerified(user.isEmailVerified());
        newUser.setPhoneVerified(user.isPhoneVerified());
        newUser.setProvider(user.getProvider());
        newUser.setProviderUserId(user.getProviderUserId());
        logger.info("Updating user with id: " + user.getUserId());
        return Optional.ofNullable(userRepo.save(newUser));     

    }

    @Override
    public void deleteUser(String id) {
        User newUser = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepo.delete(newUser);
        logger.info("Deleted user with id: " + id);
    }

    @Override
    public boolean isUserExist(String email) {
        User newUser = userRepo.findById(email).orElseThrow(null);
        return newUser != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user!=null ? true : false; 
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
