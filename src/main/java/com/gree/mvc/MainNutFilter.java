package com.gree.mvc;

import com.alibaba.druid.support.json.JSONUtils;
import com.gree.bean.UserDb;
import com.gree.mvc.context.UserContext;
import com.gree.security.JwtTonken;
import com.gree.util.Rs;
import com.gree.util.WebUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.nutz.json.Json;
import org.nutz.mvc.NutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-01-12 11:00
 * @description: 重写
 */
public class  MainNutFilter extends NutFilter {
    private static final Logger logger = LoggerFactory.getLogger(MainNutFilter.class);

    protected Set<String> prefixs = new HashSet<String>();


    @Override
    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        prefixs.add(conf.getServletContext().getContextPath() + "/druid/");
        prefixs.add(conf.getServletContext().getContextPath() + "/rs/");
        prefixs.add(conf.getServletContext().getContextPath() + "/bgo/login");
        /*可以省去
        <init-param>
          <param-name>exclusions</param-name>
          <param-value>/rs/*,/druid/*</param-value>
        </init-param>
        */

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.startsWith(prefix)) {
                    chain.doFilter(req, resp);
                    return;
                } else {
                    try {
                        if(checkToken((HttpServletRequest)req,(HttpServletResponse)resp)){
                            chain.doFilter(req, resp);
                            return;
                        };
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        super.doFilter(req, resp, chain);
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
