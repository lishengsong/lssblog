package com.gree.mvc.filter;

import com.gree.bean.UserDb;
import com.gree.mvc.context.UserContext;
import com.gree.security.JwtTonken;
import com.gree.util.Rs;
import com.gree.util.WebUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.VoidView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午12:09 2018/2/4
 * @Description:
 */
@IocBean(name = "tokenFilter")
public class AccessTokenFilter implements ActionFilter{

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenFilter.class);

    public View match(ActionContext actionContext) {
        System.out.println("AccessTokenFilter");
        try {
            if(!checkToken(actionContext.getRequest(),actionContext.getResponse())) return new VoidView();
        } catch (Exception e) {
            Rs<Object> resultBean = new Rs<Object>();
            resultBean.setCode(Rs.FAIL);
            resultBean.setMsg(e.getMessage());
            //e.printStackTrace();
            try {
                actionContext.getResponse().getWriter().append(Json.toJson(resultBean));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return new VoidView();
        }

        return null;
    }

    private boolean checkToken(HttpServletRequest request , HttpServletResponse response) throws Exception{
        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        for (String arg : args) {
            if (arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")) {
                return true;
            }
        }
        Rs<Object> resultBean = new Rs<Object>();
        String authorization = WebUtils.getHeaderFromRquest(request, "Authorization");
        if (authorization == null) {
            resultBean.setCode(Rs.NO_LOGIN);

            response.getWriter().append(Json.toJson(resultBean));
            return false;
        }
        try {
            Claims claims = JwtTonken.parseToken(authorization);
            UserDb user = new UserDb();
            user.setUsername((String) claims.get("username"));
            user.setPassword((String) claims.get("password"));
            UserContext.getCurrentuser().set(user);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if (e instanceof ExpiredJwtException) { //签名过期
                resultBean.setCode(Rs.LOGIN_EXPIRED);
                System.out.println("ExpiredJwtException");
            } else {
                resultBean.setCode(Rs.NO_LOGIN);
                System.out.println("Other JwtException");
            }
            response.getWriter().append(Json.toJson(resultBean));
            return false;
        }
    }
}
