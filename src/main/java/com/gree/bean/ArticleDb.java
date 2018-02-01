package com.gree.bean;

import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 11:33
 * @description: ArticleDb
 */

@Table("tb_article")
public class ArticleDb  extends BaseBean{

    @Id
    @ColDefine(unsigned = true, notNull = true)
    @Comment("文章表主键")
    private int aid ;

    @Column
    @Default("")
    @ColDefine(width = 100, notNull = true)
    @Comment("标题")
    private String title ;

    @Column
    @Default("")
    @ColDefine(width = 30, notNull = true)
    @Comment("作者")
    private String author ;

    @Column
    @ColDefine(type = ColType.TEXT, notNull = true)
    @Comment("内容")
    private String content;

    @Column
    @ColDefine(width = 255, notNull = true)
    @Comment("关键字")
    private String keywords;

    @Column
    @Default("")
    @ColDefine(width = 255, notNull = true)
    @Comment("描述")
    private String description;

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

    @Column("is_top")
    @Default("0")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否置顶")
    private int isTop ;

    @Column("is_original")
    @Default("1")
    @ColDefine(unsigned = true,width = 1, notNull = true)
    @Comment("是否原创")
    private int isOriginal ;

    @Column
    @Default("0")
    @ColDefine(unsigned = true, notNull = true)
    @Comment("点击数")
    private int click;

    @Column
    @Default("0")
    @ColDefine(unsigned = true, notNull = true)
    private int cid; //分类id

    @One(field="aid")   //跟图片封面表一对一对应
    private ArticleImageDb articleImage ;

    @ManyMany(relation = "tb_article_tag", from = "aid", to = "tid")
    private List<TagDb> tagDbs;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(int isOriginal) {
        this.isOriginal = isOriginal;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public ArticleImageDb getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(ArticleImageDb articleImage) {
        this.articleImage = articleImage;
    }

    public List<TagDb> getTagDbs() {
        return tagDbs;
    }

    public void setTagDbs(List<TagDb> tagDbs) {
        this.tagDbs = tagDbs;
    }
}
