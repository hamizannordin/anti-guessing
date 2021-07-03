/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.resource;

import com.hmzn.app.antiguessing.json.SetRoundRequest;
import com.hmzn.app.antiguessing.service.SetRoundService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

/**
 *
 * @author hamizan
 */
@Path("/round")
public class SetRoundResource {
    
    SetRoundService service;
    
    public SetRoundResource (SessionFactory sessionFactory){
        service = new SetRoundService(sessionFactory);
    }
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response createRound (SetRoundRequest request){
        return service.createRound(request);
    }
}
