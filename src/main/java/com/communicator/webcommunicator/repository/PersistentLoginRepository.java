package com.communicator.webcommunicator.repository;

import com.communicator.webcommunicator.model.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, String> {

    // JPA creates implementation according to generic type: PersistentLogin, name of method and method parameter
    Optional<PersistentLogin> findByUsername(String username);
    void deleteByUsername(String username);


}

