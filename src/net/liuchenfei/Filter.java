package net.liuchenfei;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by liuchenfei on 2016/11/12.
 */
@WebFilter(filterName = "Filter", urlPatterns = "/getData.html")
public class Filter implements javax.servlet.Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        System.out.println("send request to" + request.getServletPath());
        System.out.println("header" + request.getHeader("accept"));
        session.setAttribute("accept", request.getHeader("accept"));
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
