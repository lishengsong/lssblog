package com.gree.module.admin;

import com.gree.bean.UserDb;
import com.gree.mvc.filter.AccessTokenFilter;
import com.gree.mvc.filter.CrossOriginsFilter;
import com.gree.mvc.context.UserContext;
import com.gree.security.JwtTonken;
import com.gree.service.impl.UserServiceImpl;
import com.gree.util.Rs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-03 11:26
 * @description: 登录模块
 */
@Api(value = "/bgo")
@IocBean
@At("/bgo")
@Ok("json")
@Chain("crossOrigin")
public class LoginAdminModule {

    @Inject
    UserServiceImpl userService;

    @POST
    //@Filters(@By(type = CrossOriginsFilter.class,args = {"ioc:crossFilter"}))
    @At("/login")
    public Rs dologin(@Param("username") String username, @Param("password") String password) {

        Rs<UserDb> rs = new Rs<UserDb>();
        if(Strings.isBlank(username) || Strings.isBlank(password)){
            rs.setCode(Rs.FAIL);
            rs.setMsg("用户名或密码不能为空");
            return rs;
        }
        UserDb exist = userService.fetch(username);
        if (exist == null) {
            rs.setCode(Rs.FAIL);
            rs.setMsg("用户名不存在");
            return rs;
        }
        UserDb user = userService.fetch(username, password);
        if (user != null) {
            rs.setCode(Rs.SUCCESS);
            rs.setToken(JwtTonken.createToken(user));
            UserContext userContext=new UserContext(user);
            rs.setMsg("登录成功");

        } else {
            rs.setCode(Rs.FAIL);
            rs.setMsg("密码错误");
        }

        return rs;
    }

    /*@Filters({@By(type = CrossOriginsFilter.class,args = {"ioc:crossFilter"})
            ,@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"})})*/
    @GET
    @ApiOperation(value = "心跳接口", notes = "发我一个ping,回你一个pong", httpMethod="GET")
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/logout")
    public Rs doLogout(){
        Rs<UserDb> rs = new Rs<UserDb>();
        rs.setCode(Rs.SUCCESS);
        rs.setMsg("logout");
        rs.setData(UserContext.getCurrentuser().get());
        return rs;
    }
}