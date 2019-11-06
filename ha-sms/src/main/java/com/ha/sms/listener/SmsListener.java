package com.ha.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ha.sms.config.SmsConfig;
import com.ha.sms.utils.SmsUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 *    短信服务监听器
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/16
 */
@Component
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsConfig smsConfig;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ha.sms.queue",durable = "true"),
            exchange = @Exchange(value = "ha.sms.exchange",ignoreDeclarationExceptions = "true"),
            key = {"ha.verify.code"}
    ))
    public void listenSms(Map<String,String> msg){
        if (msg == null || msg.size() <= 0){
            //不做处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(code)){
            //发送消息
            try {
                SendSmsResponse response = this.smsUtils.sendSms(phone, code, smsConfig.getSignName(), smsConfig.getVerifyCodeTemplate());
                System.out.println(response.getCode()+response.getMessage());
            }catch (ClientException e){
                return;
            }
        }else {
            //不做处理
            return;
        }
    }


}
