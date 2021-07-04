/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.database;

import io.dropwizard.hibernate.AbstractDAO;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains query related to table ROUND
 * 
 * @author hamizan
 */
public class RoundDAO extends AbstractDAO<Round> {
    
    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    public RoundDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    /**
     * Insert new round into table
     * @param round
     */
    public void create (Round round){
        persist(round);
    }

    /**
     * Find specific round by id
     * @param roundId
     * @return round
     */
    public Round findByRoundId(String roundId) {
        Round round;
        String sql = "SELECT * FROM ROUND WHERE round_id=:roundId";
        try {
            Query q = currentSession().createNativeQuery(sql, Round.class);
            q.setParameter("roundId", roundId);
            round = (Round) q.getSingleResult();
        }
        catch(Exception e){
            log.error(e.getMessage());
            round = null;
        }
        return round;
    }
}
