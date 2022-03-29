package com.redhat.demos.repositories;

import com.redhat.demos.entities.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class EventsRepository implements PanacheRepository<Event> {
    public Event findByEventId(UUID eventId) {
        return find("id", eventId).firstResult();
    }
    public List<Event> getAllEvents() {
        Stream<Event> events = streamAll();

        try{
            return events.collect(Collectors.toList());
        } finally {
            events.close();
        }
    }
    public Event updateOrInsert(Event entity) {
        Event fromDb = findByEventId(entity.getId());
        if (fromDb != null) {
            fromDb.setAudience(entity.getAudience());
            fromDb.setDescription(entity.getDescription());
            fromDb.setLocation(entity.getLocation());
            fromDb.setName(entity.getName());
            fromDb.setTopics(entity.getTopics());
            fromDb.setType(entity.getType());
            persist(fromDb);
            return fromDb;
        }
        persist(entity);
        return entity;
    }
}
