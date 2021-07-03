/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.resource;

import com.hmzn.app.antiguessing.service.GenerateCombinationService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

/**
 *
 * @author hamizan
 */
@Path("/betting")
public class GenerateCombinationResource {
    
    GenerateCombinationService service;
    
    public GenerateCombinationResource (SessionFactory sessionFactory) {
        service = new GenerateCombinationService(sessionFactory);
    }
    
    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response generateCombination (@QueryParam("round-id") String roundId) {
        return service.generateCombination(roundId);
    }
}
