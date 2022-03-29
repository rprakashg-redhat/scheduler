package com.redhat.demos.repositories;

import com.redhat.demos.entities.Session;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class SessionsRepository implements PanacheRepository<Session> {
    public Session findSessionBySessionId(UUID sessionId) {
        return find("id", sessionId).firstResult();
    }
    public List<Session> getAllSessionsForEvent(UUID eventId) {
        Stream<Session> sessions = streamAll().filter( n -> eventId.equals(n.getEvent()));

        try{
            return sessions.collect(Collectors.toList());
        } finally {
            sessions.close();
        }
    }
    public Session updateOrInsert(Session entity) {
        Session fromDb = findSessionBySessionId(entity.getId());
        if (fromDb != null) {
            fromDb.setTitle(entity.getTitle());
            fromDb.setDescription(entity.getDescription());
            fromDb.setStart(entity.getStart());
            fromDb.setEnd(entity.getEnd());
            fromDb.setEvent(entity.getEvent());
            fromDb.setSpeaker(entity.getSpeaker());
            persist(fromDb);
            return fromDb;
        }
        persist(entity);
        return entity;
    }
}
