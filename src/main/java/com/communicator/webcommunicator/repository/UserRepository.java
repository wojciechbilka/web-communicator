package com.communicator.webcommunicator.repository;

import com.communicator.webcommunicator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // JPA creates implementation according to generic type: User, name of method and method parameter
    Optional<User> findByUserName(String userName);
}
