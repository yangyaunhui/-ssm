package com.xiexin.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xiexin.bean.dto.CustomerDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 制作 jwt token 的类...
 *  jwt 三部分组成
 *          加密格式,  载荷(返回前端的信息,失效时间..k-v)  ,  签名
 *
 *          // DTO :  返回给前端内容.   token -- 用户名 发送给前端
 */
public class JwtToToken {
    private static final int EX_TIME=60*60*24*10; // 10天

    // 制作  token
    // 失效日期  计算现在当前的日期 + EX_TIME 就是  10天后的日期.. 那么
    // 存个手机号
    // 生成 jWT  token
    // jwt 由哪3部分组成 :  header  载荷  签名  , 需要注意的是:  过期时间 , 必须的!!!
    // token 是 dto 的属性 , 因为手几号是 唯一的,且 dto 有手机号.
    public CustomerDTO createJwt(String phone) {
        CustomerDTO customerDTO = new CustomerDTO();
        // 当前日期.
        Calendar nowTime = Calendar.getInstance();
        // 当前的 日期nowTime + 10天 EX_TIME 等于 失效时间
        nowTime.add(Calendar.SECOND, EX_TIME);
        Date exTime = nowTime.getTime();
        // 生成token
        String token = JWT.create().withAudience(phone + System.currentTimeMillis()).withExpiresAt(exTime)
                .withJWTId(UUID.randomUUID().toString()).sign(Algorithm.HMAC256(phone));
        System.out.println(token);
        // 作业1-0 : 把 生成的 token 给存入到redis 当中.
        // 提示: phone + System.currentTimeMillis() 作为key, token作为value
        // token 给存入到 redis中...
        customerDTO.setPhone(phone);
        customerDTO.setAccessToken(token);
        return customerDTO;

    }

    public static void main(String[] args) {
        JwtToToken jwtToToken = new JwtToToken();
        CustomerDTO jwt = jwtToToken.createJwt("17550693512");
        System.out.println("jwt = " + jwt.getAccessToken());

    }


}
