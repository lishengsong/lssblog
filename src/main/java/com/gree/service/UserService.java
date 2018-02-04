package com.gree.service;

import com.gree.bean.UserDb;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:00
 * @description:
 */
public interface UserService {

    UserDb add(UserDb user);

    UserDb fetch(String username, String password);

}
