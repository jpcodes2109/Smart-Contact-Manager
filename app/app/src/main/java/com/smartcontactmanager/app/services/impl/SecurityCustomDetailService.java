package com.smartcontactmanager.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.smartcontactmanager.app.repositories.UserRepo;

@Service

public class SecurityCustomDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
    return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found With Email: " + username));
    }

}
