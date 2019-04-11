import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class EmployeeUpdateQualifications extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String employeeId = req.getParameter("employeeId");
        String qualificationId = req.getParameter("selectedQualifId");
        String qualificationDate = req.getParameter("qualificationDate");
        
        int n = EmployeeData.insertEmployeeQualification(connection, employeeId, qualificationId, qualificationDate);
		//int n = EmployeeData.insertEmployeeQualification(connection, employeeId, qualificationId, null); //Corregir null cuando tengamos fecha definida
        res.sendRedirect("EmployeeView?employeeId=" + employeeId);
    }
}

