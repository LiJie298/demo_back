package com.sebc.api.dbutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author lijie
 * @Date 2020/12/21
 * @Desc mongo链接池
 */
@Component
public class MongoClient {

    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Bean
    public MongoOperations writeMongo() {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
