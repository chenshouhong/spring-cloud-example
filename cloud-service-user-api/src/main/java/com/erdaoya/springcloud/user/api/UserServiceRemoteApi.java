package com.erdaoya.springcloud.user.api;

import com.erdaoya.springcloud.user.api.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


public interface UserServiceRemoteApi {

    @RequestMapping(value = "user/getUserById",method = RequestMethod.GET)
    public User getUserById(@RequestParam(value = "id") String id);

    @RequestMapping(value="user/createUser", method = RequestMethod.POST)
    public void createUser(User User);
}
