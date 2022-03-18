package com.lyl.play.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 物品基本信息表
 *
 * @since 2021-05-02
 */
@Data
@TableName("flow_config_commodity_basic")
public class FlowConfigCommodityBasic implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * ID
     */
    @TableId(
            type = IdType.AUTO
    )
    private Long id;

    /**
     * 商品频道ID
     */
    @TableField("channel_id")
    private Long channelId;

    /**
     * 商品类型ID
     */
    @TableField("type_id")
    private Long typeId;

    /**
     * 各种语言对应的名字列表,/分隔
     */
    @TableField("cache_names")
    private String cacheNames;

    /**
     * 商品主图地址，不含域名
     */
    @TableField("primary_pic")
    private String primaryPic;

    /**
     * 商品副图地址，不含域名
     */
    @TableField("second_pic")
    private String secondPic;

    /**
     * 商品附件地址，不含域名
     */
    private String attachment;

    /**
     * 对外释放类型，1：商品售卖，2：拍卖
     */
    @TableField("release_type")
    private Integer releaseType;

    /**
     * 对完释放的配置ID，与auction或者commodity是一对一关系，根据release_type决定
     */
    @TableField("release_config_id")
    private Long releaseConfigId;

    /**
     * 排序
     */
    private Integer seq;
}
