import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class EmployeeEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String employeeId = req.getParameter("employeeId");
        toClient.println(Utils.header("Employee " + employeeId + "(Edit mode)"));
        toClient.println("<body>");
        employeeBasic(toClient, connection, employeeId);
        employeeQualifications(toClient, connection, employeeId);
        toClient.println("</body>");
        toClient.println(Utils.footer());
        toClient.close();
    }
    
    
    public static void employeeBasic(PrintWriter toClient, Connection connection, String employeeId) {
        EmployeeData employee = EmployeeData.getEmployee(connection, employeeId);  
        //toClient.println("<h1 align='center'> Employee " + employeeId + "</h1>");
        toClient.println("<form method='get' action='EmployeeUpdate'>");
        toClient.println("<input type='hidden' name='employeeId' value='" + employeeId + "'>");
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>First Name</th>");
        toClient.println("<th>Last Name</th>");
        toClient.println("<th>Hire Date</th>");
        toClient.println("<th>Phone</th>");
        toClient.println("<th>Mail</th>");
        toClient.println("</tr>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td> <input name='firstName' value='" + employee.firstName + "'></td>");
        toClient.println("<td> <input name='lastName' value='" + employee.lastName + "'></td>");  
        toClient.println("<td> <input name='hireDate' value='" + employee.hireDate + "'></td>");  
        toClient.println("<td> <input name='phone' value='" + employee.phone + "'></td>");  
        toClient.println("<td> <input name='mail' value='" + employee.mail + "'></td>");  
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        toClient.println("<div style='text-align:right;'> <button class='button button1' type='submit'> Save changes </button></div>");
        toClient.println("</form>");
            
    }
    
        public static void employeeQualifications(PrintWriter toClient, Connection connection, String employeeId) {
        
		Vector<EmployeeQualificationData> employeeQualifications = EmployeeQualificationData.getEmployeeQualifications(connection, employeeId);
            
        toClient.println("<h2 align='center'> Employee's Qualifications </h2>");
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Qualification</th>");
        toClient.println("<th>Date</th>");
        toClient.println("</tr>");
        toClient.println("</thead>");
        
        for (int i=0; i<employeeQualifications.size();i++) {
            EmployeeQualificationData qualifications = employeeQualifications.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + qualifications.qualification + "</td>");
            toClient.println("<td>" + qualifications.qualificationDate + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</table>");
        
        //toClient.println("<form method='get' action='EmployeeEdit'>");
        //toClient.println("<input type='hidden' name='employeeId' value='" + employeeId + "'>");
        //toClient.println("<div style='text-align:right;'> <button class='button button1' type='submit'> Edit employee's information </button></div>");
        //toClient.println("</form>");
        //While in Edit Mode, it will not be allowed to add new qualifications 
        
    }
    
}