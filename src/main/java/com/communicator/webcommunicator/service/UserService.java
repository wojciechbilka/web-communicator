package com.communicator.webcommunicator.service;

import com.communicator.webcommunicator.model.User;
import com.communicator.webcommunicator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public Set<User> getAll() {
        return userRepository.findAll().stream().collect(Collectors.toSet());
    }

    public User getById(int id) {
        return userRepository.getOne(id);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void deleteById(User user) {
        userRepository.delete(user);
    }

    public boolean userExist(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isPresent();
    }

    public User getByUsername(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isPresent() ? user.get() : null;
    }
}