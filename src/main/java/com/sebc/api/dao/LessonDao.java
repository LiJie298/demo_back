package com.sebc.api.dao;

import com.sebc.api.entity.Lesson;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 课程dao层
 */
public interface LessonDao {
    /**
     * 获取所有课程
     *
     * @return 课程信息
     */
    List<Lesson> getAllLessons();
}
