import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date; 

@SuppressWarnings("serial")
public class ProjectNew extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");

		
        ProjectData project = new ProjectData(
		//ahora recoger la info de la pantalla segun el name="__" asignado dentro de cada input
                    req.getParameter("projectId"),
                    req.getParameter("clientId"),
					req.getParameter("managerId"),
					req.getParameter("startDate"),
					req.getParameter("status"),
					req.getParameter("dueDate"),
					req.getParameter("countryName")
                );
				
        int n = ProjectData.insertProject(connection, project);
        res.sendRedirect("ProjectList");
    }
}