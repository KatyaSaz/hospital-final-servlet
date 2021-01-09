package ua.sazonova.hospital.filter;

import ua.sazonova.hospital.constants.Const;
import ua.sazonova.hospital.constants.View;
import ua.sazonova.hospital.entity.User;
import ua.sazonova.hospital.entity.enam.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//"/doctor"
@WebFilter(filterName = "authenticationFilter",
        urlPatterns = {"/patient",  "/doctor-patients", "/doctor-patient", "/doctor-patient-write",
        "/admin", "/admin-patients", "/admin-patient", "/admin-doctors", "/admin-doctor", "/admin-non-reg"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig)  {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String url = httpServletRequest.getRequestURL().toString();
        User user = (User) httpServletRequest.getSession().getAttribute(Const.USER);
        if(user==null){
            httpServletResponse.sendRedirect("/login");
        }else if(url.contains("/admin") & user.getRole().equals(Role.ADMIN)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(url.contains("/doctor") & user.getRole().equals(Role.DOCTOR) & user.isActive()){
            filterChain.doFilter(servletRequest, servletResponse);
        }else if(url.contains("/patient") & user.getRole().equals(Role.PATIENT) & user.isActive()){
            filterChain.doFilter(servletRequest, servletResponse);
        }else if(!user.isActive()) {
            httpServletRequest.getRequestDispatcher(View.WAIT_FOR_ADMIN_TO_APPROVE).forward(servletRequest, servletResponse);
        }else{
            httpServletRequest.getRequestDispatcher(View.ERROR_PROHIBITED_LINK_VIEW).forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() { }
}
