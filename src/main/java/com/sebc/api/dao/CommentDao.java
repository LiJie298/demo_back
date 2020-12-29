package com.sebc.api.dao;

import com.sebc.api.entity.Comment;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 点评Dao
 */
public interface CommentDao {

    List<Comment> getAllCommentByLessonId(String ObjectId);
}
