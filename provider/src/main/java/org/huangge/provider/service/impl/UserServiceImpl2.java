package org.huangge.provider.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.huangge.service.UserService;
import org.springframework.stereotype.Service;

@DubboService(version = "2.0")
@Service
public class UserServiceImpl2 implements UserService {


    @Override
    public String getUserName() {
        return "黄哥2.0";
    }
}
