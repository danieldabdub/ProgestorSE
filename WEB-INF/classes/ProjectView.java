import java.util.Vector;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ProjectView extends HttpServlet  {
   
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		String idStr = req.getParameter("id");
		
		
		
		ProjectData project = ProjectData.getProject(connection, idStr);
		
		toClient.println(Utils.header("Project's information  " + project.projectId));
		
		
		//tabla
		
        toClient.println("<table class= 'Table1' id='project' >");
        toClient.println("<tr><td>ProjectId</td>");
        toClient.println("<td>" + project.projectId + "</td></tr>");
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td>" + project.companyName + "</td>");
		toClient.println("<tr><td>Project Manager</td>");
        toClient.println("<td>" + project.first + project.last +"</td></tr>");
		toClient.println("<tr><td>Start Date</td>");
        toClient.println("<td>" + project.startDate + "</td></tr>");
		toClient.println("<tr><td>Status</td>");
        toClient.println("<td>" + project.status + "</td></tr>");
		toClient.println("<tr><td>Due Date</td>");
        toClient.println("<td>" + project.dueDate + "</td>");
		toClient.println("<tr><td>Country</td>");
        toClient.println("<td>" + project.countryName + "</td>");
		
        toClient.println("</tr>");
        toClient.println("</table>");
		
		//boton
		toClient.println( "<form method='get' action='ProjectEdit'> ");
			toClient.println("<input type= 'hidden' name= 'id' value = " + project.projectId + ">");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Edit project's information</button>");
			toClient.println("</div>" );
		toClient.println("</form>" );
		
		//AJAX - JS para mostrar empleados del proyecto.
		projectEmployees(toClient, connection, project.projectId);
		
		toClient.println(Utils.footer());
		
	 }
      public static void projectEmployees(PrintWriter toClient, Connection connection, String projectId) {
		toClient.println("<input type='hidden' id='projId' value='" + projectId + "'> </input>");
        toClient.println("<div align='center' id='divProjectEmployees'></div>");
        toClient.println("<div style='text-align:right;'>");
        toClient.println("<button class='button button1' id='button1' type='submit' onclick='viewProjectEmployees()'>View project's employees</button>");
        toClient.println("</div>");
		toClient.println("<script src=showEmployees.js></script>");
	 }	
		/* //boton
		
		toClient.println( "<form method='get' action='AddEmployeeProject'>");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Add new employee</button>");
			toClient.println("</div>" );
		toClient.println("</form>" ); */
			


        
    }