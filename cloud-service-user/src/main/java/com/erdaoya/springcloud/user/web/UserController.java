package com.erdaoya.springcloud.user.web;

import com.erdaoya.springcloud.common.exception.code4xx.C404Exception;
import com.erdaoya.springcloud.user.api.UserServiceRemoteApi;
import com.erdaoya.springcloud.user.api.entity.User;
import com.erdaoya.springcloud.user.dao.UserDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController implements UserServiceRemoteApi{
    @Autowired
    private UserDao userDao;

    @Autowired
    Environment env;

    @ApiOperation(value = "get userInfo by id", notes = "")
    @ApiImplicitParam(name = "id", value = "user id ", required = true, paramType = "query")
    @Override
    public User getUserById(String id) {
        long userId = Long.valueOf(id);
        User user = userDao.selectUserById(userId);
        if(user == null){
            throw new C404Exception("user not found");
        }
        String host = env.getProperty("spring.cloud.client.ipAddress")+":"+env.getProperty("server.port" );
        user.setNickName(host);
        return user;
    }

    @ApiOperation(value = "create userInfo", notes = "")
    @ApiImplicitParam(name = "user", value = "user vo ", required = true, dataType = "User")
    @Override
    public void createUser(User User) {
        userDao.insertUser(User);
    }
}
