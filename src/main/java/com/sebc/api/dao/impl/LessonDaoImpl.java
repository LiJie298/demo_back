package com.sebc.api.dao.impl;


import com.sebc.api.dao.LessonDao;
import com.sebc.api.dbutil.MongoClient;
import com.sebc.api.entity.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 课程数据库接口层实现
 */
@Repository
public class LessonDaoImpl implements LessonDao {

    @Autowired
    private MongoOperations writeMongo;

    @Override
    public List<Lesson> getAllLessons() {
        return null;
    }
}
