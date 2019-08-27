package com.xust.miaosha.controller;

import com.xust.loginAndregister.entity.*;
import com.xust.loginAndregister.utils.GetTookenUtil;
import com.xust.miaosha.entity.Goods;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;
import com.xust.miaosha.service.impl.GoodsServiceImpl;
import com.xust.miaosha.service.impl.OrderInfoServiceImpl;
import com.xust.miaosha.service.impl.OrderServiceImpl;
import com.xust.properties.exception.CodeMsg;
import com.xust.properties.rabbitmq.MQSender;
import com.xust.properties.rabbitmq.MiaoShaMessage;
import com.xust.properties.redis.RedisService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Victor
 * @create: 2019-08-25 23:12
 **/
@Controller
@RequestMapping("/miaosha")
public class MiaoShaController  implements InitializingBean {

    @Autowired
    RedisService redisService;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    OrderInfoServiceImpl orderInfoService;
    @Autowired
    MQSender sender;


    //@AccessLimit(seconds=5,maxCount=5,needLogin=true)
    @Transactional
    @RequestMapping("/do_miaosha/{goodsid}")
    public String Do_miaosha(Model model, HttpServletRequest request, @PathVariable("goodsid") Integer goodsid) throws Exception {
        String tooken = GetTookenUtil.getCookieValue(request, "tooken");
        User user = redisService.get(MiaoshaUserKey.token, tooken, User.class);
        //延长有效期
        redisService.set(MiaoshaUserKey.token, tooken, user);
        model.addAttribute("user", user);
        if (user == null) {
            model.addAttribute("errmsg", CodeMsg.USER_NULL);
            return "page/user/login";
        }
        model.addAttribute("user", user);

        /**
         * 访问限制：10秒内限制登录3次
         */
        String uri = request.getRequestURI();
        String key = uri + "" + user.getId();
        Integer count = redisService.get(AccessKey.ACCESS,key,Integer.class);
        if (count == null) {
            redisService.set(AccessKey.ACCESS,key,1);
        }else if( count < 3) {
            redisService.increase(AccessKey.ACCESS,key);
        }else {
            model.addAttribute("errmsg",CodeMsg.MIAOSHA_LIMIT);
            return "page/user/login/miaosha_failed";
        }

        Order order = orderService.getMiaoshaOrderByUserId(user.getId(),goodsid);
        if (order!=null) {
            model.addAttribute("errmsg",CodeMsg.ISMIAOSHA);
            return "page/user/login/miaosha_failed";
        }

        else{
            //入队
            MiaoShaMessage miaoShaMessage = new MiaoShaMessage();
            miaoShaMessage.setUser(user);
            miaoShaMessage.setGoodsid(goodsid);
            sender.sendMiaoshaMessage(miaoShaMessage);
        }
       /*
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsid);
        int stock = goodsVo.getMiaoshaStock();
        if(stock <= 0){
            model.addAttribute("errmsg", CodeMsg.STOCK_EMPTY);
            return "page/user/login/miaosha_failed";
        }
        //判断是否秒杀到了
        Order order = orderService.getMiaoshaOrderByUserId(user.getId(),goodsid);
        if (order==null){
            //减库存 写入秒杀订单 写入订单
            goodsService.reduceStock(goodsVo);
            OrderInfo orderInfo = orderInfoService.miaoshaAdd(user,goodsVo);
            model.addAttribute("orderInfo",orderInfo);
            model.addAttribute("goodsVo",goodsVo);
            return "page/user/login/order_detail";
        }else {
            model.addAttribute("errmsg",CodeMsg.ISMIAOSHA);
            return "page/user/login/miaosha_failed";
        }

     */

        order = redisService.get(OrderKey.is_Miaosha,user.getId()+"",Order.class);
        System.out.println(order);
        if (order == null){
            model.addAttribute("errmsg",CodeMsg.MIAOSHA_FAILED);
            return "page/user/login/miaosha_failed";
        }else {
            OrderInfo orderInfo = redisService.get(OrderKey.Order,user.getId()+"",OrderInfo.class);
            GoodsVo goodsVo =  goodsService.getGoodsVoById(goodsid);
            System.out.println(orderInfo);
            model.addAttribute("orderInfo",orderInfo);
            System.out.println(goodsVo);
            model.addAttribute("goodsVo",goodsVo);
            return "page/user/login/order_detail";
        }
    }

    /**
     * 系统初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVoList = goodsService.getGoodVoList();
        if(goodsVoList == null){
            return ;
        }
        for (GoodsVo goods:goodsVoList){
            redisService.set(GoodsKey.Good_Stock,goods.getId()+"",goods.getMiaoshaStock());
        }
    }
}
