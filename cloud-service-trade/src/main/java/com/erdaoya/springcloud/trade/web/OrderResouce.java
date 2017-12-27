package com.erdaoya.springcloud.trade.web;

import com.erdaoya.springcloud.common.exception.code4xx.C404Exception;
import com.erdaoya.springcloud.trade.entity.Order;
import com.erdaoya.springcloud.user.api.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RefreshScope
@RequestMapping("/trade/order")
public class OrderResouce {

    @Value("${testvalue.price}")
    private Double price;

    @Autowired
    UserFeignClient userClient;

    @RequestMapping(method = RequestMethod.GET)
    public Object doGet(@RequestParam Long id) {
        if (id == 1) {
            Order order = new Order();
            order.setId(id);
            order.setCustomerId(1L);
            order.setPrice(price);
            order.setSellerId(2L);
            User user = userClient.getUserById("1");
            System.out.println(user.toString());
            order.setCustomer(user);
            return order;
        } else {
            throw new C404Exception("订单不存在");
        }

    }
}
