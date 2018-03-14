package com.example.admin.gyl.http.model;

/**
 * Created by yjx on 2018/3/14.
 */

public class LoginModel {


    /**
     * time : 2018-03-14 13:13:05
     * error_id : 0
     * error :
     * msg : {"id":"28","mobile":"18005602999","co_name":"yjx0123456","legal_person":"","password":"e10adc3949ba59abbe56e057f20f883e","yyzz_code":"","jgxy_code":"","khxk_code":"","bank_name":"","bank_card":"","bank_lience_img":"","yyzz_img":"","jgxy_img":"","khxk_img":"","idcard_img_front":"","idcard_img_back":"","is_auth":"0","is_examined":"0","add_time":"0000-00-00 00:00:00","update_time":"0000-00-00 00:00:00"}
     */

    private String time;
    private int    error_id;
    private String error;
    /**
     * id : 28
     * mobile : 18005602999
     * co_name : yjx0123456
     * legal_person :
     * password : e10adc3949ba59abbe56e057f20f883e
     * yyzz_code :
     * jgxy_code :
     * khxk_code :
     * bank_name :
     * bank_card :
     * bank_lience_img :
     * yyzz_img :
     * jgxy_img :
     * khxk_img :
     * idcard_img_front :
     * idcard_img_back :
     * is_auth : 0
     * is_examined : 0
     * add_time : 0000-00-00 00:00:00
     * update_time : 0000-00-00 00:00:00
     */

    private MsgBean msg;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
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
        private String bank_lience_img;
        private String yyzz_img;
        private String jgxy_img;
        private String khxk_img;
        private String idcard_img_front;
        private String idcard_img_back;
        private String is_auth;
        private String is_examined;
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

        public String getBank_lience_img() {
            return bank_lience_img;
        }

        public void setBank_lience_img(String bank_lience_img) {
            this.bank_lience_img = bank_lience_img;
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

        public String getIdcard_img_front() {
            return idcard_img_front;
        }

        public void setIdcard_img_front(String idcard_img_front) {
            this.idcard_img_front = idcard_img_front;
        }

        public String getIdcard_img_back() {
            return idcard_img_back;
        }

        public void setIdcard_img_back(String idcard_img_back) {
            this.idcard_img_back = idcard_img_back;
        }

        public String getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(String is_auth) {
            this.is_auth = is_auth;
        }

        public String getIs_examined() {
            return is_examined;
        }

        public void setIs_examined(String is_examined) {
            this.is_examined = is_examined;
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
}
