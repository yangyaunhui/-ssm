package com.xiexin.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 登录的拦截器,为了拦截三端的登录的接口
*
* 1)    后台管理------session
* 2)    顾客------token
* 3)    工程师------暂定
* */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JedisPool jedisPool;

    //方法调用之前的拦截
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //判断对方有没有,即判断它是否携带了token
        //如果携带了token,则需要判断token是否在redis中的
        //如果不在redis中,则是一个过期的或者前端伪造的token,则不允许登录
        //如果这个token是ok的,那么校验它的jwt token是否过期(非必要的)
        //过期了也不登录,并删除reids中的key
        //排除完毕后,就可以登录了

        //那么,后台 HttpServletRequest这个参数可以收取前端传来的token,那么现在问题
        //在于前端把token放入在哪里???才可以正常的发送到后台!!!!!!可以放在ajax的请求头中
        System.out.println("拦截器******生效了  return true放行了  return false拦截了");

        //后台获取
        String token = httpServletRequest.getHeader("token");//获取请求头
        System.out.println("token = " + token);
        //long time = JWT.decode(token).getExpiresAt().getTime();//获取token的过期时间
        //System.out.println("time = " + time);
        //String s = JWT.decode(token).getAudience().get(0);
        //System.out.println("s = " + s.substring(0,11));//获得手机号

        //获取了token---看token有没有在redis当中   单点登录******基于redis+token
        //因为要用到手机号,之前手机号是存入到jwt的信息中的,需要解析出来,生成的不同,解析的方式也不同
        //DecodedJWT decode = JWT.decode(token);//解析jwt
        //System.out.println("decode = " + decode);

        return true;
    }

    //方法执行中的拦截
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //方法调用之后的拦截
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
