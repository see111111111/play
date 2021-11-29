package com.lyl.play.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 推特账户信息
 *
 * @since 2021-11-24
 */
@Data
@TableName("twitter_account")
public class TwitterAccount implements Serializable {
    private static final long serialVersionUID = 1;

    @TableId(
            type = IdType.AUTO
    )
    private Long id;

    /**
     * 推特唯一标识
     */
    @TableField("twitter_no")
    private String twitterNo;

    /**
     * 推特绑定钱包
     */
    private String address;

    /**
     * 作品名，缓存作用
     */
    @TableField("create_date")
    private Date createDate;
}
