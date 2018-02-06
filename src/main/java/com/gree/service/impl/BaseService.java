package com.gree.service.impl;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;

import java.util.List;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:10
 * @description:
 */
public abstract class BaseService {

    /** 注入同名的一个ioc对象 */
    @Inject
    protected Dao dao;

    public QueryResult query(Class<?> klass, Condition cnd, Pager pager, String regex) {
        if (pager != null && pager.getPageNumber() < 1) {
            pager.setPageNumber(1);
        }
        List<?> roles = dao.query(klass, cnd, pager);
        dao.fetchLinks(roles, null);
        pager.setRecordCount(dao.count(klass, cnd));
        return new QueryResult(roles, pager);
    }

}
