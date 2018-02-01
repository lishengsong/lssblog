package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 16:28
 * @description: LinkDb
 */
public class LinkDb {

    /*CREATE TABLE `tb_link` (
            `lid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
            `lname` varchar(50) NOT NULL DEFAULT '' COMMENT '链接名',
            `url` varchar(255) NOT NULL DEFAULT '' COMMENT '链接地址',
            `sort` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '排序',
            `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '文章是否显示 1是 0否',
            `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 1是 0否',
      PRIMARY KEY (`lid`)
    ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;*/


    @Id
    @Column("lid")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("主键id")
    private int linkId ;

    @Column("lname")
    @ColDefine(width = 50, notNull = true)
    @Comment("链接名")
    private String linkName ;

    @Column
    @ColDefine(width = 255, notNull = true)
    @Comment("链接地址")
    private String url ;

    @Column
    @Default("1")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("排序")
    private int sort ;

    @Column("is_show")
    @Default("1")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否显示 1是 0否")
    private int  isShow ;

    @Column("is_delete")
    @Default("0")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否删除 1是 0否")
    private int  isDelete ;

}
