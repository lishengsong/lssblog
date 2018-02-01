package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 15:46
 * @description: 评论db
 */
@Table("tb_comment")
public class CommentDb extends BaseBean{

    /*CREATE TABLE `tb_comment` (
                `cmtid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
                `ouid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论用户id 关联oauth_user表的id',
                `type` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '1：文章评论',
                `pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父级id',
                `aid` int(10) unsigned NOT NULL COMMENT '文章id',
                `content` text NOT NULL COMMENT '内容',
                `date` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论日期',
                `status` tinyint(1) unsigned NOT NULL COMMENT '1:已审核 0：未审核',
                `is_delete` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
        PRIMARY KEY (`cmtid`)
    ) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;*/

    @Id
    @Column("cmtid")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("主键id")
    private int cmtId ;

    @Column
    @Default("0")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("评论用户id 关联oauth_user表的id")
    private int uid ;

    @Column
    @Default("1")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("1：文章评论")
    private int  type ;

    @Column
    @Default("0")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("父级id")
    private int  pid ;

    @Column
    @ColDefine(unsigned = true, notNull = true)
    @Comment("文章id")
    private int  aid ;

    @Column
    @ColDefine(type = ColType.TEXT, notNull = true)
    @Comment("内容")
    private int  content ;

    @Column
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("1:已审核 0：未审核")
    private int  status ;

    @Column("is_delete")
    @Default("0")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否删除")
    private int  isDelete ;


    public int getCmtId() {
        return cmtId;
    }

    public void setCmtId(int cmtId) {
        this.cmtId = cmtId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
