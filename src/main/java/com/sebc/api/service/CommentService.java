package com.sebc.api.service;

import com.sebc.api.entity.Comment;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 点评服务
 */
public interface CommentService {

    /**
     * 获取课程对应的点评
     *
     * @param lessonId 课程Id
     * @return 点评信息
     */
    List<Comment> getLessonComment(String lessonId);
}
