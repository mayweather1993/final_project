package com.homework.project.rest.vm;

import com.homework.project.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventUserVM extends AbstractAuditingEntity {
    private Long id;
    private Long hours;
    private Long eventId;
    private Long userId;
}
