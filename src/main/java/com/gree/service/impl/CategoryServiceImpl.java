package com.gree.service.impl;

import com.gree.bean.CategoryDb;
import com.gree.service.CategoryService;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.QueryResult;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;
import java.util.Locale;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-08 16:00
 * @description:
 */
@IocBean(name = "categoryService")
public class CategoryServiceImpl extends BaseService implements CategoryService {


    public CategoryDb add(final CategoryDb category) throws Exception {
        int pid = category.getPid();
        if((pid > 0 && dao.fetch(CategoryDb.class, pid)!= null) || pid == 0){
            //过滤cid
           /* FieldFilter.locked(CategoryDb.class, "^cid$").run(new Atom(){
                public void run(){
                    dao.insert(category);
                }
            });*/

            FieldFilter filter = FieldFilter.locked(CategoryDb.class, "^cid$");
            return Daos.ext(dao, filter).insert(category);
        } else {
            throw  new Exception("非法传入父类id");
        }
    }

    public CategoryDb fetch(int id) {
        return dao.fetch(CategoryDb.class,id);
    }

    /**
     * 返回所有数据
     * @return
     */
    public List<CategoryDb> query(){
        return dao.query(CategoryDb.class,null);
    }

    /**
     * 修改忽略空值
     * @param tag
     * @return
     */
    public int edit(CategoryDb tag) {
        return Daos.ext(dao, FieldFilter.create(CategoryDb.class, true)).update(tag);
    }

    public int delete(int id) {
        return 0;
    }
}
