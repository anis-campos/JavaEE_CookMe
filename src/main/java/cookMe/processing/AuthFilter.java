package cookMe.processing;

import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

            if (reqURI.contains("admin") && !reqURI.contains("adminLogin")
                    && (loggedUser == null || loggedUser.getType() != UserType.admin)) {

                String message = loggedUser == null ? "Non connecté ! " : "N'est pas admin : " + loggedUser.getLastname();
                //FacesContext context = FacesContext.getCurrentInstance();
                //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Second Message", "Additional Message Detail"));
                System.out.println(String.format("URL : %s - ", reqURI) + message);
                session.removeAttribute("logedUser");
                session.setAttribute("redirectFromLogin", req.getRequestURI());
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