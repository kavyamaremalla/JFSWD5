package com.example.todomanagement.security;

import com.example.todomanagement.entity.User;
import com.example.todomanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameorEmail, usernameorEmail).get();

        Set<GrantedAuthority> authoritySet = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(
                usernameorEmail,
                user.getPassword(),
                authoritySet
        );
    }
}
