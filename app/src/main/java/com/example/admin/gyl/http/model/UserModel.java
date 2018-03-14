package com.example.admin.gyl.http.model;

/**
 * Created by Administrator on 2018/3/8.
 * 用户信息类
 */

public class UserModel implements java.io.Serializable{
    private static final long serialVersionUID = 2184226040671871034L;
    private String id;
    private String mobile;
    private String co_name;
    private String legal_person;
    private String password;
    private String yyzz_code;
    private String jgxy_code;
    private String khxk_code;
    private String bank_name;
    private String bank_card;
    private String yyzz_img;
    private String jgxy_img;
    private String khxk_img;
    private String is_auth;
    private String add_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYyzz_code() {
        return yyzz_code;
    }

    public void setYyzz_code(String yyzz_code) {
        this.yyzz_code = yyzz_code;
    }

    public String getJgxy_code() {
        return jgxy_code;
    }

    public void setJgxy_code(String jgxy_code) {
        this.jgxy_code = jgxy_code;
    }

    public String getKhxk_code() {
        return khxk_code;
    }

    public void setKhxk_code(String khxk_code) {
        this.khxk_code = khxk_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getYyzz_img() {
        return yyzz_img;
    }

    public void setYyzz_img(String yyzz_img) {
        this.yyzz_img = yyzz_img;
    }

    public String getJgxy_img() {
        return jgxy_img;
    }

    public void setJgxy_img(String jgxy_img) {
        this.jgxy_img = jgxy_img;
    }

    public String getKhxk_img() {
        return khxk_img;
    }

    public void setKhxk_img(String khxk_img) {
        this.khxk_img = khxk_img;
    }

    public String getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(String is_auth) {
        this.is_auth = is_auth;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
