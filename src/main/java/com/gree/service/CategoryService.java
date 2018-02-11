package com.gree.service;

import com.gree.bean.CategoryDb;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-08 16:00
 * @description: 分类业务接口
 */
public interface CategoryService {

    CategoryDb add(CategoryDb tag) throws Exception;
    CategoryDb fetch(int id);
    int edit(CategoryDb tag) throws Exception;
    int delete(int id);
}
