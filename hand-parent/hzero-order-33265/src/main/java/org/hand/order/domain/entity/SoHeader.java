package org.hand.order.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author long.chen08@hand-china.com 2021-07-31 09:17:09
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_header")
public class SoHeader extends AuditDomain {

    public static final String FIELD_SO_HEADER_ID = "soHeaderId";
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_COMPANY_ID = "companyId";
    public static final String FIELD_ORDER_DATE = "orderDate";
    public static final String FIELD_ORDER_STATUS = "orderStatus";
    public static final String FIELD_CUSTOMER_ID = "customerId";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


    @ApiModelProperty("订单头ID（主键）")
    @Id
    @GeneratedValue
    private Long soHeaderId;
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank
    private String orderNumber;
    @ApiModelProperty(value = "公司ID", required = true)
    @NotNull
    private Long companyId;
    @ApiModelProperty(value = "订单日期", required = true)
    @NotNull
    private Date orderDate;
    @ApiModelProperty(value = "订单状态", required = true)
    @NotBlank
    private String orderStatus;
    @ApiModelProperty(value = "客户ID", required = true)
    @NotNull
    private Long customerId;

//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------

    /**
     * @return 订单头ID（主键）
     */
    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public SoHeader setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
        return this;
    }

    /**
     * @return 订单编号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    public SoHeader setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    /**
     * @return 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    public SoHeader setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    /**
     * @return 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    public SoHeader setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    /**
     * @return 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    public SoHeader setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    /**
     * @return 客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    public SoHeader setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
}
