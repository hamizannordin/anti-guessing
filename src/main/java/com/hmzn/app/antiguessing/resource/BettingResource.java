/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.resource;

import com.hmzn.app.antiguessing.json.PlaceBetListRequest;
import com.hmzn.app.antiguessing.json.PlaceBetRequest;
import com.hmzn.app.antiguessing.service.GenerateService;
import com.hmzn.app.antiguessing.service.BettingService;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;

/**
 * Resource for betting-related service
 * 
 * @author hamizan
 */
@Path("/betting")
public class BettingResource {
    
    GenerateService generateService;
    BettingService bettingService;
    
    /**
     * Initialized service class, pass database session
     * @param sessionFactory
     */
    public BettingResource (SessionFactory sessionFactory){
        bettingService = new BettingService(sessionFactory);
        generateService = new GenerateService(sessionFactory);
    }
    
    /**
     * Create new bet
     * @param request placeBetRequest
     * @return success or fail
     */
    @POST
    @Path("/place")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response placeBet (PlaceBetRequest request){
        return bettingService.placeBet(request);
    }
    
    /**
     * Create new bet list
     * @param request placeBetListRequest
     * @return success or fail
     */
    @POST
    @Path("/placeList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response placeBetList (PlaceBetListRequest request){
        return bettingService.placeBetList(request);
    }
    
    /**
     * Generate combination
     * @param roundId
     * @return combination or fail
     */
    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response generateCombination (@QueryParam("round-id") String roundId) {
        return generateService.generateCombination(roundId);
    }
    
    /**
     * View all bet on specific round
     * @param roundId
     * @return list or fail
     */
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response viewBet (@QueryParam("round-id") String roundId){
        return bettingService.viewBet(roundId);
    }
    
    /**
     * Find specific bet on specific round
     * @param combination
     * @param roundId
     * @return betting or fail
     */
    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response findBet (
            @QueryParam("combination") String combination,
            @QueryParam("round-id") String roundId){
        return bettingService.findBet(combination, roundId);
    }
    
    /**
     * Auto generate betting according to 
     * the total number needed
     * @param roundId
     * @param total
     * @return list of betting
     */
    @GET
    @Path("/autobet/{round-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response autoBet (
            @PathParam("round-id") String roundId,
            @QueryParam("total") int total) {
        return bettingService.autoBet(roundId, total);
    }
}
