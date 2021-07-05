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
 * Business logic to generate combination
 * 
 * @author hamizan
 */
public class GenerateService {

    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    BettingDAO bettingDAO;
    
    // combination length
    int digitLength = 8;
    // number from 0-9
    int digitRange = 10;
    
    /**
     * Initialized DAO
     * @param sessionFactory
     */
    public GenerateService(SessionFactory sessionFactory) {
        bettingDAO = new BettingDAO(sessionFactory);
    }

    /**
     * Process combination
     * @param roundId
     * @return combination or fail
     */
    public Response generateCombination(String roundId) {
        if(roundId == null || roundId.isEmpty()){
            log.info("Round id is null or empty");
            return ResponseHandler.throwResponse(false, "Round id is null or empty");
        }
        
        String combination = "";
        int randomUse = 0;

        for(int i = 0; i < digitLength; i++){
            
            List<Integer> listDigit = bettingDAO.countNumberFrequency(Integer.toString(i), roundId);
            
            if(listDigit != null){
                
                if(listDigit.isEmpty()){
                    log.info("No betting found for this round");
                    return ResponseHandler.throwResponse(false, "No betting found for round: " + roundId);
                }
                
                combination += getLeastNumberChosen(listDigit);
            }
            else {
                log.info("Failed to read from database, using random");
                int random = new java.util.Random().nextInt(digitRange);
                combination += Integer.toString(random);
                randomUse++;
            }
        }
        
        //check if random are used more than 2 times
        if(randomUse > 2){
            log.info("Too much random use, consider retry");
            return ResponseHandler.throwResponse(false, "Generate error. Please retry.");
        }
        
        log.info("Generated combination: " + combination);
        return ResponseHandler.throwResponse(true, combination);
    }
    
    /**
     * Find rare or unused number from the betting result
     * @param listDigit from database query
     * @return a number
     */
    private String getLeastNumberChosen(List<Integer> listDigit){
        
        /* length is 10 because that is the total number starts from 0-9,
           if listDigit less than 10, means there is/are unused number   */
        
        if(listDigit.size() < 10){
            log.info("Some number is not betted by anyone, finding the number...");
            
            int smallestPossibleNumber = 0;
            int biggestPossibleNumber = 9;
            int iterator = 1;
            
            String numberNotBetted = "";
            
            /* start checking the unavailable number from the list with both
               smallest and biggest number.
            
               if smallestPossibleNumber / biggestPossibleNumber is on the list, continue loop.
               smallestPossibleNumber increase by 1, 
               biggestPossibleNumber decrease by 1.
            
               if smallestPossibleNumber / biggestPossibleNumber is not on the list, 
               return either smallestPossibleNumber or biggestPossibleNumbers */
            
            while(true){
                if(!listDigit.contains(smallestPossibleNumber)){
                    numberNotBetted = Integer.toString(smallestPossibleNumber);
                    break;
                }
                if(!listDigit.contains(biggestPossibleNumber)){
                    numberNotBetted = Integer.toString(biggestPossibleNumber);
                    break;
                }
                smallestPossibleNumber += iterator;
                biggestPossibleNumber -= iterator;
            }
            
            log.info("Number found: " + numberNotBetted);
            return numberNotBetted;
        }
        else {
            log.info("All number are betted, finding the least betted...");
            
            /* query already sort the number frequency in ascending order.
               hence, the first index of the list is the rarely used number */
            
            return Integer.toString(listDigit.get(0));
        }
    }
    
}
