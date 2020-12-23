package com.sebc.api.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @Author lijie
 * @Date 2020/12/21
 * @Desc 配置Mongo链接
 */
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private MongoProperties properties;

    @Override
    protected String getDatabaseName() {
        return properties.getDatabase();
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.credential(MongoCredential.createCredential(properties.getUsername(), getDatabaseName(), properties.getPassword().toCharArray()))
                .applyToClusterSettings(settings -> {
                    if (properties.getAddress() != null) {
                        List<ServerAddress> serverAddressList = new LinkedList<>();
                        properties.getAddress().forEach(m -> {
                            serverAddressList.add(new ServerAddress(m.split(":")[0], Integer.parseInt(m.split(":")[1])));
                        });
                        settings.hosts(serverAddressList);
                    }
                });
    }
}
