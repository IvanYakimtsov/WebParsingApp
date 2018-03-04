import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/main_page")
public class ParseServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        response.getWriter().print("This is " + this.getClass().getName()
//                + ", using the POST method");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("localeName");
        String language = locale.substring(0,2);
        String country = locale.substring(3, locale.length());
        Locale loc = new Locale(language, country);
        request.getSession().setAttribute("locale", loc);
        ResourceBundle rb = ResourceBundle.getBundle("elements", loc);
        String pageTitle = rb.getString("pageTitle");
        String languageElement = rb.getString("language");
        String submitElement = rb.getString("submit");
        request.setAttribute("locale", loc);
        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("language", languageElement);
        request.setAttribute("submit", submitElement);
        logger.log(Level.INFO, "hello from first servlet " + loc.getCountry());

        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}