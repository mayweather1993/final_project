package com.homework.project.listeners;

import com.homework.project.models.BaseEvent;
import org.springframework.context.ApplicationListener;

public interface ProjectEventListener<T extends BaseEvent> extends ApplicationListener<T> {
}
