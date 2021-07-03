/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.service;

import com.hmzn.app.antiguessing.database.BettingDAO;
import com.hmzn.app.antiguessing.util.ErrorHandler;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hamizan
 */
public class GenerateCombinationService {

    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    BettingDAO bettingDAO;
    
    public GenerateCombinationService(SessionFactory sessionFactory) {
        bettingDAO = new BettingDAO(sessionFactory);
    }

    public Response generateCombination(String roundId) {
        if(roundId == null || roundId.isEmpty()){
            log.info("Round id is null or empty");
            return ErrorHandler.throwResponse(false, "Round id is null or empty");
        }
        
        return null;
    }
    
}
