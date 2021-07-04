/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.database;

import io.dropwizard.hibernate.AbstractDAO;
import javax.persistence.Query;
import org.hibernate.SessionFactory;

/**
 *
 * @author hamizan
 */
public class RoundDAO extends AbstractDAO<Round> {
    
    public RoundDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public void create (Round round){
        persist(round);
    }

    public Round findByRoundId(String roundId) {
        Round round;
        String sql = "SELECT * FROM ROUND WHERE round_id=:roundId";
        try {
            Query q = currentSession().createNativeQuery(sql, Round.class);
            q.setParameter("roundId", roundId);
            round = (Round) q.getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
            round = null;
        }
        return round;
    }
}
