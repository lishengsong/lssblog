package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 15:37
 * @description: 分类db
 */
@Table("tb_category")
public class CategoryDb extends BaseBean{

      /*`cid` tinyint(2) unsigned NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
        `cname` varchar(15) NOT NULL DEFAULT '' COMMENT '分类名称',
        `keywords` varchar(255) DEFAULT '' COMMENT '关键词',
        `description` varchar(255) DEFAULT '' COMMENT '描述',
        `sort` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
        `pid` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '父级栏目id',*/

    @Id
    @Comment("分类id")
    @ColDefine(unsigned = true, notNull = true)
    private int cid ;

    @Column
    @Default("")
    @ColDefine(width = 15, notNull = true)
    @Comment("分类名称")
    private String cname;

    @Column
    @Default("")
    @ColDefine(width = 255)
    @Comment("关键词")
    private String keywords;

    @Column
    @Default("")
    @ColDefine(width = 255)
    @Comment("描述")
    private String description;

    @Column
    @Default("0")
    @ColDefine(unsigned = true,notNull = true)
    @Comment("排序")
    private int sort;

    @Column
    @Default("0")
    @ColDefine(unsigned = true,notNull = true)
    @Comment("父级栏目id")
    private int pid;


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
