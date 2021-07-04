/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing;

import com.hmzn.app.antiguessing.database.Betting;
import com.hmzn.app.antiguessing.database.Round;
import com.hmzn.app.antiguessing.resource.GenerateCombinationResource;
import com.hmzn.app.antiguessing.resource.PlaceBetResource;
import com.hmzn.app.antiguessing.resource.SetRoundResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 * @author hamizan
 */
public class AntiGuessingApp extends Application<AntiGuessingConfig> {

    private final HibernateBundle<AntiGuessingConfig> hibernate = new HibernateBundle<AntiGuessingConfig>(Betting.class, Round.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AntiGuessingConfig configuration) {
            return configuration.getDatabase();
        }
    };
    
    @Override
    public void initialize(Bootstrap<AntiGuessingConfig> bootstrap) {
        bootstrap.addBundle(hibernate);
    }
    
    @Override
    public void run(AntiGuessingConfig config, Environment env) throws Exception {
        env.jersey().register(new PlaceBetResource(hibernate.getSessionFactory()));
        env.jersey().register(new SetRoundResource(hibernate.getSessionFactory()));
        env.jersey().register(new GenerateCombinationResource(hibernate.getSessionFactory()));
    }
    
    public static void main(String[]args) throws Exception{
        new AntiGuessingApp().run(args);
    }
    
}
