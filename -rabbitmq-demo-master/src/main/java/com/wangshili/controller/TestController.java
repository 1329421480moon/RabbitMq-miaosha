package com.wangshili.controller;

import com.wangshili.dao.KucunDao;
import com.wangshili.pojo.Kucun;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 使用jemeter模拟并发，超过100就行，因为库存是100
 *
 * @author Administrator
 */
@RestController
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //模拟用户id
    private static Integer userId = 0;

    @RequestMapping("/miaosha")
    public String miaosha() {

        userId++;//模拟用户id

        //发布消息到消息队列中
        rabbitTemplate.convertAndSend("miaosha", userId);

        return "ok" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd"));
    }
}
