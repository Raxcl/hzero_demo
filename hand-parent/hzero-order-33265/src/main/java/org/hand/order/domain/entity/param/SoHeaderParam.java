package org.hand.order.domain.entity.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单头请求参数体
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */

@Data
@ApiModel("订单头查询请求参数体")
public class SoHeaderParam {
    @ApiModelProperty("公司编号")
    private Long companyId;
    @ApiModelProperty("顾客编号")
    private Long customerId;
    @ApiModelProperty("订单编号")
    private String orderNumber;
    @ApiModelProperty("订单状态")
    private String orderStatus;
}
