package com.xiexin.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

/*
* 阿里云的短信发送的工具类
* */
public class AliSMSUtil {
    public static void sendMsg(String phoneNum,Integer codeNum) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI5tM3zGxQ7YoQ7afNgzKL", "LsIpTyKYcLFalwPWkQErQIMi7qbMZX");

        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNum);//此手机号要从前端传进来
        request.putQueryParameter("SignName", "快速指定文件夹清理");//这个签名要和自己阿里云中的签名一样
        request.putQueryParameter("TemplateCode", "SMS_168825399");//这个是模板,也要和自己阿里云中的模板一样
        //随机的6位数字
//        int i = new Random().nextInt(999999);//0-999998 如果i<100000
//        if (i<100000){
//            i = i +100000;
//        }
        request.putQueryParameter("TemplateParam", "{\"code\":\""+codeNum+"\"}");//下面这个是验证码,需要自己写个随机数
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //随机6位数
    public static void main(String[] args) {
        int i = new Random().nextInt(999999);//0-999998 如果i<100000
        if (i<100000){
            i = i +100000;
        }
        System.out.println("i = " + i);
    }
}

