package com.homework.project.repositories;

import com.homework.project.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> getAllForMonth(@Param("month") final int month);
}
