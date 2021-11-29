package com.lyl.play.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("com_lyl_play_vo_req_AddressReq")
@Data
public class AddressReq {

    @ApiModelProperty("当前用户")
    private String mintUser;
    @ApiModelProperty("钱包地址")
    private String address;
}
