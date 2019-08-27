package com.xust.miaosha.service.impl;

import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.MiaoshaGoods;
import com.xust.miaosha.mapper.GoodsMapper;
import com.xust.miaosha.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
@Service
public class GoodsServiceImpl  implements IGoodsService {
    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<GoodsVo> getGoodVoList() {
        return mapper.getGoodVoList();
    }

    @Override
    public GoodsVo getGoodsVoById(long id) {
        return mapper.getGoodsVoById(id);
    }

    @Override
    public boolean reduceStock(GoodsVo goodsVo) {
        Long goodsid = goodsVo.getId().longValue();
        int stock = goodsVo.getMiaoshaStock();
        int res = mapper.reduceStock(stock-1,goodsid);
        return res>0;
    }


}
