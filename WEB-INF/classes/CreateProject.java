import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CreateProject extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
	
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println(Utils.header("Projects"));
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>ProjectID</th>");
		toClient.println("<th>Client</th>");
		toClient.println("<th>Project Manager</th>");
		toClient.println("<th>Status</th>");
		
		
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        ProjectData project = new ProjectData(
                    req.getParameter("ProjectID"),
                    req.getParameter("CompanyName"),
                    req.getParameter("ProjectManager"),
                    null,
                    req.getParameter("status")
                );
        int n = ProjectData.insertProject(connection, project);
        res.sendRedirect("Projectlist");
    }
}