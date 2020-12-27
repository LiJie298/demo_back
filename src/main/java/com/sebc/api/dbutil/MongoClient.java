package com.sebc.api.dbutil;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author lijie
 * @Date 2020/12/21
 * @Desc mongo链接池
 */
@Component
public class MongoClient {

    private static final String DEFAULT_BUCKET_NAME = "my_video";

    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Bean
    public MongoOperations writeMongo() {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    /**
     * 获取存储名字
     *
     * @param bucketName 视频存储bucket
     * @return GridFSBucket
     */
    public GridFSBucket gFSBucket(String bucketName) {
        bucketName = StringUtils.isBlank(bucketName) ? DEFAULT_BUCKET_NAME : bucketName;
        return GridFSBuckets.create(mongoDatabaseFactory.getMongoDatabase(), bucketName);
    }

    /**
     * 获取存储名字
     *
     * @return GridFSBucket
     */
    public GridFSBucket defaultGFSBucket() {
        return gFSBucket(null);
    }
}
