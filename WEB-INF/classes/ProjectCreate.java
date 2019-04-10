import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ProjectCreate extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println("<form action='ProjectList' method='GET'>");		
		

        ProjectData project = ProjectData.NewProject(connection, idStr);
		
		toClient.println(Utils.header("Create Project "));
		
        toClient.println("<form action='ProjectUpdate' method='GET'>");
		
        toClient.println("<table class='Table1'>");
        toClient.println("<tr><td>Project ID</td>");
        toClient.println("<td><input name='projectId' value='Project ID'></td></tr>");
        
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td><input name='companyName' value='Company name'></td></tr>");

		toClient.println("<tr><td>Manager</td>");
        toClient.println("<td><input name='first' value='First Name'></td>");		
	
	toClient.println("<tr><td>Last</td>");
        toClient.println("<td><input name='last' value='Last Name '></td>");
		
        toClient.println("<tr><td>Start Date</td>");
        toClient.println("<td><input name='startDate' value='Start Date'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Status</td>");
        toClient.println("<td><input name='status' value='Status'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>DueDate</td>");
        toClient.println("<td><input name='dueDate' value='Due Date'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Country</td>");
        toClient.println("<td><input name='countryName' value='Country'></td>");
        toClient.println("</tr>");

        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Save</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer());
        toClient.close();
    }
}