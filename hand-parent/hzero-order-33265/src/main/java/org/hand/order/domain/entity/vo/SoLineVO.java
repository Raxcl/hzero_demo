package org.hand.order.domain.entity.vo;

import io.choerodon.mybatis.domain.AuditDomain;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单行VO
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Data
public class SoLineVO extends AuditDomain {
    private Long soLineId;
    private Long itemId;
    private Long lineNumber;
    private String itemCode;
    private String itemDescription;
    private BigDecimal orderQuantity;
    private String orderQuantityUom;
    private BigDecimal unitSellingPrice;
    private Long itemSumPrice;
    private String description;
    private String addition1;
    private String addition2;
    private String addition3;
    private String addition4;
    private String addition5;
}
