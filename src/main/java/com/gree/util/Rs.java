package com.gree.util;

import java.io.Serializable;
import java.util.Map;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:29
 * @description: restful api
 */
public class Rs<T> implements Serializable {

    /*--定义公共状态常量--*/
    //未登录
    public static final int NO_LOGIN=301;
    //登陆过期
    public static final int LOGIN_EXPIRED = 302;
    //成功
    public static final int SUCCESS=200;
    //失败
    public static final int FAIL=500;
    //没有权限
    public static final int NO_PERMISSION=400;

    private Integer Code;
    private String msg;
    private String token;
    private T data;




    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public static <T> Builder<T> builder(){
        return new Builder<T>();
    }

    public static class Builder<T>{
        private Integer code;
        private String msg;
        private String token;
        private T data;
        public Builder code(Integer code){
            this.code=code;
            return this;
        }
        public Builder msg(String msg){
            this.msg =msg;
            return this;
        }
        public Builder token(String token){
            this.token=token;
            return this;
        }
        public Builder data(T data){
            this.data =data;
            return this;
        }
        public Rs<T> build(){
            Rs<T> resultBean =new Rs<T>();
            resultBean.setCode(this.code);
            resultBean.setMsg(this.msg);
            resultBean.setToken(this.token);
            resultBean.setData(this.data);
            return  resultBean;
        }

    }
}
