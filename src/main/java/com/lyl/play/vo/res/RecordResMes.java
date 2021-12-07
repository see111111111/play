package com.lyl.play.vo.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("com_lyl_play_vo_res_RecordResMes")
@Data
public class RecordResMes {

    @ApiModelProperty("唯一标识")
    private Long tokenId;

    @ApiModelProperty("钱包地址")
    private String address;

    @ApiModelProperty("订单号")
    private String orderNo;


}
