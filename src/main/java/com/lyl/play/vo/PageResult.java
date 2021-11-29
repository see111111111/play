package com.lyl.play.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/***
 * @author wanguelin
 */
@Data
@ApiModel
public class PageResult<T> {

    @ApiModelProperty(value = "总数", position = 1)
    private long total;

    @ApiModelProperty(value = "每页显示数", position = 2)
    private long size;

    @ApiModelProperty(value = "总页数", position = 3)
    private long pages;

    @ApiModelProperty(value = "当前页", position = 4)
    private long current;

    @ApiModelProperty(value = "结果集", position = 6)
    private List<T> records;
}
