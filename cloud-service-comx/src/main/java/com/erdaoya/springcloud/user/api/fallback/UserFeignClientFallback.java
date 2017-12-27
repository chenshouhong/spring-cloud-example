package com.erdaoya.springcloud.user.api.fallback;

import com.erdaoya.springcloud.user.api.client.UserFeignClient;
import com.erdaoya.springcloud.user.api.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luzhiwei on 2017/12/26.
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public User getUserById(@RequestParam(value = "id") String id) {
        User user = new User();
        user.setNickName("getUserById fallback");
        return user;
    }

    @Override
    public void createUser(User User) {
        System.out.println("create User fallback");
    }
}
