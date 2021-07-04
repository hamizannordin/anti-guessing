/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.service;

import com.hmzn.app.antiguessing.database.BettingDAO;
import com.hmzn.app.antiguessing.util.ResponseHandler;
import java.util.List;
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
    
    int digitLength = 8;
    int digitRange = 10;
    
    public GenerateCombinationService(SessionFactory sessionFactory) {
        bettingDAO = new BettingDAO(sessionFactory);
    }

    public Response generateCombination(String roundId) {
        if(roundId == null || roundId.isEmpty()){
            log.info("Round id is null or empty");
            return ResponseHandler.throwResponse(false, "Round id is null or empty");
        }
        
        String combination = "";
        //boolean randomUse = false;

        for(int i = 0; i < digitLength; i++){
            List<Integer> listDigit = bettingDAO.getDigitAnalysis(Integer.toString(i), roundId);
            if(listDigit != null){
                combination += getLeastBet(listDigit);
            }
            else {
                log.info("Failed to read from database, using random");
                int random = new java.util.Random().nextInt(digitRange);
                combination += Integer.toString(random);
                //randomUse = true;
            }
        }
        log.info("Generated combination: " + combination);
        return ResponseHandler.throwResponse(true, combination);
    }
    
    private String getLeastBet(List<Integer> listDigit){
        if(listDigit.size() < 10){
            log.info("Some number is not betted by anyone, finding the number...");
            
            int fromSmallest = 0;
            int fromBiggest = 9;
            int iterator = 1;
            
            String numberNotBetted = "";
            
            while(true){
                if(listDigit.contains(fromSmallest)){
                    numberNotBetted = Integer.toString(fromSmallest);
                    break;
                }
                if(listDigit.contains(fromBiggest)){
                    numberNotBetted = Integer.toString(fromBiggest);
                    break;
                }
                fromSmallest += iterator;
                fromBiggest -= iterator;
            }
            
            log.info("Number found: " + numberNotBetted);
            return numberNotBetted;
        }
        else {
            log.info("All number are betted, finding the least betted...");
            return Integer.toString(listDigit.get(0));
        }
    }
    
}
