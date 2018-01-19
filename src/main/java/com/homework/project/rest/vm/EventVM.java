package com.homework.project.rest.vm;

import com.homework.project.domain.AbstractAuditingEntity;
import com.homework.project.domain.EventType;
import lombok.Data;

import java.util.List;

@Data
public class EventVM extends AbstractAuditingEntity {
    private Long id;
    private EventType eventType;
    private List<EventUserVM> eventUsers;

    public EventVM(final Long id, final EventType eventType) {
        this.id = id;
        this.eventType = eventType;
    }
}
