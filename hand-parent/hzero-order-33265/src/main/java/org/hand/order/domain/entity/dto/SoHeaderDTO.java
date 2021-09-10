package org.hand.order.domain.entity.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 销售订单汇总
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */

@ApiModel("销售订单汇总")
@Data
public class SoHeaderDTO {
    @ApiModelProperty("订单头编号")
    private Long soHeaderId;
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank
    private String orderNumber;
    @ApiModelProperty("公司id")
    private Long companyId;
    @ApiModelProperty("客户ID")
    private Long customerId;

    @ApiModelProperty("公司编码")
    private Long companyNumber;
    @ApiModelProperty(value = "公司名称", required = true)
    @NotBlank
    private String companyName;
    @ApiModelProperty(value = "订单状态", required = true)
    @NotBlank
    private String orderStatus;
    @ApiModelProperty(value = "客户名称", required = true)
    @NotBlank
    private String customerName;
    @ApiModelProperty(value = "订单日期", required = true)
    @NotNull
    private Date orderDate;

    @ApiModelProperty("客户编码")
    private Long customerNumber;
    @ApiModelProperty(value = "订单金额", required = true)
    @NotBlank
    private String orderPrice;


}
