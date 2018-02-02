package com.gree.util;

import com.gree.bean.UserDb;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @user: 180296-Web寻梦狮
 * @date: 2018-02-02 11:00
 * @description: CurrentUserUtils
 */
public class CurrentUserUtils {

    public static final String CUR_USER = "me";
    public static CurrentUserUtils INSTANCE = null;

    private CurrentUserUtils() {

    }

    /**
     * 获取实例
     *
     * @return
     */
    public static CurrentUserUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (CurrentUserUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CurrentUserUtils();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * 获取当前Request
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        return Mvcs.getReq();
    }

    /**
     * 获取当前Session
     *
     * @return
     */
    private HttpSession getSession() {
        return getRequest().getSession(true);
    }

    /**
     * 获取当前session里面放置的User对象
     *
     * @return
     */
    public UserDb getUser() {
        if (Lang.isEmpty(getSession().getAttribute(CUR_USER))) {
            return null;
        }
        return (UserDb) getSession().getAttribute(CUR_USER);
    }

    /**
     * 把当前User对象放置到session里面
     *
     * @param user
     */
    public void setUser(UserDb user) {
        getSession().setAttribute(CUR_USER, user);
    }
}
