package com.gree.module.admin;

import com.gree.bean.CategoryDb;
import com.gree.mvc.context.UserContext;
import com.gree.mvc.filter.AccessTokenFilter;
import com.gree.security.JwtTonken;
import com.gree.service.impl.CategoryServiceImpl;
import com.gree.util.Rs;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Category;
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
            value = "添加分类",
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

    /**
     * 返回所有分类列表
     * @return
     */
    @ApiOperation(httpMethod = "GET",
            value = "返回分类列表",
            response = Rs.class,
            nickname="query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/query")
    @GET
    public Rs query(){
        return Rs.builder().data(categoryService.query())
                .code(Rs.SUCCESS)
                .token(JwtTonken.createToken(UserContext.getCurrentuser().get()))
                .build();
    }


    @ApiOperation(httpMethod = "GET",
            value = "根据id返回分类对象",
            response = Rs.class,
            nickname="fetch/{cateId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cateId", paramType = "path", value = "分类id",dataType = "long", required = true)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @GET
    public Rs fetch(@Param("cateId") int cid){
        Rs<CategoryDb> rs = new Rs<CategoryDb>();
        if(cid>0){
            CategoryDb category = categoryService.fetch(cid);
            if(category!=null){
                rs.setCode(Rs.SUCCESS);
                rs.setData(category);
                rs.setToken(JwtTonken.createToken(UserContext.getCurrentuser().get()));
            } else {
                rs.setCode(Rs.FAIL);
                rs.setMsg("未找到对应标签");
            }
        } else {
            rs.setCode(Rs.FAIL);
            rs.setMsg("非法输入");
        }
        return rs;
    }

    /**
     * 编辑标签
     * @param tag
     * @return
     */
    @ApiOperation(httpMethod = "POST",
            value = "修改分类",
            response = Rs.class,
            nickname="edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", paramType="form", value = "分类id", dataType="long", required = true)
            ,@ApiImplicitParam(name = "tname", paramType="form", value = "标签名", dataType="long", required = false)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })

    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/edit")
    @POST
    public Rs edit(@Param("..") CategoryDb category){
        Rs<CategoryDb> rs = new Rs<CategoryDb>();
        if(category.getCid()>0){
            if(Strings.isBlank(category.getCname())){
                rs.setCode(Rs.FAIL);
                rs.setMsg("标签名称不能为空");
            } else if (categoryService.fetch(category.getCid()) == null){
                rs.setCode(Rs.FAIL);
                rs.setMsg("分类找不到");
            } else {
                if(categoryService.edit(category)>0){
                    rs.setCode(Rs.SUCCESS);
                    rs.setMsg("修改标签成功");
                    rs.setToken(JwtTonken.createToken(UserContext.getCurrentuser().get()));
                } else{
                    rs.setCode(Rs.FAIL);
                    rs.setMsg("修改标签失败");
                }
            }
        } else {
            rs.setCode(Rs.FAIL);
            rs.setMsg("标签id不能为空");
        }
        return rs;
    }

}
