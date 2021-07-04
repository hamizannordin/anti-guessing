/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.service;

import com.hmzn.app.antiguessing.database.Round;
import com.hmzn.app.antiguessing.database.RoundDAO;
import com.hmzn.app.antiguessing.json.SetRoundRequest;
import com.hmzn.app.antiguessing.util.ResponseHandler;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Business logic related to round
 * 
 * @author hamizan
 */
public class RoundService {

    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    RoundDAO roundDAO;
    
    /**
     * Initialized DAO
     * @param sessionFactory
     */
    public RoundService(SessionFactory sessionFactory) {
        roundDAO = new RoundDAO(sessionFactory);
    }

    /**
     * Create new round
     * @param request setRoundRequest
     * @return success or fail
     */
    public Response createRound(SetRoundRequest request) {
        
        if(request == null || request.getRoundName() == null
                || request.getRoundName().isEmpty()){
            log.info("Request is null");
            return ResponseHandler.throwResponse(false, "Request is null");
        }
        
        Round round = new Round();
        round.setRound_name(request.getRoundName());
        round.setStatus(true);
        
        log.info("Create new round: " + request.getRoundName());
        int roundId = roundDAO.create(round);
        
        return ResponseHandler.throwResponse(true, request.getRoundName() + " created with id: " + roundId);
    }
    
}
