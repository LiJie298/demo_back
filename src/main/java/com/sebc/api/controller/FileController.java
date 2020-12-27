package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import com.sebc.api.service.UserService;
import com.sebc.api.service.VideoFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    private VideoFileService videoFileService;

    @PostMapping("getSome")
    @ResponseBody
    public ResultVO getSomeThing() {
        return ResultVO.successResult("ok");
    }

    @PostMapping("uploadFile")
    @ResponseBody
    public ResultVO saveUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || StringUtils.isBlank(multipartFile.getName())) {
            return ResultVO.paramErrorResult();
        }
        String fileName = multipartFile.getName();
        Object id = videoFileService.saveFile(fileName, multipartFile.getInputStream());
        return id == null ? ResultVO.serverErrorResult() : ResultVO.successResult(id.toString());
    }
}
