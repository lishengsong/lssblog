package com.gree.service.impl;

import com.gree.bean.TagDb;
import com.gree.service.TagService;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Date;
import java.util.List;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午6:18 2018/2/4
 * @Description:
 */
@IocBean(name = "tagService")
public class TagServiceImpl extends BaseService implements TagService {

    public TagDb add(TagDb tag) {
        tag.setCreateDate(new Date());
        tag.setUpdateDate(new Date());
        return dao.insert(tag);
    }

    public TagDb fetch(String name) {
        return dao.fetch(TagDb.class,name);
    }
    public TagDb fetch(int id) {
        return dao.fetch(TagDb.class,id);
    }

    public int edit(TagDb tag) {
        return dao.update(tag,"^$tname");
    }

    public int delete(int id) {
        return dao.delete(TagDb.class,id);
    }


}
