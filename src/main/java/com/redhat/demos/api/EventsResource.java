package com.redhat.demos.api;

import com.redhat.demos.entities.Event;
import com.redhat.demos.services.EventsService;

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
@Path("/events")
public class EventsResource {

    @Inject
    EventsService service;

    @GET
    @Path("/")
    public Response getEvents() {
        List<Event> events = service.getAllEvents();

        return Response.ok(events)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/{eventId}")
    public Response getEventById(@PathParam("eventId") UUID eventId){
        Event event = service.getEventById(eventId);

        return Response.ok(event)
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Path("/")
    @Transactional
    public Response newEvent(Event newEvent) {
        Event event = service.updateOrInsert(newEvent);

        return Response.created(URI.create("/events/" + event.getId()))
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/{eventId}")
    @Transactional
    public Response updateEvent(Event eventToUpdate) {
        Event event = service.updateOrInsert(eventToUpdate);

        return Response.accepted(event)
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
