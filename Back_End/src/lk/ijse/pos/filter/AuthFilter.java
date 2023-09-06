package lk.ijse.pos.filter;

import lk.ijse.pos.util.ResponseUtil;

import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RescaleOp;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String auth = req.getHeader("Auth");
        if(auth != null && auth.equals("user=admin,pass=users")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            res.addHeader("Content-Type","application/json");
            JsonObject authantication_fail = ResponseUtil.getJson("Auth-Error", "Authantication fail");
            res.getWriter().print(authantication_fail);
        }
    }

    @Override
    public void destroy() {

    }
}
