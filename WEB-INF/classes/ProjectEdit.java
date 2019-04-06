

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ProductEdit extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("Edit Project " + ));
        toClient.println("<form action='ProjectUpdate' method='GET'>");
        toClient.println("<table class='Table1'>");
		
		//desde ProjectView, al darle al boton paso un hidden value llamado "id".
        String idStr = req.getParameter("id");
		
		//i call the method getProduct from the ProductData (in ProductData.java file)  class to grab info from the DATABASE 
		//in the method definition we defined two parameters:
		//1. connection =to establish connection with the DATABASE
		//2. idStr = an identifier that I will use to execute a pre-made SQL-SELECT instruction to get info from the Database
        ProjectData project = ProjectData.getProject(connection, idStr);
		
        toClient.println("<tr><td>ProjectId</td>");
        toClient.println("<td><input name='projectId' value='" + project.projectId + "'></td></tr>");
        
		toClient.println("<tr><td>Client</td>");
        String client = project.client;
		System.out.println("Client: " + client);
        name = name.replace("'","&#39;");
        System.out.println("Client: " + client);
        toClient.println("<td><input name='client' value='" + client + "'></td></tr>");
		
        toClient.println("<tr><td>Project Manager</td>");
        toClient.println("<td><input name='projectManager' value='" + project.manager + "'></td>");
		
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