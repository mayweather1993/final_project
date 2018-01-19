package com.homework.project.services;

import com.homework.project.domain.EventUser;
import com.homework.project.repositories.EventUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventUsersServiceImpl implements EventUsersService {
    private final EventUserRepository eventUserRepository;

    @Override
    public Long create(EventUser entity) {
        EventUser eventUser = eventUserRepository.save(entity);
        return eventUser.getId();
    }

    @Override
    public void update(Long id, EventUser updated) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        eventUserRepository.deleteById(id);

    }
}
