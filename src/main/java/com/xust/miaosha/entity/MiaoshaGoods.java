package com.xust.miaosha.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
public class MiaoshaGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀的商品表
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 秒杀价
     */
    private BigDecimal miaoshaPrice;

    private Integer miaoshaStock;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }
    public Integer getMiaoshaStock() {
        return miaoshaStock;
    }

    public void setMiaoshaStock(Integer miaoshaStock) {
        this.miaoshaStock = miaoshaStock;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MiaoshaGoods{" +
            "id=" + id +
            ", goodsId=" + goodsId +
            ", miaoshaPrice=" + miaoshaPrice +
            ", miaoshaStock=" + miaoshaStock +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
        "}";
    }
}
