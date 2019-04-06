import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ProjectUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String idStr = req.getParameter("projectId");
		
        ProjectData project = new ProjectData(
		//ahora recogeré la info de la pantalla segun el name="__" asignado dentro de cada input
                    req.getParameter("projectId"),
                    req.getParameter("client"),
					req.getParameter("manager"),
					req.getParameter("startDate"),
					req.getParameter("status")
					req.getParameter("dueDate")
                );
        int n = ProjectData.updateProject(connection, project);
        res.sendRedirect("ProjectEdit?id=" + idStr + "&a=" + Math.random());
    }
}