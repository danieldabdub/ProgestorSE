import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@SuppressWarnings("serial")
public class ClientProjects extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String clientId = req.getParameter("clientId");
        PrintWriter toClient = res.getWriter();
        
        Vector<ClientProjectData> clientProjects = ClientProjectData.getClientProjects(connection, clientId);
        
        toClient.println("<h2 align='center'> Client's projects </h2>");
        toClient.println("<table class='Table1' id='clientProject' border='1'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>ProjectID</th>");
        toClient.println("<th>Client</th>");
        toClient.println("<th>Status</th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for (int i=0; i<clientProjects.size();i++) {
            ClientProjectData projects = clientProjects.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + projects.projectId + "</td>");
            toClient.println("<td>" + projects.countryName + "</td>");
            toClient.println("<td>" + projects.status + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}