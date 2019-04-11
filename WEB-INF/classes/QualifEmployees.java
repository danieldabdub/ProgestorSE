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
public class QualifEmployees extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String StrId = req.getParameter("id");
        PrintWriter toClient = res.getWriter();
        
        Vector<EmployeeData> qualifEmployees = EmployeeData.getQualifiedEmployee(connection, StrId);
        
        toClient.println("<h2 align='center'> Qualified Employees </h2>");
        toClient.println("<table class='Table1' id='qualifiedEmployees' border='1'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>EmployeeId</th>");
        toClient.println("<th>Name</th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for (int i=0; i<qualifEmployees.size();i++) {
            EmployeeData employee = qualifEmployees.elementAt(i);
            
            toClient.println("<tr>");
            toClient.println("<td>" + employee.employeeId + "</td>");
            toClient.println("<td>" + employee.firstName +" " + employee.lastName + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}