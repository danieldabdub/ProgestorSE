import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

public class ProjectList extends HttpServlet {
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
		
		
        Vector<ProjectData> ProjectList;
        ProjectList= ProjectData.getProjectList(connection);
        for(int i=0; i< ProjectList.size(); i++){
                ProjectData project =ProjectList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='ProjectView?id=" + project.projectId + "'>"+ project.projectId +"</a></td>");
                toClient.println("<td>" + project.companyName + " </td>");
                toClient.println("<td>" + project.last + " </td>");
                toClient.println("<td>" + project.first + " </td>");
                toClient.println("<td>" + project.status + " </td>");
                
                toClient.println("<td>" + project.dueDate + " </td>");
                
                toClient.println("</tr>");

				


        }
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
		toClient.println("<form method='get' action='CreateProject'>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Create Project</button>");
		toClient.println("</div>");
		toClient.println("</form>");
		toClient.println("</body>");
        toClient.close();
    }
}