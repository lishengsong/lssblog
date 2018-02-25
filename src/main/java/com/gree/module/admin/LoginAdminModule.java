package com.gree.module.admin;

import com.gree.bean.UserDb;
import com.gree.mvc.filter.AccessTokenFilter;
import com.gree.mvc.context.UserContext;
import com.gree.security.JwtTonken;
import com.gree.service.impl.UserServiceImpl;
import com.gree.util.Rs;
import io.swagger.annotations.*;
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
@Chain("crossOriginAdmin")
public class LoginAdminModule {

    @Inject
    protected UserServiceImpl userService;

    @ApiOperation(httpMethod = "POST",
            value = "返回一个用户信息",
            response = Rs.class,
            nickname="login")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", paramType="form", value = "用户名", dataType="string", required = true, defaultValue = "admin")
            ,@ApiImplicitParam(name = "password", paramType="form", value = "密码", dataType="string", required = true, defaultValue = "123456")})
    @POST
    //@Filters(@By(type = CrossOriginsFilter.class,args = {"ioc:crossFilter"}))
    @At("/login")
    public Rs dologin(@Param("username")  String username, @Param("password") String password) {

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
    @ApiImplicitParams(@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true))
    @ApiOperation(value = "退出登录接口", notes = "退出销毁", httpMethod="GET", response = Rs.class, nickname = "logout")
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/logout")
    public Rs doLogout(){
        Rs<UserDb> rs = new Rs<UserDb>();
        rs.setCode(Rs.SUCCESS);
        rs.setMsg("logout");
        rs.setData(UserContext.getCurrentUser().get());
        return rs;
    }
}
