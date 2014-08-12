package com.stagegage.festivalService.repository;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 8/9/14.
 *
 * @author Scott Hendrickson
 */
@Component
public class MongoConfig {

    private MongoClient client;
    private List<ServerAddress> servers;

    public MongoConfig() {

        servers = new ArrayList<ServerAddress>();

        // we can get fancy with this later
        try {
            servers.add(new ServerAddress("104.131.137.204"));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //client = new MongoClient(servers);

        try {
            client = new MongoClient("104.131.137.204");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        createDB();
    }


    @PostConstruct
    public @Bean DB createDB() {
        DB db = client.getDB("festivalDB");

        if (db == null) {
            System.out.print("Could not get the festivaDB");
            return null;
        } else {
            return db;
        }
    }
}
