package com.gree;

import com.gree.bean.UserDb;
import com.gree.service.impl.UserServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-01-10 17:08
 * @description: MainSetup 需要实现Setup接口,并在其中初始化数据库表
 */
public class MainSetup implements Setup{


    // 特别留意一下,是init方法,不是destroy方法!!!!!
    public void init(NutConfig nc) {
        System.out.println("setup::init");
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        // 如果没有createTablesInPackage,请检查nutz版本
        Daos.createTablesInPackage(dao, "com.gree.bean", false);
        //Daos.migration(dao, UserDb.class, true, true, true);
        // 初始化默认根用户
       /* if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("123456");
            //user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            dao.insert(user);
        }*/
        // 初始化默认根用户
        if (dao.count(UserDb.class) == 0) {
            UserServiceImpl us = ioc.get(UserServiceImpl.class);
            us.add(new UserDb("admin","123456"));
        }
        // 获取NutQuartzCronJobFactory从而触发计划任务的初始化与启动
        //ioc.get(NutQuartzCronJobFactory.class);

    }

    public void destroy(NutConfig nc) {        // webapp销毁之前执行的逻辑
        // 这个时候依然可以从nc取出ioc, 然后取出需要的ioc 对象进行操作
        System.out.println("setup::destroy");
    }

}
