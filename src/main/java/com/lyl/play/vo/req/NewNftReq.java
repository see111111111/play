package com.lyl.play.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("com_lyl_play_vo_req_NewNftReq")
@Data
public class NewNftReq {

    @ApiModelProperty("接收地址")
    private String address;

    @ApiModelProperty("创建人推特账号")
    private String mintUser;

    @ApiModelProperty("类型：0:NFT/1:POSTER/2:Co-NFT")
    private int type;

    @ApiModelProperty("铸造内容:标题")
    private String name;

    @ApiModelProperty("铸造内容:描述")
    private String description;

    @ApiModelProperty("铸造内容:文件路径")
    private String picturePath;

    @ApiModelProperty("接收人推特账号")
    private String receiveUser;

    @ApiModelProperty("源文件")
    private String sourceFileIpfs;



}
