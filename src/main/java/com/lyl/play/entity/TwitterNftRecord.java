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
@TableName("twitter_nft_record")
public class TwitterNftRecord implements Serializable {
    private static final long serialVersionUID = 1;

    @TableId(
            type = IdType.AUTO
    )
    private Long id;

    //创建人
    @TableField("mint_user")
    private String mintUser;

    //接受地址
    private String address;

    //类型：0:NFT/1:POSTER/2:Co-NFT
    private int type;

    //铸造内容
    @TableField("nft_content")
    private String nftContent;

    //源文件
    @TableField("source_file_ipfs")
    private String sourceFileIpfs;

    //上传ipfs地址
    @TableField("metadata_ipfs")
    private String metadataIpfs;

    //铸造状态：0创建/1待铸造/2铸造中/3铸造完毕/4铸造失败
    private int status;

    //铸造时间
    @TableField("mint_time")
    private Date mintTime;

    @TableField("transaction_hash")
    private String transactionHash;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    //订单编号：用于区分是否同一批次铸造
    @TableField("order_no")
    private String orderNo;

    //0自己创建的/1别人创建的
    @TableField("creator_tag")
    private int creatorTag;

    @TableField(exist = false)
    private String ohterUser;

    @TableField(exist = false)
    private String ohterAddress;
}
