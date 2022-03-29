package com.redhat.demos.services;

import com.redhat.demos.entities.Event;
import com.redhat.demos.repositories.EventsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventsService {

    @Inject
    EventsRepository repository;

    public List<Event> getAllEvents() {
        return repository.getAllEvents();
    }

    public Event getEventById(UUID eventId) {
        return repository.findByEventId(eventId);
    }

    public Event updateOrInsert(Event entity) {
        return repository.updateOrInsert(entity);
    }
}
