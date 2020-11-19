package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.User;

public interface UserService {

    List<User> findAll();

    Optional<User> findOne(Long id);

    List<User> findAllRevisions(Long id);

    User save(User entity);

    void delete(Long id);
}