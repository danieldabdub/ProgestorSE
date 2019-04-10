import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class QualificationUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String idStr = req.getParameter("qualificationId");

		
        QualificationData qualification = new QualificationData(
                    req.getParameter("qualificationId"),
                    req.getParameter("qualification"),
					req.getParameter("description")
                );
				
        int n = QualificationData.updateQualification(connection, qualification);
        res.sendRedirect("QualificationView?id=" + idStr + "&a=" + Math.random());
    }
}