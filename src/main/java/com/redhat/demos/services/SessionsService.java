package com.redhat.demos.services;


import com.redhat.demos.entities.Session;
import com.redhat.demos.repositories.SessionsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SessionsService {
    @Inject
    SessionsRepository repository;

    public List<Session> getSessionsForEvent(UUID eventId) {
        return repository.getAllSessionsForEvent(eventId);
    }

    public Session getSessionById(UUID id) {
        return repository.findSessionBySessionId(id);
    }

    public Session updateOrInsert(Session entity) {
        return repository.updateOrInsert(entity);
    }
}
