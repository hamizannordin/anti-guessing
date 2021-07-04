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

/**
 *
 * @author hamizan
 */
public class BettingDAO extends AbstractDAO<Betting> {
    
    public BettingDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public void create (Betting betting){
        persist(betting);
    }
    
    public List<Integer> getDigitAnalysis (String placement, String roundId){
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
            e.printStackTrace();
            return null;
        }
    }
}
