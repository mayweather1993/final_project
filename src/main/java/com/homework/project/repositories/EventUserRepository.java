package com.homework.project.repositories;

import com.homework.project.domain.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventUserRepository extends JpaRepository<EventUser, Long> {
}
