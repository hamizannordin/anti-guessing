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
import com.hmzn.app.antiguessing.json.PlaceBetListRequest;
import com.hmzn.app.antiguessing.json.PlaceBetRequest;
import com.hmzn.app.antiguessing.util.ResponseHandler;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Response;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Business logic for betting
 * 
 * @author hamizan
 */
public class BettingService {
    
    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    BettingDAO bettingDAO;
    RoundDAO roundDAO;

    /**
     * Initialized DAO
     * @param sessionFactory
     */
    public BettingService(SessionFactory sessionFactory) {
        bettingDAO = new BettingDAO(sessionFactory);
        roundDAO = new RoundDAO(sessionFactory);
    }

    /**
     * Insert new bet
     * @param request placeBetRequest
     * @return success or fail
     */
    public Response placeBet(PlaceBetRequest request) {
        
        if(request == null){
            log.info("Request is null");
            return ResponseHandler.throwResponse(false, "Request is null");
        }
        
        if(request.getCombination() == null || request.getCombination().length() != 8
                || !request.getCombination().matches("[0-9]+")){
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

    /**
     * Find all bet on specific round
     * @param roundId
     * @return list or fail
     */
    public Response viewBet(String roundId) {
        
        List<Betting> listBetting = bettingDAO.getAllBettingByRoundId(roundId);
        
        if(listBetting == null || listBetting.isEmpty()){
            log.info("No betting found for round-id: " + roundId);
            return ResponseHandler.throwResponse(false, "No betting found");
        }
        
        log.info("Betting found! " + listBetting.size());
        return ResponseHandler.throwResponse(true, listBetting.toString());
    }

    /**
     * Find specific bet on specific round
     * @param combination
     * @param roundId
     * @return betting or fail
     */
    public Response findBet(String combination, String roundId) {
        
        if(combination == null || combination.isEmpty() || combination.length() < 8){
            log.info("Combination is null, empty or invalid");
            return ResponseHandler.throwResponse(false, "Combination is null, empty or invalid");
        }
        if(roundId == null || roundId.isEmpty()){
            log.info("Round-id is null or empty");
            return ResponseHandler.throwResponse(false, "Round-id is null or empty");
        }
        
        Betting betting = bettingDAO.getBettingByRoundId(combination.split(""), roundId);
        
        if(betting == null){
            log.info("No betting found");
            return ResponseHandler.throwResponse(false, "No betting found");
        }
        
        log.info("Betting found");
        return ResponseHandler.throwResponse(true, betting.toString());
    }

    /**
     * Inset new bet in list
     * @param request placeBetListRequest
     * @return  success or fail
     */
    public Response placeBetList(PlaceBetListRequest request) {
        if(request == null){
            log.info("Betting list is null");
            return ResponseHandler.throwResponse(false, "Betting list is null");
        }
        if(request.getRoundId() == null || request.getRoundId().isEmpty()){
            log.info("Round id is null, empty or invalid");
            return ResponseHandler.throwResponse(false, "Round id is null, empty or invalid");
        }
        
        List<String> combinationList = request.getCombination();
        
        if(combinationList == null || combinationList.isEmpty()){
            log.info("Betting list is null or empty");
            return ResponseHandler.throwResponse(false, "Betting list is null or empty");
        }
        
        log.info("Total betting to be insert: " + combinationList.size());
        
        int successInsertCount = 0;
        int failedInsertCount = 0;
        
        String failedCombination = "\n\nFailed combination:\n";
        
        for(String combination : combinationList){
            PlaceBetRequest betRequest = new PlaceBetRequest();
            betRequest.setRoundId(request.getRoundId());
            betRequest.setCombination(combination);
            
            Response insertResponse = placeBet(betRequest);
            
            if(insertResponse.getStatus() == 200){
                successInsertCount ++;
            }
            else {
                failedInsertCount ++;
                failedCombination += combination + "\n";
            }
        }
        
        String result = "Finish inserting bet. Success: " + successInsertCount +
                ", Failed: " + failedInsertCount;
        log.info(result);
        return ResponseHandler.throwResponse(true, result + failedCombination);
    }
    
}
