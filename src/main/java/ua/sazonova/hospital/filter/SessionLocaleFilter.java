package ua.sazonova.hospital.filter;

import ua.sazonova.hospital.constants.Const;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getParameter(Const.SESSION_LOCALE) != null) {
            req.getSession().setAttribute(Const.LANG, req.getParameter(Const.SESSION_LOCALE));
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}