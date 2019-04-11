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
public class ProjectEmployees extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String projectId = req.getParameter("projectId");
        PrintWriter toClient = res.getWriter();
        
        Vector<EmployeeData> projectEmployees = EmployeeData.getProjectEmployeeList(connection, projectId);
        
        toClient.println("<h2 align='center'> Project's Employees</h2>");
        toClient.println("<table class='Table1' id='projectEmployees' border='1'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>EmployeeId</th>");
        toClient.println("<th>First Name</th>");
        toClient.println("<th>Last Name</th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for (int i=0; i<projectEmployees.size();i++) {
            EmployeeData employees = projectEmployees.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + employees.employeeId + "</td>");
            toClient.println("<td>" + employees.firstName + "</td>");
            toClient.println("<td>" + employees.lastName + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}