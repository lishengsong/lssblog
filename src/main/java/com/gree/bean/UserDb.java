package com.gree.bean;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 11:21
 * @description: user类
 */
@Table("tb_user")
public class UserDb extends BaseBean{


    @Name
    @ColDefine(width = 100, notNull = true)
    private String username;

    @Column
    @ColDefine(width = 100, notNull = true)
    private String password;

    @Column
    @ColDefine(width = 100, notNull = true)
    private String nickname;

    @Column
    @ColDefine(width = 400)
    private String photo;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
