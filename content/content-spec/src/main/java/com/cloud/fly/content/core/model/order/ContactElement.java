package com.cloud.fly.content.core.model.order;

/**
 * @Auther: chw
 * @Date: 2018/12/21 00:05
 * @Description:
 */
public class ContactElement {
    /**
     * 联系人姓名，丌单独
     * 区分姓和名
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 联系人邮箱(留空)
     */
    private String email;

    /**
     * 联系人手机号，如有 多个按,号分割
     */
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
