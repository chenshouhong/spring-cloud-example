package com.erdaoya.springcloud.trade.web;

import com.erdaoya.springcloud.user.api.UserServiceRemoteApi;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 2016/12/25 21:43
 * @author erdaoya
 */
@FeignClient(value = "cloud-service-user")
public interface UserFeignClient extends UserServiceRemoteApi {


}
