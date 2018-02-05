package com.gree.module.admin;

import com.gree.bean.TagDb;
import com.gree.mvc.filter.AccessTokenFilter;
import com.gree.service.impl.TagServiceImpl;
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
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午6:14 2018/2/4
 * @Description: 标签控制器
 */


@Api(value = "bgo/tag")
@At("/bgo/tag")
@Ok("json")
@IocBean
@Chain("crossOrigin")
public class TagAdminModule {
    @Inject
    TagServiceImpl tagService;

    @ApiOperation(httpMethod = "POST",
            value = "返回标签对象",
            response = Rs.class,
            nickname="add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tname", paramType="form", value = "标签名称", dataType="string", required = true)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })
    @POST
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/add")
    public Rs add(@Param("tname") String tagName){
        if(Strings.isBlank(tagName)){
            return Rs.builder().code(Rs.FAIL).msg("标签名不能为空").build();
        } else {
            if(tagService.fetch(tagName)!=null){
                return Rs.builder().code(Rs.FAIL).msg("标签名已经存在").build();
            }
            TagDb tagDb = new TagDb();
            tagDb.setTname(tagName);
            if(tagService.add(tagDb)!=null) return Rs.builder().code(Rs.SUCCESS).msg("添加标签成功！").build();
            else return Rs.builder().code(Rs.FAIL).msg("新增标签失败").build();
        }
    }

}
