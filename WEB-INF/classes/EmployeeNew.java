import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date; 

@SuppressWarnings("serial")
public class EmployeeNew extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");

		
        EmployeeData employee = new EmployeeData(
		//ahora recoger la info de la pantalla segun el name="__" asignado dentro de cada input
                    req.getParameter("employeeId"),
                    req.getParameter("firstName"),
					req.getParameter("lastName"),
					req.getParameter("hireDate"),
					req.getParameter("phone"),
					req.getParameter("mail")
                );
				
        int n = EmployeeData.insertEmployee(connection, employee);
        res.sendRedirect("EmployeeList");
    }
}