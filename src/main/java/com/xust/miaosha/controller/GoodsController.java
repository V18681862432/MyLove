package com.xust.miaosha.controller;


import com.xust.loginAndregister.entity.MiaoshaUserKey;
import com.xust.loginAndregister.entity.User;
import com.xust.loginAndregister.utils.GetTookenUtil;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;
import com.xust.miaosha.service.impl.GoodsServiceImpl;
import com.xust.miaosha.service.impl.OrderInfoServiceImpl;
import com.xust.miaosha.service.impl.OrderServiceImpl;
import com.xust.properties.exception.CodeMsg;
import com.xust.properties.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
@Controller
@RequestMapping("/miaosha/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;
    /**
     * 秒杀订单
     */
    @Autowired
    private OrderServiceImpl orderService;
    /**
     * 订单
     */
    @Autowired
    private OrderInfoServiceImpl orderInfoService ;

    @Autowired
    RedisService redisService;
    @RequestMapping("/to_list")
    /**
     * 1: QPS:421
     */
    public String list(Model model,  HttpServletRequest request,String tooken){
        String token = GetTookenUtil.getCookieValue(request,"tooken");
        User user = redisService.get(MiaoshaUserKey.token,token,User.class);
        //延长有效期
        redisService.set(MiaoshaUserKey.token, tooken, user);
        model.addAttribute("user",user);
//        List<GoodsVo> goodslist = (List<GoodsVo>) redisService.get(GoodsKey.prefix,"",GoodsVo.class);
////        if (goodslist==null || goodslist.size()==0 ){
////            goodslist = goodsService.getGoodVoList();
////            redisService.set(GoodsKey.prefix,"",goodslist);
////        }
        List<GoodsVo> goodslist = goodsService.getGoodVoList();

        model.addAttribute("goodslist",goodslist);
        return "page/user/login/goods_list";
    }

    @RequestMapping("/to_detail/{goodsid}")
    public String detail(Model model, HttpServletRequest request,@PathVariable("goodsid")Integer goodsid){
        String tooken = GetTookenUtil.getCookieValue(request,"tooken");
        User user = redisService.get(MiaoshaUserKey.token,tooken,User.class);
        //延长有效期
        redisService.set(MiaoshaUserKey.token, tooken, user);
        model.addAttribute("user",user);
        GoodsVo  goodsVo = goodsService.getGoodsVoById(goodsid);
        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0 ;
        int remainSecond = 0;

        if(now < startAt) {
            //秒杀未开始
            miaoshaStatus = 0;
            remainSecond = (int)(now-startAt);
        }else if(now > endAt){
            //秒杀截止
            miaoshaStatus = 2;
            remainSecond = -1;
        }else {
            //秒杀进行中
            miaoshaStatus = 1;
            remainSecond = (int) (endAt - now)/1000;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(remainSecond);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSecond",remainSecond);
        model.addAttribute("starttime",simpleDateFormat.format(goodsVo.getStartDate()));
        model.addAttribute("goodsVo",goodsVo);
        return "page/user/login/goods_detail";
    }

}
