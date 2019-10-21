import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/first")
public class FirstServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FirstServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("https://onet.pl");

//        req.getRequestDispatcher("/first").forward(req, resp);
//        req.getRequestDispatcher("/second").include(req, resp);

        HttpSession session = req.getSession(true);

        Object counter = session.getAttribute("counter");
        int intCounter;
        if (counter == null) {
            logger.info("Counter not exists, creating new one");
            intCounter = 1;
            session.setAttribute("counter", intCounter);
        }
        else  {
            intCounter = (int) counter;
            logger.info("Current counter value for session: " + session.getId() + " is: "
             + counter);
            session.setAttribute("counter", ++intCounter);
        }

        resp.getWriter().println("Hello from first servlet for " + intCounter + " times!");
    }
}