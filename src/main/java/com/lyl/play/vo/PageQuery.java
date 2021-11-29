package com.lyl.play.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

/***
 * @author wanguelin
 * 分页查询参数
 */
@Data
@ApiModel
public class PageQuery {

    @ApiModelProperty(value = "当前页", position = 1)
    private int current = 1;

    @ApiModelProperty(value = "每页条目数", position = 2)
    @Min(value = 1, message = "每页最大条目数不能低于1")
    private int pageSize = 10;

    @ApiModelProperty(value = "排序列表", position = 3)
    private List<OrderItem> orders;

    public int getPageSize() {
        if (this.pageSize <= 0) {
            return 10;
        }
        return pageSize;
    }

}
