package com.cq.struts2.action.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2016/6/3.
 */
public class SessionFilter implements Filter {
    protected FilterConfig filterConfig = null;
    private String redirectURL = null;
    private Set<String> notCheckURLList = new HashSet<String>();
    private String sessionKey = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        redirectURL = filterConfig.getInitParameter("redirectURL");
        sessionKey = filterConfig.getInitParameter("checkSessionKey");
        String notCheckURLListStr = filterConfig
                .getInitParameter("notCheckURLList");
        if (notCheckURLListStr != null) {
            System.out.println(notCheckURLListStr);
            String[] params = notCheckURLListStr.split(",");
            for (int i = 0; i < params.length; i++) {
                notCheckURLList.add(params[i].trim());
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (sessionKey == null) {
            System.out.println("1");
            filterChain.doFilter(request, response);
            return;
        }
        if ((!checkRequestURIIntNotFilterList(request))
                && session.getAttribute(sessionKey) == null) {
            System.out.println("2");
            response.sendRedirect(request.getContextPath() + redirectURL);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        notCheckURLList.clear();
    }


    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
        String uri = request.getServletPath()
                + (request.getPathInfo() == null ? "" : request.getPathInfo());
        String temp = request.getRequestURI();
        temp = temp.substring(request.getContextPath().length() + 1);
        // System.out.println("是否包括："+uri+";"+notCheckURLList+"=="+notCheckURLList.contains(uri));
        return notCheckURLList.contains(uri);
    }
}
