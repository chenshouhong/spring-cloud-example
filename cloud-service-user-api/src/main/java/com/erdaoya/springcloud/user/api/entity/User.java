package com.erdaoya.springcloud.user.api.entity;

import lombok.Data;

/**
 * 用户实体
 */
@Data
public class User {
    private Long id;
    private String loginName;
    private String nickName;
    private String password;
    private String mobile;
    private String email;
    private Integer gender;
    private Long registerTime;
    private Long updateTime;
}
