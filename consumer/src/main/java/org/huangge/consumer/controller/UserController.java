package org.huangge.consumer.controller;


import org.apache.dubbo.config.annotation.DubboReference;
import org.huangge.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/consumer")
public class UserController {

    @DubboReference(version = "2.0")
    private UserService userService;

    @GetMapping(value = "/getUserName")
    public String getUserName() {
        String userName = userService.getUserName();
        return userName;
    }

    @GetMapping(value = "/insertOrder")
    public String insertOrder() {
        String userName = userService.getUserName();
        return userName;
    }

}
