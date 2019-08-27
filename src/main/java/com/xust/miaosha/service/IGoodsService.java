package com.xust.miaosha.service;

import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.MiaoshaGoods;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
public interface IGoodsService  {
    public List<GoodsVo> getGoodVoList();
    public GoodsVo getGoodsVoById(long id);
    public boolean reduceStock(GoodsVo goodsVo);
}
