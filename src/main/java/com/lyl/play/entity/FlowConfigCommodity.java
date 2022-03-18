package com.lyl.play.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 *
 * @since 2021-10-15
 */
@Data
@TableName("flow_config_commodity")
public class FlowConfigCommodity implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * ID
     */
    @TableId(
            type = IdType.AUTO
    )
    private Long id;

    /**
     * 物品基本信息表ID
     */
    @TableField("basic_id")
    private Long basicId;

    /**
     * USDT价格，和美元1:1，直销专用
     */
    private BigDecimal price;

    /**
     * 港币价格，直销专用
     */
    @TableField("hkd_price")
    private BigDecimal hkdPrice;

    /**
     * 一次可购买数量限制，null代表不限制，直销专用
     */
    @TableField("once_count_limit")
    private Integer onceCountLimit;

    /**
     * 一个用户可购买数量限制，null代表不限制，直销专用
     */
    @TableField("one_user_count_limit")
    private Integer oneUserCountLimit;

    /**
     * 上架时间，不设置则需要手动上架，直销专用
     */
    @TableField("on_line_time")
    private Date onLineTime;

    /**
     * 下线时间，不设置则需要手动下架，直销专用
     */
    @TableField("down_line_time")
    private Date downLineTime;

    /**
     * 售卖开始时间，直销专用
     */
    @TableField("sale_start_time")
    private Date saleStartTime;

    /**
     * 售卖结束时间，直销专用
     */
    @TableField("sale_end_time")
    private Date saleEndTime;

    /**
     * 锁定数量，下单一次，直销专用
     */
    @TableField("sold_count")
    private Integer soldCount;

    /**
     * 状态，0：下架，1：上架，100：垃圾箱（已经有出售记录的商品禁止删除），直销专用
     */
    private Integer status;

    /**
     * 起始版号
     */
    @TableField("start_edition")
    private Integer startEdition;
    /**
     * 结束版号
     */
    @TableField("end_edition")
    private Integer endEdition;
    /**
     * 库存
     */
    private Integer storage;

    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField("global_storage")
    private Integer globalStorage;

    private String type;
}
