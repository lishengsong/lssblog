package com.gree.module;

import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.servlet.Reader;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.resource.Scans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

/**
 * @Author :Web寻梦狮（lishengsong）
 * @Date Created in 下午11:21 2018/2/4
 * @Description: swaggerApi main 入口
 */
@IocBean(create = "init")
@At("/swagger")
public class SwaggerModule {

    private static final Log log = Logs.get();

    protected Swagger swagger;

    @Ok("void")
    @At
    public void swagger(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("true".equals(request.getParameter("force")))
            init(); //强制刷新
        final String pathInfo = request.getRequestURI();
        if (pathInfo.endsWith("/swagger.json")) {
            response.setContentType("application/json");
            response.getWriter().println(Json.mapper().writeValueAsString(swagger));
        } else if (pathInfo.endsWith("/swagger.yaml")) {
            response.setContentType("application/yaml");
            response.getWriter().println(Yaml.mapper().writeValueAsString(swagger));
        } else {
            response.setStatus(404);
        }
    }

    public void init() {
        log.info("init swagger ...");
        swagger = new Swagger();
        Info info = new Info();
        info.title("lssblog-Api");
        swagger.info(info);
        HashSet<Class<?>> classes = new HashSet<Class<?>>();
        // 把下来的package路径改成你自己的package路径
        for (Class<?> klass : Scans.me().scanPackage("com.gree.module")) {
            classes.add(klass);
        }
        Reader.read(swagger, classes);
    }
}
