

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ProjectEdit extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println("<form action='ProjectUpdate' method='GET'>");		
		//desde ProjectView, al darle al boton paso un hidden value llamado "id".
        String idStr = req.getParameter("id");
		

        ProjectData project = ProjectData.getProject(connection, idStr);
		
		toClient.println(Utils.header("Edit Project " + project.projectId));
		
        toClient.println("<form action='ProjectUpdate' method='GET'>");
		
        toClient.println("<table class='Table1'>");
        toClient.println("<tr><td>ProjectId</td>");
        toClient.println("<td><input name='projectId' value='" + project.projectId + "'></td></tr>");
        
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td><input name='companyName' value='" + project.companyName + "'></td></tr>");

		toClient.println("<tr><td>Manager</td>");
        toClient.println("<td><input name='first' value='" + project.first + "'></td>");		
	
	toClient.println("<tr><td>Last</td>");
        toClient.println("<td><input name='last' value='" + project.last + "'></td>");
		
        toClient.println("<tr><td>Start Date</td>");
        toClient.println("<td><input name='startDate' value='" + project.startDate + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Status</td>");
        toClient.println("<td><input name='status' value='" + project.status + "'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>DueDate</td>");
        toClient.println("<td><input name='dueDate' value='" + project.dueDate + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Country</td>");
        toClient.println("<td><input name='countryName' value='" + project.countryName + "'></td>");
        toClient.println("</tr>");

        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Submit</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer());
        toClient.close();
    }
}