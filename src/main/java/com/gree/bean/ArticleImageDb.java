package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 14:38
 * @description: 文章图片封面
 */
@Table("tb_article_pic")
public class ArticleImageDb extends BaseBean {


    @Id
    @Column("pid")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("图片封面id")
    private int picId ;


    @Column("aid")
    @Default("0")
    @ColDefine(notNull = true)
    @Comment("对应文章的id")
    private int articleId ;

    @Column
    @Default("")
    @ColDefine(width = 140, notNull = true)
    @Comment("图片路径")
    private String path ;

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
