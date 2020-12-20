package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import com.sebc.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "hello")
    @ResponseBody
    public ResultVO<String> hello(@RequestParam("name") String username) {
        return ResultVO.successResult(username);
    }

    @PostMapping(value = "login")
    @ResponseBody
    public ResultVO<String> userLogin(@RequestParam("name") String username, @RequestParam("password") String userpassword) {
        return ResultVO.successResult(username);
    }

    @PostMapping(value = "regist", consumes = "application/json")
    @ResponseBody
    public ResultVO<String> userRegist(@RequestBody String user) {
        if (user == null || user.length() == 0) {
            return ResultVO.paramErrorResult();
        }
        return ResultVO.successResult(user);
    }
}
