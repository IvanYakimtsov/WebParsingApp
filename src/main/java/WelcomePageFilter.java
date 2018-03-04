import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter("/index.jsp")
public class WelcomePageFilter implements Filter{
    private static Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Locale loc = new Locale("en", "US");
        ResourceBundle rb = ResourceBundle.getBundle("elements", loc);

        String pageTitle = rb.getString("pageTitle");
        String languageElement = rb.getString("language");
        String submitElement = rb.getString("submit");
        servletRequest.setAttribute("locale", loc);
        servletRequest.setAttribute("pageTitle", pageTitle);
        servletRequest.setAttribute("language", languageElement);
        servletRequest.setAttribute("submit", submitElement);

        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
