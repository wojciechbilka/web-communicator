package com.communicator.webcommunicator.service;

import com.communicator.webcommunicator.model.PersistentLogin;
import com.communicator.webcommunicator.repository.PersistentLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersistentLoginService implements PersistentTokenRepository {

    private final PersistentLoginRepository persistentLoginRepository;

    public void save(PersistentLogin persistentLogin) {
        persistentLoginRepository.save(persistentLogin);
    }

    public Set<PersistentLogin> getAll() {
        return persistentLoginRepository.findAll().stream().collect(Collectors.toSet());
    }

    public PersistentLogin getById(String series) {
        return persistentLoginRepository.getOne(series);
    }

    public void deleteById(String series) {
        persistentLoginRepository.deleteById(series);
    }

    public void deleteById(PersistentLogin persistentLogin) {
        persistentLoginRepository.delete(persistentLogin);
    }

    public boolean persistentLoginExist(String userName) {
        Optional<PersistentLogin> persistentLogin = persistentLoginRepository.findByUsername(userName);
        return persistentLogin.isPresent();
    }

    public PersistentLogin getByUsername(String userName) {
        Optional<PersistentLogin> persistentLogin = persistentLoginRepository.findByUsername(userName);
        return persistentLogin.isPresent() ? persistentLogin.get() : null;
    }


    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin login = new PersistentLogin();
        login.setUsername(token.getUsername());
        login.setSeries(token.getSeries());
        login.setToken(token.getTokenValue());
        login.setLastUsed(token.getDate());
        persistentLoginRepository.save(login);
    }

    @Override
    @Transactional
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Optional<PersistentLogin> login = persistentLoginRepository.findById(seriesId);

        if (login.isPresent()) {
            PersistentLogin persistentLogin = login.get();
            return new PersistentRememberMeToken(persistentLogin.getUsername(),
                    persistentLogin.getSeries(), persistentLogin.getToken(),persistentLogin.getLastUsed());
        }

        return null;
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        persistentLoginRepository.deleteByUsername(username);
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentLogin persistentLogin = persistentLoginRepository.getOne(series);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
    }
}