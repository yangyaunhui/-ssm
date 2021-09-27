package com.xiexin.bean.dto;

/**
 * DTO -- 通俗:  实体类 增加的新属性 或者 减少后的属性..
 *      一般用于传参!!
 *
 */
public class CustomerDTO {
    private String phone;
    // 猪脚:  token
    private String accessToken;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
