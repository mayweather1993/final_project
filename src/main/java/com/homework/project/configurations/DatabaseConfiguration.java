package com.homework.project.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.homework.project.repositories")
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class DatabaseConfiguration {
}
