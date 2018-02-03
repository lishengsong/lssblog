package com.gree.service.impl;

import com.gree.bean.UserDb;
import com.gree.service.UserService;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;

import java.util.Date;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:15
 * @description:
 */
@IocBean(name="userService")
public class UserServiceImpl extends BaseService implements UserService{

    public UserDb add(UserDb user) {
        user.setPassword(Lang.md5(user.getPassword()));
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return dao.insert(user);
    }

    public UserDb fetch(String username, String password) {
        UserDb user = fetch(username);
        if (user == null) {
            return null;
        }
        String _pass = Lang.md5(password);
        if (_pass.equalsIgnoreCase(user.getPassword())) {
            return user;
        }
        return null;
    }

    public UserDb fetch(String username){
        return dao.fetch(UserDb.class,username);
    }
}
