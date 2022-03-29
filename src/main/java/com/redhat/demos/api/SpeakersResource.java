package com.redhat.demos.api;

import com.redhat.demos.entities.Speaker;
import com.redhat.demos.services.SpeakersService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/speakers")
public class SpeakersResource {

    @Inject
    SpeakersService service;

    @GET
    public Response getSpeakers(@QueryParam("eventId") UUID eventId, @QueryParam("sessionId") UUID sessionId) {
        List<Speaker> speakers = eventId != null ? service.getSpeakersForEvent(eventId):
                sessionId != null ? service.getSpeakersForSession(sessionId): service.getAllSpeakers();

        return Response.ok(speakers)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("{speakerId}")
    public Response getSpeaker(@PathParam("speakerId") UUID speakerId) {
        Speaker speaker = service.getSpeakerById(speakerId);

        return Response.ok(speaker)
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Transactional
    public Response newSpeaker(Speaker newSpeaker) {
        Speaker speaker = service.updateOrInsert(newSpeaker);

        return Response.created(URI.create("/speakers/" + speaker.getId()))
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("{speakerId}")
    @Transactional
    public Response updateSpeaker(@PathParam("speakerId") UUID speakerId, Speaker speakerToUpdate) {
        Speaker speaker = service.updateOrInsert(speakerToUpdate);

        return Response.accepted(speaker)
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
