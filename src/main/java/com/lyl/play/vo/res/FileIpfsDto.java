package com.lyl.play.vo.res;


import lombok.Data;

import java.io.Serializable;

@Data
public class FileIpfsDto implements Serializable {
    /**
     * 文件名
     */
    private String name;
    /**
     * 作评名称
     */
    private String name1;
    private String name2;
    private String name3;
    /**
     * 文件描述
     */
    private String description1;
    private String description2;
    private String description3;
    /**
     * 文件类型
     */
    private String category;
    /**
     * 地址
     */
    private String externalUrl;
    /**
     * commodityTypeNft关联数据
     */
    private int commodityTypeNftId;

    /**
     * 返回值
     */
    private String metaDataIpfs;
    private String sourceIpfs;

    /**
     * 没有模板，新建记录数据
     */

}
