package ua.sazonova.hospital.filter;

import ua.sazonova.hospital.constants.Const;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType(Const.TEXT_HTML);
        servletResponse.setCharacterEncoding(Const.UTF_8);
        servletRequest.setCharacterEncoding(Const.UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
