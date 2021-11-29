package com.lyl.play.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("com_lyl_play_vo_req_UpReq")
@Data
public class UpReq {

    @ApiModelProperty("状态")
    private int status;

    @ApiModelProperty("钱包地址")
    private String address;

    @ApiModelProperty("唯一标识")
    private Long tokenId;


}
