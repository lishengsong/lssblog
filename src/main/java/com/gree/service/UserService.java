package com.gree.service;

import com.gree.bean.UserDb;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:00
 * @description:
 */
public interface UserService {

    public UserDb add(UserDb user);

    public UserDb fetch(String username, String password);

}
