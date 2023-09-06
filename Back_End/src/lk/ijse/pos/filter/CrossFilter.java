package lk.ijse.pos.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String method = req.getMethod();
        if(method.equals("OPTION")){
            res.setStatus(200);
            res.addHeader("Access-Control-Allow-Origin","*");
            res.addHeader("Access-Control-Allow-Method","Put,Delete");
            res.addHeader("Access-Control-Allow-Headers","content-type,auth");


        }else {
            res.addHeader("Access-Control-Allow-Origin", "*");

        }

    }

    @Override
    public void destroy() {

    }
}
