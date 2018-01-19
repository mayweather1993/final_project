package com.homework.project.services;

import com.homework.project.domain.Event;
import com.homework.project.rest.vm.EventVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventsService extends CrudService<Event>{
    Page<EventVM> getEvents(Pageable pageable);

    List<Event> getAllForMonth(int month);
}
