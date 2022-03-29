package com.redhat.demos.services;

import com.redhat.demos.entities.Event;
import com.redhat.demos.entities.Session;
import com.redhat.demos.entities.Speaker;
import com.redhat.demos.repositories.EventsRepository;
import com.redhat.demos.repositories.SessionsRepository;
import com.redhat.demos.repositories.SpeakersRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class SpeakersService {

    @Inject
    SpeakersRepository repository;

    @Inject
    SessionsRepository sessionsRepository;

    @Inject
    EventsRepository eventsRepository;

    public Speaker getSpeakerById(UUID speakerId) {
        return repository.findSpeakerById(speakerId);
    }

    public List<Speaker> getSpeakersForSession(UUID sessionId) {
        Session session = sessionsRepository.findSessionBySessionId(sessionId);
        if (session == null)
            return null;
        Speaker entity = repository.findSpeakerById(session.getSpeaker());
        ArrayList<Speaker> speakers = new ArrayList<>();
        speakers.add(entity);
        return speakers;
    }

    public List<Speaker> getSpeakersForEvent(UUID eventId) {
        //find all sessions for event
        List<Session> sessions = sessionsRepository.getAllSessionsForEvent(eventId);
        if (sessions == null || sessions.size() == 0)
            return null;
        ArrayList<Speaker> speakers = new ArrayList<>();
        for (Session session : sessions) {
            Speaker speaker = repository.findSpeakerById(session.getSpeaker());
            if (speaker == null)
                break;
            //add speaker to collection
            speakers.add(speaker);
        }
        return speakers;
    }

    public List<Speaker> getAllSpeakers() {
        return repository.getAllSpeakers();
    }

    public Speaker updateOrInsert(Speaker entity) {
        return repository.updateOrInsert(entity);
    }
}
