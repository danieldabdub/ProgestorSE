import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

public class EmployeeList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
		toClient.println(Utils.header("Employees"));
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Employee ID</th>");
		toClient.println("<th>First Name </th>");
		toClient.println("<th>Last Name </th>");
		toClient.println("</thead>");
		
        Vector<EmployeeData> EmployeeList;
        EmployeeList= EmployeeData.getEmployeeList(connection);
        for(int i=0; i< EmployeeList.size(); i++){
                EmployeeData employee =EmployeeList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='EmployeeView?id=" + employee.employeeId + "'>"+ employee.employeeId +"</a></td>");
                toClient.println("<td>" + employee.firstName + " </td>");
				toClient.println("<td>" + employee.lastName + " </td>");
                toClient.println("</tr>");


        }
		
		toClient.println(Utils.footer());
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
		toClient.println("<form method='get' action='NewClient.html'>"); /*a donde se dirige*/
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Create Client</button>");
		toClient.println("</div>");
		toClient.println("</form>");
		toClient.println("</body>");
        toClient.close();
    }
}