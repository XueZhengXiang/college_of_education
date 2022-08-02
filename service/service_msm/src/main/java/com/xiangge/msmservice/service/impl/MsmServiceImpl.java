package com.xiangge.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.xiangge.msmservice.service.MsmService;
import com.xiangge.msmservice.util.ConstantVodUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 祥哥
 * @version 1.0
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(Map<String, String> map, String phone) {
        String checkCode = "";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("SMS_154950909");
        request.setPhoneNumbers(phone);
        request.setTemplateParam(JSONObject.toJSONString(map));
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            checkCode = response.getCode();
            return checkCode.equals("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
//        if (StringUtils.isEmpty(phone)) {
//            return false;
//        }
//        DefaultProfile profile = DefaultProfile.getProfile
//                ("default", "LTAI5t7ACfNm3TyyvWRnsJRt", "JBmkIiO0PbpbBCM5OQt8ITcM9TAq5t");
//        IAcsClient client = new DefaultAcsClient(profile);
//        //设置相关参数
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2022-2-2");
//        request.setSysAction("SendSms");
//        //设置发送的相关参数
//        request.putQueryParameter("PhoneNumbers", phone); //手机号
//        request.putQueryParameter("SignName", "我的谷粒在线教育网站"); //申请阿里云 签名名称
//        request.putQueryParameter("TemplateCode", "SMS_180051135"); //申请阿里云 模板code
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map)); //验证码数据，转换json数据传递
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            boolean success = response.getHttpResponse().isSuccess();
//            return success;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    }
}
