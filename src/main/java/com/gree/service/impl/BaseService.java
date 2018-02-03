package com.gree.service.impl;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:10
 * @description:
 */
public abstract class BaseService {

    /** 注入同名的一个ioc对象 */
    @Inject
    protected Dao dao;

}
