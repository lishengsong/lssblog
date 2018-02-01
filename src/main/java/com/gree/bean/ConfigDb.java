package com.gree.bean;

import org.nutz.dao.entity.annotation.*;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.io.Serializable;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 17:26
 * @description:  ConfigDb 配置项
 */
@Table("tb_config")
public class ConfigDb implements Serializable{

    /*CREATE TABLE `bjy_config` (
                `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                `name` varchar(100) NOT NULL DEFAULT '' COMMENT '配置项键名',
                `value` text COMMENT '配置项键值 1表示开启 0 关闭',
        PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;*/



    @Id
    @ColDefine(unsigned = true, notNull = true)
    @Comment("主键id")
    private int id ;

    @Column
    @Default("")
    @ColDefine(width = 100,notNull = true)
    @Comment("配置项键名")
    private String name;

    @Column
    @ColDefine(type = ColType.TEXT)
    @Comment("配置项键值 1表示开启 0 关闭")
    private String value;


    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
