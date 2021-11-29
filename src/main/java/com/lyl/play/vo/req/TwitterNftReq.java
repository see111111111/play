package com.lyl.play.vo.req;

import com.lyl.play.vo.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("com_lyl_play_vo_req_TwitterNftReq")
@Data
public class TwitterNftReq extends PageQuery {

    @ApiModelProperty("铸造账号")
    private String mintUser;

    @ApiModelProperty("铸造类型")
    private int type;

    @ApiModelProperty("订单号")
    private String orderNo;


}
