package com.homework.project.models;

import org.springframework.context.ApplicationEvent;

public class BaseEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BaseEvent(Object source) {
        super(source);
    }
}
