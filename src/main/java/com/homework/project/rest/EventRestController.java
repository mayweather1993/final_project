package com.homework.project.rest;

import com.homework.project.domain.Event;
import com.homework.project.rest.vm.EventVM;
import com.homework.project.services.EventsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.homework.project.SecurityConstants.HAS_ADMIN_AUTHORITY;
import static com.homework.project.SecurityConstants.HAS_ADMIN_MODERATOR_AUTHORITY;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventRestController {

    private final EventsService eventsService;

    //Create an Event
    @PostMapping
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity post(@RequestBody Event event) {
        eventsService.create(event);
        return new ResponseEntity<>(CREATED);
    }

    //update an Event
    @PutMapping("{id}")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Event event) {
        eventsService.update(id, event);
        return new ResponseEntity<>(OK);
    }

    //delete an Event
    @DeleteMapping("{id}")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        eventsService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping
    @PreAuthorize(HAS_ADMIN_MODERATOR_AUTHORITY)
    public ResponseEntity getEvents(Pageable pageable) {
        final Page<EventVM> events = eventsService.getEvents(pageable);
        return new ResponseEntity<>(events, OK);
    }

}
