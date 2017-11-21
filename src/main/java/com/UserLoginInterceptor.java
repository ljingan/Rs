package com;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 拦截器
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 在页面渲染完成返回给客户端之前执行
     */
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     */
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    /**
     * 在DispatcherServlet之前执行
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {


        String url = request.getRequestURI();
        //除开登录页面动作，其它的都进行拦截控制
        if (url.equals("/login") || url.equals("/doLogin")) {
            return true;
        }
        //获取Session
        HttpSession session = request.getSession();
        String adminName = (String) session.getAttribute("adminName");

        if (adminName != null) {
            return true;
        }
        //不符合条件的，跳转到登录界面
        response.sendRedirect("/login");
//        request.getRequestDispatcher("/admin/login").forward(request, response);

        return false;
    }

}