package com.redhat.demos.repositories;

import com.redhat.demos.entities.Speaker;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class SpeakersRepository implements PanacheRepository<Speaker> {
    public Speaker findSpeakerById(UUID speakerId) {
        return find("id", speakerId).firstResult();
    }
    public List<Speaker> getAllSpeakers(){
        Stream<Speaker> speakers = streamAll();
        try{
            return speakers.collect(Collectors.toList());
        } finally {
            speakers.close();
        }
    }
    public Speaker updateOrInsert(Speaker entity) {
        Speaker fromDb = findSpeakerById(entity.getId());
        if (fromDb != null) {
            fromDb.setCompany(entity.getCompany());
            fromDb.setEmail(entity.getEmail());
            fromDb.setLinkedIn(entity.getLinkedIn());
            fromDb.setName(entity.getName());
            fromDb.setTitle(entity.getTitle());
            fromDb.setTwitter(entity.getTwitter());

            persist(fromDb);
            return fromDb;
        }
        persist(entity);
        return entity;
    }
}
