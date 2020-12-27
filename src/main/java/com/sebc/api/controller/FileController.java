package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("file")
public class FileController {

    @PostMapping("getSome")
    @ResponseBody
    public ResultVO getSomeThing(){
        return ResultVO.successResult("ok");
    }
}
