import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class EmployeeUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String employeeId = req.getParameter("employeeId");
        
        EmployeeData employee = new EmployeeData(
                    req.getParameter("employeeId"),
                    req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    req.getParameter("hireDate"),
                    req.getParameter("phone"),
					req.getParameter("mail")
                );
        int n = EmployeeData.updateEmployee(connection, employee);
        res.sendRedirect("EmployeeView?employeeId=" + employeeId);
    }
}