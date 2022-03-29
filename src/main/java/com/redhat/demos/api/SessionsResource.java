package com.redhat.demos.api;

import com.redhat.demos.entities.Session;
import com.redhat.demos.services.SessionsService;

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
@Path("/sessions")
public class SessionsResource {

    @Inject
    SessionsService service;

    @GET
    public Response getSessions(@QueryParam("eventId") UUID eventId) {
        List<Session> sessions = service.getSessionsForEvent(eventId);
        return Response.ok(sessions)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("/{sessionId}")
    public Response getSession(@PathParam("sessionId") UUID sessionId, @QueryParam("eventId") UUID eventId) {
        Session session = service.getSessionById(sessionId);

        return Response.ok(session)
                .status(Response.Status.OK)
                .build();
    }

    @POST
    @Transactional
    public Response newSession(Session newSession) {
        Session session = service.updateOrInsert(newSession);

        return Response.created(URI.create( "/sessions/" + session.getId()))
                .status(Response.Status.CREATED)
                .build();
    }

    @PUT
    @Path("/{sessionId}")
    @Transactional
    public Response updateSession(@PathParam("sessionId") UUID sessionId, Session sessionToUpdate) {
        Session session = service.updateOrInsert(sessionToUpdate);

        return Response.accepted(session)
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
