package com.gree.service;

import com.gree.bean.TagDb;

import java.util.List;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午6:18 2018/2/4
 * @Description: tag接口
 */
public interface TagService {

    TagDb add(TagDb tag);
    TagDb fetch(int id);
    int edit(TagDb tag);

}
