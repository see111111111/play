package com.lyl.play.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品文案表
 *
 * @since 2021-05-02
 */
@Data
@TableName("flow_config_commodity_content")
public class FlowConfigCommodityContent implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * ID
     */
    @TableId(
            type = IdType.AUTO
    )
    private Long id;

    /**
     * 对应的商品id
     */
    @TableField("basic_id")
    private Long basicId;

    /**
     * 语言，TC：繁体，EN：英语
     */
    private String lang;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品简介
     */
    private String introduce;

    /**
     * 商品详情
     */
    private String content;

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
}
