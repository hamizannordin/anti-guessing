/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.database;

import io.dropwizard.hibernate.AbstractDAO;
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
}
