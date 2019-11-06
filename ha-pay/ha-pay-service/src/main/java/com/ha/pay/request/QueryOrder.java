package com.ha.pay.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/3
 */
@Data
public class QueryOrder {
    /**
     * 支付id
     */
    private Integer payId;
    /**
     * 支付平台订单号
     */
    private String tradeNo;
    /**
     * 商户单号
     */
    private String outTradeNo;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 总金额
      */
    private BigDecimal totalAmount;
    /**
     * 账单时间：具体请查看对应支付平台
     */
    private Date billDate;
    /**
     * 账单类型：具体请查看对应支付平台
     */
    private String billType;
    /**
     * 支付平台订单号或者账单日期
     */
    private Object tradeNoOrBillDate;
    /**
     * 商户单号或者 账单类型
      */
    private String outTradeNoBillType;
    /**
     * 交易类型
      */
    private String transactionType;


}
