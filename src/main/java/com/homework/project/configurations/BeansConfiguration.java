package com.homework.project.configurations;

import com.homework.project.listeners.ProjectEventListener;
import com.homework.project.services.SecurityAwareService;
import com.homework.project.services.UsersService;
import com.homework.project.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class BeansConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return SecurityUtils::getCurrentUserLogin;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean(final UsersService usersService) {
        return new SecurityAwareService(usersService);
    }

    @Bean
    public ApplicationEventMulticaster eventMulticaster(final List<ProjectEventListener> listenerList) {
        final ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        listenerList.forEach(eventMulticaster::addApplicationListener);
        return eventMulticaster;
    }
}
