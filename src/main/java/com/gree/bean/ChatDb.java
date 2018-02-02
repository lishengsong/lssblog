package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 16:15
 * @description: 聊天db
 */
public class ChatDb extends BaseBean{

    /*CREATE TABLE `bjy_chat` (
            `chid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '碎言id',
            `date` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '发表时间',
            `content` text NOT NULL COMMENT '内容',
            `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否显示',
            `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
            PRIMARY KEY (`chid`)
    ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;*/


    @Id
    @Column("chid")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("碎言id")
    private int chatId ;

    @Column
    @ColDefine(type = ColType.TEXT, notNull = true)
    @Comment("内容")
    private int  content ;


    @Column("is_show")
    @Default("1")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否显示")
    private int isShow ;

    @Column("is_delete")
    @Default("0")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否删除")
    private int isDelete ;

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
