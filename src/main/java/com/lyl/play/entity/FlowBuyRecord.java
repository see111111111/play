package com.lyl.play.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*
* 购买记录表
*
* @since 2021/10/19
* */
@Data
@TableName("flow_buy_record")
public class FlowBuyRecord implements Serializable {

    @TableId(type= IdType.AUTO)
    private Integer id;

    @TableField("flow_basic_id")
    private Long flowBasicId;

    private BigDecimal price;

    @TableField("transaction_hash")
    private String transactionHash;

    @TableField("token_type")
    private String tokenType;

    @TableField("token_id")
    private Integer tokenId;

    @TableField("flow_address")
    private String flowAddress;

    @TableField("order_type")
    private Integer orderType;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
}
