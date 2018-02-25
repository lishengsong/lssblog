package com.gree.mvc;

import com.gree.bean.UserDb;
import com.gree.mvc.context.UserContext;
import com.gree.util.Rs;
import org.nutz.json.Json;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.impl.processor.AbstractProcessor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-25 16:23
 * @description: 验证管理员身份
 */
public class AdminProcessor extends AbstractProcessor {

    public void process(ActionContext ac) throws Throwable {
        System.out.println("adminProcessor");
        UserDb user =UserContext.getCurrentUser().get();
        if(user==null || user.getIsAdmin()==1){
            doNext(ac);
        } else {
            Rs rs = new Rs();
            rs.setCode(Rs.FAIL);
            rs.setMsg("您没有权限访问");
            try {
                HttpServletResponse response = ac.getResponse();
                response.getWriter().write(Json.toJson(rs));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
                //e1.printStackTrace();
            }
        }
    }
}
