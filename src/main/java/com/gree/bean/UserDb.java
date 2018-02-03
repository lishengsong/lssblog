package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 11:21
 * @description: user类
 */
@Table("tb_user")
public class UserDb extends BaseBean{
    /*CREATE TABLE `tb_user` (
            `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
            `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '关联的本站用户id',
            `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '类型 1：QQ  2：新浪微博 3：豆瓣 4：人人 5：开心网',
            `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '第三方昵称',
            `head_img` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
            `openid` varchar(40) NOT NULL DEFAULT '' COMMENT '第三方用户id',
            `access_token` varchar(255) NOT NULL DEFAULT '' COMMENT 'access_token token',
            `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '绑定时间',
            `last_login_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最后登录时间',
            `last_login_ip` varchar(16) NOT NULL DEFAULT '' COMMENT '最后登录ip',
            `login_times` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '登录次数',
            `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '状态',
            `email` varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
            `is_admin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否是admin',
            `is_vip` tinyint(2) unsigned DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;*/

    @Name
    @ColDefine(width = 100, notNull = true)
    private String username;

    @Column
    @ColDefine(width = 100, notNull = true)
    private String password;

    @Column
    @ColDefine(width = 100)
    private String nickname;

    @Column
    @ColDefine(width = 400)
    private String photo;

    @Column("is_admin")
    @Default("0")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否是管理员")
    private int isAdmin ;


    public UserDb(){};

    public UserDb(String username, String password) {
        this.username = username;
        this.password = password;
    }

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


    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }


}
