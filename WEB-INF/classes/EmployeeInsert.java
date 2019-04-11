import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class EmployeeInsert extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		//desde ProjectView, al darle al boton paso un hidden value llamado "id".
        
		
		toClient.println(Utils.header("New Employee "));
		
        toClient.println("<form action='EmployeeNew' method='GET'>");
		
        toClient.println("<table id='newEmployeeTable' class='Table1'>");
        toClient.println("<tr><td>Employee ID </td>");
        toClient.println("<td><input name='employeeId'></input></td></tr>");
        
		toClient.println("<tr><td>First Name</td>");
        toClient.println("<td><input  name='firstName'></inputt></td></tr>");
		
		
		toClient.println("<tr><td>Last Name</td>");
        toClient.println("<td><input name='lastName' ></input></td>");
		
        toClient.println("<tr><td>Hire Date</td>");
        toClient.println("<td><input type='date' name='hireDate'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Phone</td>");
        toClient.println("<td><input name='phone' ></input></td>");
		
		toClient.println("<tr><td>mail</td>");
        toClient.println("<td><input name='mail' ></input></td>");
		
		

        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1' type='submit'>Save</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
	}
	
}