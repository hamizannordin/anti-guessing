/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.service;

import com.hmzn.app.antiguessing.database.Betting;
import com.hmzn.app.antiguessing.database.BettingDAO;
import com.hmzn.app.antiguessing.database.Round;
import com.hmzn.app.antiguessing.database.RoundDAO;
import com.hmzn.app.antiguessing.json.PlaceBetRequest;
import com.hmzn.app.antiguessing.util.ResponseHandler;
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
    RoundDAO roundDAO;

    public PlaceBetService(SessionFactory sessionFactory) {
        bettingDAO = new BettingDAO(sessionFactory);
        roundDAO = new RoundDAO(sessionFactory);
    }

    public Response placeBet(PlaceBetRequest request) {
        
        if(request == null){
            log.info("Request is null");
            return ResponseHandler.throwResponse(false, "Request is null");
        }
        
        if(request.getCombination() == null || request.getCombination().length() < 8
                || request.getCombination().matches("\\D")){
            log.info("Combination is invalid");
            return ResponseHandler.throwResponse(false, "Combination is invalid");
        }
        
        if(request.getRoundId() == null || request.getRoundId().isEmpty()){
            log.info("Round id is null or empty");
            return ResponseHandler.throwResponse(false, "Round id is null or empty");
        }
        
        Round round = roundDAO.findByRoundId(request.getRoundId());
        
        if(round == null){
            log.info("Round not found");
            return ResponseHandler.throwResponse(false, "Round not found");
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
        betting.setRound(round);
        
        log.info("Placing your bet: " + request.getCombination());
        bettingDAO.create(betting);
        
        return ResponseHandler.throwResponse(true, request.getCombination());
    }
    
}
