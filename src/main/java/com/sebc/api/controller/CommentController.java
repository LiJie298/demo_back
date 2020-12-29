package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import com.sebc.api.entity.Comment;
import com.sebc.api.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author lijie
 * @Date 2020/12/29
 * @Desc 点评访问层
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping("")
    public ResultVO getLessonComment(@RequestParam("lessonId") String lessonId) {
        if (StringUtils.isBlank(lessonId)) {
            return ResultVO.paramErrorResult();
        }
        List<Comment> comments = commentService.getLessonComment(lessonId);
        return ResultVO.successResult(comments);
    }
}
