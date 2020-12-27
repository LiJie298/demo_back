package com.sebc.api.service.impl;

import com.sebc.api.dbutil.MongoClient;
import com.sebc.api.entity.CurrentUser;
import com.sebc.api.service.VideoFileService;
import com.sebc.api.util.LogUtil;
import com.sebc.api.util.UserUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VideoFileServiceImpl implements VideoFileService {
    @Autowired
    private MongoClient mongoClient;

    @Override
    public ObjectId saveFile(String fileName, InputStream inputStream) {
        ObjectId id = null;
        try {
            CurrentUser currentUser = UserUtils.getCurrentUser();
            System.out.println(currentUser.getUserName());
            id = mongoClient.defaultGFSBucket().uploadFromStream(fileName, inputStream);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LogUtil.error("文件关闭失败,文件名称：" + fileName, e);
            }
        }
        return id;
    }
}
