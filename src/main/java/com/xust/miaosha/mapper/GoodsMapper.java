package com.xust.miaosha.mapper;

import com.xust.miaosha.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.MiaoshaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
@Component
public interface GoodsMapper extends BaseMapper<Goods> {
    @Select({"select g.*,mg.miaosha_stock, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id"})
    public List<GoodsVo> getGoodVoList();

    @Select("select g.*,mg.miaosha_stock, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoById(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set miaosha_stock=#{miaoshaStock} where goods_id=#{goodsId}")
    public int reduceStock(@Param("miaoshaStock") int miaoshaStock,@Param("goodsId") Long goodsId);
}
