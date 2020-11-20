package com.gt.logbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.gt.logbook.domain.entity.DefaultUserDetails;
import com.gt.logbook.domain.entity.User;
import com.gt.logbook.domain.repository.UserRepository;

public class DefaultUserDetailsService implements UserDetailsService {

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid userName: '" + username + "'!"));

        return new DefaultUserDetails(user);
    }
}
