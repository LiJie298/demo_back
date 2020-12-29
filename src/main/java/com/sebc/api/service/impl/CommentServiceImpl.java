package com.sebc.api.service.impl;

import com.sebc.api.dao.CommentDao;
import com.sebc.api.entity.Comment;
import com.sebc.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 点评服务实现
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public List<Comment> getLessonComment(String lessonId) {
        return commentDao.getAllCommentByLessonId(lessonId);
    }
}
