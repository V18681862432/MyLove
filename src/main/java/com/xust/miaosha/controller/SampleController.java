package com.xust.miaosha.controller;

import com.xust.properties.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Victor
 * @create: 2019-08-25 22:21
 **/
@Controller
@RequestMapping("/rabbitmq")
public class SampleController {
    @Autowired
    MQSender sender;

//    @RequestMapping("/mqsend")
//    @ResponseBody
//    public String send(){
//        sender.send("hello,rabbit");
//        return "success";
//    }
//
//    @RequestMapping("/mqsend/topic")
//    @ResponseBody
//    public String sendTopic(){
//        sender.sendTopic("hello,rabbit");
//        return "success";
//    }
}
