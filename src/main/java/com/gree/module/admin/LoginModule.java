package com.gree.module.admin;

import com.gree.bean.UserDb;
import com.gree.mvc.CrossOriginsFilter;
import com.gree.mvc.context.UserContext;
import com.gree.security.JwtTonken;
import com.gree.service.impl.UserServiceImpl;
import com.gree.util.Rs;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:26
 * @description: 登录模块
 */
@IocBean
@At("/bgo")
@Ok("json")
public class LoginModule {

    @Inject
    UserServiceImpl userService;

    @Filters(@By(type=CrossOriginsFilter.class))
    @At("/login")
    public Rs dologin(@Param("username") String username, @Param("password") String password) {

        Rs<UserDb> rs = new Rs<UserDb>();
        UserDb exist = userService.fetch(username);
        if (exist == null) {
            rs.setCode(rs.FAIL);
            rs.setMsg("用户名不存在");
            return rs;
        }
        UserDb user = userService.fetch(username, password);
        if (user != null) {
            rs.setCode(rs.SUCCESS);
            rs.setToken(JwtTonken.createToken(user));
            UserContext userContext=new UserContext(user);
            rs.setMsg("登录成功");

        } else {
            rs.setCode(rs.FAIL);
            rs.setMsg("密码错误");
        }

        return rs;
    }
}
