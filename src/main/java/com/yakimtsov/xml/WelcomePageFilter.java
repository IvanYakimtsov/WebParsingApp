package com.yakimtsov.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

@WebFilter("/index.jsp")
public class WelcomePageFilter implements Filter{
    private static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Locale loc;
        if(session.getAttribute("locale") == null){
            loc = new Locale("en", "US");
            session.setAttribute("locale", loc);
        } else {
            loc = (Locale) session.getAttribute("locale");
        }

        ResourceBundle rb = ResourceBundle.getBundle("elements", loc);
        Set<String> keySet = rb.keySet();
        keySet.forEach(key -> {
            String value = rb.getString(key);
            servletRequest.setAttribute(key,value);
        });

        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
