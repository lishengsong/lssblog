package com.gree.module.admin;

import com.gree.bean.TagDb;
import com.gree.mvc.context.UserContext;
import com.gree.mvc.filter.AccessTokenFilter;
import com.gree.security.JwtTonken;
import com.gree.service.impl.TagServiceImpl;
import com.gree.util.Rs;
import io.swagger.annotations.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.*;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午6:14 2018/2/4
 * @Description: 标签控制器
 */


@Api(value = "bgo/tag", description = "Operations about tag")
@At("/bgo/tag")
@Ok("json")
@IocBean
@Chain("crossOrigin")
public class TagAdminModule {
    @Inject
    protected TagServiceImpl tagService;

    /**
     * 添加标签
     * @param tagName
     * @return
     */
    @ApiOperation(httpMethod = "POST",
            value = "返回标签对象",
            response = Rs.class,
            nickname="add")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tname", paramType="form", value = "标签名称", dataType="string", required = true)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })

    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/add")
    @POST
    public Rs add(@Param("tname") String tagName){
        if(Strings.isBlank(tagName)){
            return Rs.builder().code(Rs.FAIL).msg("标签名不能为空").build();
        } else {
            if(tagService.fetch(tagName)!=null){
                return Rs.builder().code(Rs.FAIL).msg("标签名已经存在").build();
            }
            TagDb tagDb = new TagDb();
            tagDb.setTname(tagName);
            if(tagService.add(tagDb)!=null) return Rs.builder().code(Rs.SUCCESS).msg("添加标签成功！").token(JwtTonken.createToken(UserContext.getCurrentuser().get())).build();
            else return Rs.builder().code(Rs.FAIL).msg("新增标签失败").build();
        }
    }

    /**
     * 获取标签列表
     * @param tagName
     * @param pager
     * @return
     */
    @ApiOperation(httpMethod = "POST",
            value = "根据分页参数和标签名，返回标签列表",
            response = Rs.class,
            nickname="query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tname", paramType="form", value = "标签名称", dataType="string", required = false)
            ,@ApiImplicitParam(name = "pageSize", paramType="form", value = "每页显示的数目", dataType="long", required = false, defaultValue = "5")
            ,@ApiImplicitParam(name = "pageNumber", paramType="form", value = "第几页", dataType="long", required = false, defaultValue = "1")
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/query")
    @POST
    public Rs queryList(@Param("tname")String tagName
            ,@ApiParam(name="Pager",value="分页类",required=false) @Param("..")Pager pager){
        Cnd cnd = Strings.isBlank(tagName) ? null : Cnd.where("tname", "like", "%"+tagName+"%");
        QueryResult tagList = tagService.query(TagDb.class,cnd,pager,null);
        return Rs.builder().data(tagList).code(Rs.SUCCESS).token(JwtTonken.createToken(UserContext.getCurrentuser().get())).build();
    }


    /**
     * 获取标签
     * @param tid
     * @return
     */
    @ApiOperation(httpMethod = "GET",
            value = "根据标签id获取",
            response = Rs.class,
            nickname="fetch/{tid}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType="path", value = "标签id", dataType="long", required = true)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/fetch")
    @GET
    public Rs fetch(@Param("tid") int tid){
        Rs<TagDb> rs = new Rs<TagDb>();
        if(tid>0){
            TagDb tag =tagService.fetch(tid);
            if(tag == null){
                rs.setCode(Rs.FAIL);
                rs.setMsg("未找到对应标签");
            } else {
                rs.setCode(Rs.SUCCESS);
                rs.setData(tag);
                rs.setToken(JwtTonken.createToken(UserContext.getCurrentuser().get()));
            }
        } else {
            rs.setCode(Rs.FAIL);
            rs.setMsg("标签id不能为空");
        }
        return rs;
    }

    /**
     * 编辑标签
     * @param tag
     * @return
     */
    @ApiOperation(httpMethod = "POST",
            value = "修改标签",
            response = Rs.class,
            nickname="edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", paramType="form", value = "标签id", dataType="long", required = true)
            ,@ApiImplicitParam(name = "tname", paramType="form", value = "标签名", dataType="long", required = false)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })

    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/edit")
    @POST
    public Rs edit(@Param("..") TagDb tag){
        Rs<TagDb> rs = new Rs<TagDb>();
        if(tag.getTid()>0){
            if(Strings.isBlank(tag.getTname())){
                rs.setCode(Rs.FAIL);
                rs.setMsg("标签名称不能为空");
            } else {
                if(tagService.edit(tag)>0){
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

    /**
     *
     * @param tid
     * @return
     */
    @ApiOperation(httpMethod = "GET",
            value = "删除标签",
            response = Rs.class,
            nickname="delete/{tid}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", paramType="path", value = "标签id", dataType="long", required = true)
            ,@ApiImplicitParam(name = "Authorization", paramType = "header", value = "token input",dataType = "string", required = true)
    })
    @Filters(@By(type = AccessTokenFilter.class, args = {"ioc:tokenFilter"}))
    @At("/delete/?")
    @GET
    public Rs delete(int tid){
        Rs<TagDb> rs = new Rs<TagDb>();
        if(tid>0){
            if(tagService.delete(tid)>0) {
                rs.setCode(Rs.SUCCESS);
                rs.setMsg("删除标签成功");
                rs.setToken(JwtTonken.createToken(UserContext.getCurrentuser().get()));
            } else {
                rs.setCode(Rs.FAIL);
                rs.setMsg("标签删除失败");
            }
        } else {
            rs.setCode(Rs.FAIL);
            rs.setMsg("标签id不能为空");
        }
        return rs;
    }



}
