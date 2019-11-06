package com.ha.pay;

import com.jpay.ext.kit.HttpKit;
import com.jpay.ext.kit.PaymentKit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaPayServiceApplicationTests {

    @Test
    public void contextLoads() {

        String xmlMsg = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx175aacee5b06c0c0]]></appid>\n" +
                "<mch_id><![CDATA[1538281411]]></mch_id>\n" +
                "<nonce_str><![CDATA[tZ4F6MNNUQsRYfTC]]></nonce_str>\n" +
                "<sign><![CDATA[80A3A331E7EE16F87B470FD53B4310F0]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<transaction_id><![CDATA[4200000450201910126260023016]]></transaction_id>\n" +
                "<out_trade_no><![CDATA[1570847047674]]></out_trade_no>\n" +
                "<out_refund_no><![CDATA[1570847543317]]></out_refund_no>\n" +
                "<refund_id><![CDATA[50000002212019101212751777374]]></refund_id>\n" +
                "<refund_channel><![CDATA[]]></refund_channel>\n" +
                "<refund_fee>10</refund_fee>\n" +
                "<coupon_refund_fee>0</coupon_refund_fee>\n" +
                "<total_fee>10</total_fee>\n" +
                "<cash_fee>10</cash_fee>\n" +
                "<coupon_refund_count>0</coupon_refund_count>\n" +
                "<cash_refund_fee>10</cash_refund_fee>\n" +
                "</xml>";
        System.out.println("支付通知=" + xmlMsg);
        Map<String, String> params = PaymentKit.xmlToMap(xmlMsg);
        System.out.println(params.toString());
    }

}
