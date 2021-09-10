package org.hand.order.domain.entity.vo;

import io.choerodon.mybatis.domain.AuditDomain;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单头VO
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@Data
public class SoOrderVO extends AuditDomain {
    private Long soHeaderId;
    private String orderNumber;
    private Long companyId;
    private String companyName;
    private Long customerId;
    private String customerName;
    private Date orderDate;
    private Long orderSumPrice;
    private String orderStatus;
    private BigDecimal unitSellingPrice;
    private List<SoLineVO> soLineList;
}
