package com.yahaha.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.yahaha.reggie.common.BaseContext;
import com.yahaha.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yahaha
 * @date 2022-08-19 11:26:48
 * @description 检测用户登陆
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // 路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}",requestURI);

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3、如果不需要处理，则直接放行
        if (check){
            log.info("本次请求不需要处理：{}",requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //4-1、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id：{}",request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            // 获取线程号
            long id = Thread.currentThread().getId();
            log.info("登陆线程Id:{}", id);

            filterChain.doFilter(request, response);
            return;
        }

        //4-2、判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id：{}",request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            // 获取线程号
            long id = Thread.currentThread().getId();
            log.info("登陆线程Id:{}", id);

            filterChain.doFilter(request, response);
            return;
        }

        // 5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 进行路径匹配，检测本次是否放行
     * @param urls
     * @param requestURL
     * @return
     */
    public boolean check(String[] urls, String requestURL){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURL);
            if (match){
                return true;
            }
        }
        return false;
    }
}
