package com.gree.module.admin;

import com.gree.bean.CategoryDb;
import com.gree.mvc.context.UserContext;
import com.gree.mvc.filter.AccessTokenFilter;
import com.gree.security.JwtTonken;
import com.gree.service.impl.CategoryServiceImpl;
import com.gree.util.Rs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.*;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-08 15:57
 * @description： 分类模块
 */

@Api(value = "bgo/category", description = "Operations about category")
@At("/bgo/category")
@Ok("json")
@IocBean
@Chain("crossOrigin")
public class CategoryAdminModule  {

    @Inject
    protected CategoryServiceImpl categoryService;



    @ApiOperation(httpMethod = "POST",
            value = "返回标签对象",
            response = Rs.class,
            nickname="add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cname", paramType="form", value = "分类名称", dataType="string", required = true, defaultValue = "gree")
            ,@ApiImplicitParam(name = "keywords", paramType="form", value = "关键词", dataType="string", required = false, defaultValue = "gree_lss")
            ,@ApiImplicitParam(name = "description", paramType="form", value = "分类描述", dataType="string", required = false, defaultValue = "gree_description")
            ,@ApiImplicitParam(name = "sort", paramType="form", value = "排序", dataType="long", required = false, defaultValue = "0")
            ,@ApiImplicitParam(name = "pid", paramType="form", value = "父级id", dataType="long", required = false, defaultValue = "0")
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })

    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/add")
    @POST
    public Rs add(@Param("..") CategoryDb category){
        if(Strings.isBlank(category.getCname())){
            return Rs.builder().code(Rs.FAIL).msg("分类名不能为空").build();
        } else {
            try {
                if(categoryService.add(category)!=null)
                    return Rs.builder().code(Rs.SUCCESS).msg("添加分类成功！").token(JwtTonken.createToken(UserContext.getCurrentuser().get())).build();
                else
                    return Rs.builder().code(Rs.FAIL).msg("添加分类失败").build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return Rs.builder().code(Rs.FAIL).msg(e.getMessage()).build();
            }
        }
    }


    public Rs




}
