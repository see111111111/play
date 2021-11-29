package com.lyl.play.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * @author wanguelin
 */
@Data
@ApiModel
public class OrderItem {
    @ApiModelProperty("排序字段名")
    private String column;
    @ApiModelProperty("排序类型")
    private boolean asc = true;
}
