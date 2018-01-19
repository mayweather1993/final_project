package com.homework.project.services;

import com.homework.project.domain.Event;
import com.homework.project.domain.EventUser;
import com.homework.project.repositories.EventRepository;
import com.homework.project.rest.vm.EventUserVM;
import com.homework.project.rest.vm.EventVM;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventsService {
    private final EventRepository eventRepository;
    private final EventUsersService eventUsersService;

    private final Function<Event, EventVM> eventToVm = (e) -> {
        final EventVM eventVM = new EventVM(e.getId(), e.getEventType());
        eventVM.setCreatedBy(e.getCreatedBy());
        eventVM.setLastModifiedBy(e.getLastModifiedBy());
        eventVM.setLastModifiedDate(e.getLastModifiedDate());
        eventVM.setCreatedDate(e.getCreatedDate());
        eventVM.setEventUsers(
                e.getEventUsers().stream()
                        .map(eu -> new EventUserVM(eu.getId(), eu.getHours(), eu.getEvent().getId(), eu.getUserId()))
                        .collect(Collectors.toList())
        );
        return eventVM;
    };

    @Override
    public Long create(Event entity) {
        final List<EventUser> eventUsers = entity.getEventUsers();
        final Event event = eventRepository.save(entity);
        if (eventUsers != null) {
            for (EventUser eventUser : eventUsers) {
                eventUser.setEvent(event);
                eventUsersService.create(eventUser);
            }
        }
        return event.getId();
    }

    @Override
    public void update(Long id, Event updated) {
        Event event = eventRepository.getOne(id);
        event.setEventType(updated.getEventType());
        event.setEventUsers(updated.getEventUsers());
        eventRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Page<EventVM> getEvents(Pageable pageable) {
        final Page<Event> all = eventRepository.findAll(pageable);
        final List<EventVM> eventVMS = all.stream()
                .map(eventToVm)
                .collect(Collectors.toList());
        return new PageImpl<>(eventVMS, pageable, all.getTotalElements());
    }

    @Override
    public List<Event> getAllForMonth(final int month) {
        return eventRepository.getAllForMonth(month);
    }
}
