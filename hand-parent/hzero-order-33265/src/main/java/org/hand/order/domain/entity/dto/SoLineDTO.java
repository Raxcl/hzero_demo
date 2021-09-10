package org.hand.order.domain.entity.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单行汇总
 *
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */

@ApiModel("订单行信息汇总")
@Data
public class SoLineDTO {
    @ApiModelProperty("订单行ID（主键）")
    @GeneratedValue
    private Long soLineId;
    @ApiModelProperty(value = "订单头ID", required = true)
    @NotNull
    private Long soHeaderId;
    @ApiModelProperty(value = "行号", required = true)
    @NotNull
    private Long lineNumber;
    @ApiModelProperty(value = "产品ID", required = true)
    @NotNull
    private Long itemId;
    @ApiModelProperty(value = "数量", required = true)
    @NotNull
    private BigDecimal orderQuantity;
    @ApiModelProperty(value = "产品单位", required = true)
    @NotBlank
    private String orderQuantityUom;
    @ApiModelProperty(value = "销售单价", required = true)
    @NotNull
    private BigDecimal unitSellingPrice;
    @ApiModelProperty(value = "备注")
    private String description;
    @ApiModelProperty(value = "附加信息1")
    private String addition1;
    @ApiModelProperty(value = "附加信息2")
    private String addition2;
    @ApiModelProperty(value = "附加信息3")
    private String addition3;
    @ApiModelProperty(value = "附加信息4")
    private String addition4;
    @ApiModelProperty(value = "附加信息5")
    private String addition5;
    @ApiModelProperty(value = "物料编码", required = true)
    private String itemCode;
    @ApiModelProperty(value = "物料单位", required = true)
    private String itemUom;
    @ApiModelProperty(value = "物料描述", required = true)
    private String itemDescription;


}
