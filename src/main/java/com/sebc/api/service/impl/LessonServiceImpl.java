package com.sebc.api.service.impl;

import com.sebc.api.dao.LessonDao;
import com.sebc.api.entity.Lesson;
import com.sebc.api.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 课程服务实现
 */
@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Override
    public List<Lesson> getAllLessons() {
        return null;
    }
}
