package com.gree.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-01 11:14
 * @description: 基础
 */
public  abstract  class BaseBean  implements Serializable {


    @Column("ct")
    @Default("0")
    protected Date createDate;

    @Column("ut")
    @Default("0")
    protected Date updateDate;

    public String toString() {
        return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
