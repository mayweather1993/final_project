package com.homework.project.services;

import com.homework.project.domain.User;
import com.homework.project.domain.UserType;
import com.homework.project.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@AllArgsConstructor
@Slf4j
public class SecurityAwareService implements UserDetailsService {

    private final UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        try {
            final User user = usersService.findByEmail(username);
            return createSpringSecurityUser(user);
        } catch (ResourceNotFoundException e) {
            log.error("User with email [{}] not found.", username);
            throw new UsernameNotFoundException(username, e);
        }
    }

    public static org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        final UserType type = user.getType();
        final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(type.name());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                Collections.singletonList(grantedAuthority));
    }
}
