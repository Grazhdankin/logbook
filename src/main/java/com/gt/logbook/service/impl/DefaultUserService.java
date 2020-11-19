package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.User;
import com.gt.logbook.domain.repository.UserRepository;
import com.gt.logbook.service.UserService;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository repository;

    public DefaultUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<User> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<User> findAllRevisions(Long id) {
        return repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public User save(User entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}