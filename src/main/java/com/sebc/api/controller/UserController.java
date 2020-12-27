package com.sebc.api.controller;

import com.sebc.api.VO.ResultVO;
import com.sebc.api.entity.User;
import com.sebc.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(value = "hello")
    public ResultVO<String> hello(@RequestParam("name") String username) {
        return ResultVO.successResult(username);
    }


    @ResponseBody
    @RequestMapping(value = "register", consumes = "application/json")
    public ResultVO<String> userRegister(@RequestBody User user) {
        if (user != null) {
            User user1 = userService.createUser(user);
            return ResultVO.successResult(user1.getId().toString());
        }
        return ResultVO.paramErrorResult();
    }

    @GetMapping("deleteUser")
    @ResponseBody
    public ResultVO delUser(@PathParam("id") String id) {
        userService.deleteUserById(id);
        return ResultVO.successResult("delete");
    }
}
