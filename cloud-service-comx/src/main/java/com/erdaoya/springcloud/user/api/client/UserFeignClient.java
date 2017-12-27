package com.erdaoya.springcloud.user.api.client;


import com.erdaoya.springcloud.user.api.UserServiceRemoteApi;
import com.erdaoya.springcloud.user.api.fallback.UserFeignClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(value = "cloud-service-user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient extends UserServiceRemoteApi {

}


