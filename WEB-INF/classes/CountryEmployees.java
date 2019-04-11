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
public class CountryEmployees extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String idStr = req.getParameter("id");
        PrintWriter toClient = res.getWriter();
        
        CountryData country = CountryData.getCountry(connection, idStr);
        
        
        Vector<EmployeeData> EmployeeList;
        EmployeeList = EmployeeData.getCountryEmployeeList(connection, country.countryName);
        
        toClient.println("<h2 align='center'> " + "Employees Availiable for  " + country.countryName+ "</h2>");
        
        toClient.println("<table class='Table1' id='countryEmployees' border='1'>");
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>EmployeeID</th>");
        toClient.println("<th>Name</th>");
        toClient.println("</tr>"); 
        toClient.println("</thead>"); 
        
        for(int i=0; i< EmployeeList.size(); i++){
                EmployeeData employee = EmployeeList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='EmployeeView?id=" + employee.employeeId + "'>"+ employee.employeeId +"</a></td>");
                toClient.println("<td>" + employee.firstName +" " + employee.lastName + " </td>");
                toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        toClient.close();
    }
}