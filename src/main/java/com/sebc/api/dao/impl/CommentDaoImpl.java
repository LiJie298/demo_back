package com.sebc.api.dao.impl;

import com.sebc.api.dao.CommentDao;
import com.sebc.api.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 点评数据接口层实现
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private MongoOperations writeMongo;


    @Override
    public List<Comment> getAllCommentByLessonId(String ObjectId) {
        return null;
    }
}
