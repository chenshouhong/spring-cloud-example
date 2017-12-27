package com.erdaoya.springcloud.user.api.web;

import com.erdaoya.springcloud.user.api.client.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 2017/1/13
 * @author erdaoya
 * @since 1.0
 */
@RestController
@RequestMapping("/ext")
public class UserController {
    @Autowired
    private UserFeignClient feignClient;
    @Autowired
    Environment env;
    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    public Object getUserByFeign(String id) {
        long userId = Long.valueOf(id);
        Map<String, Object> response = new HashMap<>();
        response.put("user", feignClient.getUserById(id));
        response.put("comx host", env.getProperty("spring.cloud.client.ipAddress")+":"+env.getProperty("server.port" ));
        return response;
    }

}
