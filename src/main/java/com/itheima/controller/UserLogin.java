package com.itheima.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登录验证过滤器
 * @author liuzhiyong
能直接访问的链接有：
http://localhost:8080/contactSystem/login.jsp
http://localhost:8080/contactSystem/LoginServlet
不能直接访问的链接有：
http://localhost:8080/contactSystem/ListServlet
http://localhost:8080/contactSystem/list.jsp
 */
public class UserLogin implements Filter{
    /**
     * 拦截业务逻辑：
     * 1.先指定放行的资源，哪些资源不需要拦截：
     * login.jsp + /LoginServlet (request对象可以获取)
     * 2.获取session，从session中获取用户信息userInfo
     * 3.再判断用户信息是否为空
     * 为空，说明没有登录，跳转到登录
     * 不为空，已经登录，放行
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
//转型
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
//获取到请求的uri
        String uri = request.getRequestURI();
        System.out.println("=======拦截的uri=========="+uri);
//先判断指定放行的资源，给予放行：
        if(uri.equals(request.getContextPath()+"/LoginServlet") || uri.equals(request.getContextPath()+"/login.jsp")
                || uri.equals(request.getContextPath()+"/css/login.css") || uri.equals(request.getContextPath()+"/js/login.js")
                || uri.equals(request.getContextPath()+"/js/jquery-1.8.2.js")){
//放行
            chain.doFilter(request, response);
        }else{//拦截其它资源
//获取session，从session中获取用户信息userInfo
            HttpSession session = request.getSession(false);//注意这里填上参数false，以免自己创建
            if(session != null){
//从session中获取用户信息userInfo
                Object userInfo = session.getAttribute("userInfo");
//判断用户信息是否为空
                if(userInfo == null){//没有登录，跳转到登录
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }else{//已经登录，放行
                    chain.doFilter(request, response);
                }
            }else{//session为空，说明也没有登录，跳到登录
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
// TODO Auto-generated method stub
    }
    @Override
    public void destroy() {
// TODO Auto-generated method stub
    }
}
