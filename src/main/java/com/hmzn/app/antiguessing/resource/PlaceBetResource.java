/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.resource;

import com.hmzn.app.antiguessing.json.PlaceBetRequest;
import com.hmzn.app.antiguessing.service.PlaceBetService;
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
@Path("/betting")
public class PlaceBetResource {
    
    PlaceBetService service;
    
    public PlaceBetResource (SessionFactory sessionFactory){
        service = new PlaceBetService(sessionFactory);
    }
    
    @POST
    @Path("/place")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response placeBet (PlaceBetRequest request){
        return service.placeBet(request);
    }
    
}
