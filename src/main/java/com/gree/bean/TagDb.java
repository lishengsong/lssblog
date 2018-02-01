package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 15:00
 * @description:
 */
@Table("tb_tag")
public class TagDb  extends BaseBean{

    @Id
    @ColDefine(unsigned = true)
    @Comment("标签主键")
    private int tid ;

    @Column
    @ColDefine(width = 15)
    @Comment("标签名称")
    private String tname;

    @ManyMany(relation = "tb_article_tag", from = "tid", to = "aid")
    private List<ArticleDb> articleDbs;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
