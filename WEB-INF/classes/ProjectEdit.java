

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

		
		//desde ProjectView, al darle al boton paso un hidden value llamado "id".
        String idStr = req.getParameter("id");
		
		//i call the method getProduct from the ProductData (in ProductData.java file)  class to grab info from the DATABASE 
		//in the method definition we defined two parameters:
		//1. connection =to establish connection with the DATABASE
		//2. idStr = an identifier that I will use to execute a pre-made SQL-SELECT instruction to get info from the Database
        ProjectData project = ProjectData.getProject(connection, idStr);
		
		toClient.println(Utils.header("Edit Project " + project.projectId));
		
        toClient.println("<form action='ProjectUpdate' method='GET'>");
		
        toClient.println("<table class='Table1'>");
        toClient.println("<tr><td>ProjectId</td>");
        toClient.println("<td><input name='projectId' value='" + project.projectId + "'></td></tr>");
        
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td><input name='companyName' value='" + project.companyName + "'></td></tr>");

		toClient.println("<tr><td>Manager Last Name</td>");
        toClient.println("<td><input name='last' value='" + project.last + "'></td>");		
		
        toClient.println("<tr><td>Manager First Name</td>");
        toClient.println("<td><input name='first' value='" + project.first + "'></td>");
		
        toClient.println("<tr><td>Start Date</td>");
        toClient.println("<td><input name='startDate' value='" + project.startDate + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Status</td>");
        toClient.println("<td><input name='status' value='" + project.status + "'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>DueDate</td>");
        toClient.println("<td><input name='dueDate' value='" + project.dueDate + "'></td>");
        toClient.println("</tr>");

        toClient.println("</table>");
        toClient.println("<input type='submit'>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer("Product Form"));
        toClient.close();
    }
}