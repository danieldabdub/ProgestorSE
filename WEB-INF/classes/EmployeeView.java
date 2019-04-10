import java.util.Vector;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;


public class EmployeeView extends HttpServlet  {
   
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		String idStr = req.getParameter("id");
		
		EmployeeData employee = EmployeeData.getEmployee(connection, idStr);
		
		toClient.println(Utils.header("Employee's information  " + employee.employeeId));
   
		
		//tabla
		
        toClient.println("<table class= 'Table1' id='employee' >");
        toClient.println("<tr><td>First name</td>");
        toClient.println("<td>" + employee.firstName + "</td></tr>");
		toClient.println("<tr><td>Last Name</td>");
        toClient.println("<td>" + employee.lastName + "</td>");
		toClient.println("<tr><td>Hire Date</td>");
        toClient.println("<td>" + employee.hireDate +"</td></tr>");
		toClient.println("<tr><td>Phone</td>");
        toClient.println("<td>" + employee.phone + "</td></tr>");
		toClient.println("<tr><td>Mail</td>");
        toClient.println("<td>" + employee.mail + "</td></tr>");
		
        toClient.println("</tr>");
        toClient.println("</table>");
		
		//boton
		toClient.println( "<form method='get' action='EmployeeEdit'> ");
			toClient.println("<input type= 'hidden' name= 'id' value = " + employee.employeeId + ">");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Edit Employee's information</button>");
			toClient.println("</div>" );
		toClient.println("</form>" );

 
		toClient.println(Utils.footer());
			
        

        
    }
}
