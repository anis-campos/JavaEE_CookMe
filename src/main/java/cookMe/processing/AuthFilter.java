package cookMe.processing;

import cookMe.model.UserModelBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Anis on 24/05/2016.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.jsf"})
public class AuthFilter implements Filter {

    public AuthFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            UserModelBean loggedUser = (UserModelBean) session.getAttribute("loggedUser");
            String reqURI = req.getRequestURI();

            if (reqURI.contains("admin") && !reqURI.contains("adminLogin")) {
                if (loggedUser == null || loggedUser.getType() != UserModelBean.UserType.admin)
                    res.sendRedirect("adminLogin.jsf");
            } else
                chain.doFilter(request, response);
        } catch (Throwable t) {
            System.out.println(t);
        }
    }

    @Override
    public void destroy() {

    }
}