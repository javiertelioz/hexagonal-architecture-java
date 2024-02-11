package com.inicions.tasks.infrastructure.services;

import com.inicions.tasks.infrastructure.repositories.user.JpaUserRepositoryAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.inicions.tasks.infrastructure.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    public UserDetailsServiceImpl(JpaUserRepositoryAdapter jpaUserRepositoryAdapter) {
        this.jpaUserRepositoryAdapter = jpaUserRepositoryAdapter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = UserEntity.fromDomainModel(jpaUserRepositoryAdapter.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not exist.".formatted(username))));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
