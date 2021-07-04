/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.database;

import com.hmzn.app.antiguessing.util.ListConverter;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains query related to table BETTING
 * 
 * @author hamizan
 */
public class BettingDAO extends AbstractDAO<Betting> {
    
    Logger log = LoggerFactory.getLogger(getClass().getName());
    
    public BettingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    /**
     * Insert new betting to table
     * @param betting
     */
    public void create (Betting betting){
        persist(betting);
    }
    
    /**
     * Find specific betting in specific round
     * @param digit array by splitting the combination
     * @param roundId 
     * @return one specific betting or null if not exist
     */
    public Betting getBettingByRoundId (String[] digit, String roundId){
        String sql = "SELECT * FROM BETTING WHERE digit_0=:digit0 \n" +
                "AND digit_1=:digit1 AND digit_2=:digit2 \n" +
                "AND digit_3=:digit3 AND digit_4=:digit4 \n" +
                "AND digit_5=:digit5 AND digit_6=:digit6 \n" +
                "AND digit_7=:digit7 \n" +
                "AND round_id=:roundId";
        try {
            Query q = currentSession().createNativeQuery(sql, Betting.class);
            q.setParameter("digit0", digit[0]);
            q.setParameter("digit1", digit[1]);
            q.setParameter("digit2", digit[2]);
            q.setParameter("digit3", digit[3]);
            q.setParameter("digit4", digit[4]);
            q.setParameter("digit5", digit[5]);
            q.setParameter("digit6", digit[6]);
            q.setParameter("digit7", digit[7]);
            q.setParameter("roundId", roundId);
            return (Betting) q.getSingleResult();
        }
        catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
    
    /**
     * Get all betting on the specific round
     * @param roundId
     * @return all betting on the specific round 
     */
    public List<Betting> getAllBettingByRoundId (String roundId){
        String sql = "SELECT * FROM BETTING WHERE round_id=:roundId";
        try {
            Query q = currentSession().createNativeQuery(sql, Betting.class);
            q.setParameter("roundId", roundId);
            return (List<Betting>) q.getResultList();
        }
        catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
    
    /**
     * To count frequency of each value on specific column
     * @param placement or position of the digit; 0,1,2...
     * @param roundId
     * @return value sorted by frequency in ascending order
     */
    public List<Integer> countNumberFrequency (String placement, String roundId){
        String sql = "SELECT digit_" + placement + " FROM BETTING \n" +
                "WHERE round_id=:roundId GROUP BY digit_" + placement +
                " ORDER BY COUNT(*)";
        try {
            Query q = currentSession().createNativeQuery(sql);
            q.setParameter("roundId", roundId);
            List<Integer> result = ListConverter.convertToListString(q.getResultList());
            return result;
        }
        catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
