/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.service;

import com.hmzn.app.antiguessing.database.Betting;
import com.hmzn.app.antiguessing.database.BettingDAO;
import com.hmzn.app.antiguessing.json.PlaceBetRequest;
import com.hmzn.app.antiguessing.util.ErrorHandler;
import java.util.Date;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hamizan
 */
public class PlaceBetService {
    
    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    BettingDAO bettingDAO;

    public PlaceBetService(SessionFactory sessionFactory) {
        bettingDAO = new BettingDAO(sessionFactory);
    }

    public Response placeBet(PlaceBetRequest request) {
        
        if(request == null){
            log.info("Request is null");
            return ErrorHandler.throwResponse(false, "Request is null");
        }
        
        if(request.getCombination() == null || request.getCombination().length() < 8
                || request.getCombination().matches("\\D")){
            log.info("Combination is invalid");
            return ErrorHandler.throwResponse(false, "Combination is invalid");
        }
        
        Betting betting = new Betting();
        
        String[] combination = request.getCombination().split("");
        
        betting.setDigit_0(Integer.parseInt(combination[0]));
        betting.setDigit_1(Integer.parseInt(combination[1]));
        betting.setDigit_2(Integer.parseInt(combination[2]));
        betting.setDigit_3(Integer.parseInt(combination[3]));
        betting.setDigit_4(Integer.parseInt(combination[4]));
        betting.setDigit_5(Integer.parseInt(combination[5]));
        betting.setDigit_6(Integer.parseInt(combination[6]));
        betting.setDigit_7(Integer.parseInt(combination[7]));
        betting.setInsert_datetime(new Date());
        
        log.info("Placing your bet: " + request.getCombination());
        bettingDAO.create(betting);
        
        return ErrorHandler.throwResponse(true, request.getCombination());
    }
    
}
