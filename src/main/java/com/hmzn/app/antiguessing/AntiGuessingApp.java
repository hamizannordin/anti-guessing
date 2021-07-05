/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing;

import com.hmzn.app.antiguessing.database.Betting;
import com.hmzn.app.antiguessing.database.Round;
import com.hmzn.app.antiguessing.resource.BettingResource;
import com.hmzn.app.antiguessing.resource.RoundResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Register resource, initialized database session and run the app
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
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }
    
    /**
     * Register resource and pass the database session
     * @param config from AntiGuessingConfig
     * @param env
     * @throws Exception
     */
    @Override
    public void run(AntiGuessingConfig config, Environment env) throws Exception {
        env.jersey().register(new BettingResource(hibernate.getSessionFactory()));
        env.jersey().register(new RoundResource(hibernate.getSessionFactory()));
    }
    
    /**
     * Run the app
     * @param args
     * @throws Exception
     */
    public static void main(String[]args) throws Exception{
        new AntiGuessingApp().run(args);
    }
    
}
