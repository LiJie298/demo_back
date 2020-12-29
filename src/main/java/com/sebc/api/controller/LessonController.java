package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import com.sebc.api.entity.Lesson;
import com.sebc.api.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 课程访问层
 */
@Controller
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    /**
     *
     * @return
     */
    @PostMapping("/getAllLessons")
    @ResponseBody
    public ResultVO findAllLessons(){
        List<Lesson> allLessons = lessonService.getAllLessons();
        return ResultVO.successResult(allLessons);
    }
}

