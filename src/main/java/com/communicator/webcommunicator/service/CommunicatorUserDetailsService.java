package com.communicator.webcommunicator.service;

import com.communicator.webcommunicator.model.CommunicatorUserDetails;
import com.communicator.webcommunicator.model.User;
import com.communicator.webcommunicator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunicatorUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("User name: " + userName + " not found."));

        return user.map(CommunicatorUserDetails::new).get();
    }
}

