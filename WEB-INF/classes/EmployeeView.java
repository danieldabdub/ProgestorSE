import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class EmployeeView extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String employeeId = req.getParameter("employeeId");
        toClient.println(Utils.header("Employee " + employeeId));
        toClient.println("<body>");
        employeeBasic(toClient, connection, employeeId);
        employeeQualifications(toClient, connection, employeeId);
        employeeProjects(toClient, connection, employeeId);
        employeeCountries(toClient, connection, employeeId);
        toClient.println("</body>");
        toClient.println(Utils.footer());
        toClient.close();
    }
    
    
    public static void employeeBasic(PrintWriter toClient, Connection connection, String employeeId) {
        EmployeeData employee = EmployeeData.getEmployee(connection, employeeId);  
        //toClient.println("<h1 align='center'> Employee " + employeeId + "</h1>");
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
        toClient.println("<td>" + employee.firstName + "</td>");
        toClient.println("<td>" + employee.lastName + "</td>");   
        toClient.println("<td>" + employee.hireDate + "</td>");
        toClient.println("<td>" + employee.phone + "</td>");
        toClient.println("<td>" + employee.mail + "</td>");
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        
        toClient.println("<form method='get' action='EmployeeEdit'>");
        toClient.println("<input type='hidden' name='employeeId' value='" + employeeId + "'>");
        toClient.println("<div style='text-align:right;'> <button class='button button1' type='submit'> Edit employee's information </button></div>");
        toClient.println("</form>");
        
    }
    
    public static void employeeQualifications(PrintWriter toClient, Connection connection, String employeeId) {
        
		Vector<EmployeeQualificationData> employeeQualifications = EmployeeQualificationData.getEmployeeQualifications(connection, employeeId);
            
        toClient.println("<h2 align='center'> Employee's Qualifications </h2>");
        toClient.println("<table id='qualificationsTable' class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Qualification</th>");
        toClient.println("<th>Date</th>");
        toClient.println("</tr>");
        toClient.println("</thead>");
        toClient.println("<tbody>");
        for (int i=0; i<employeeQualifications.size();i++) {
            EmployeeQualificationData qualifications = employeeQualifications.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + qualifications.qualification + "</td>");
            toClient.println("<td>" + qualifications.qualificationDate + "</td>");
            toClient.println("</tr>");
        }
        toClient.println("</tbody>");
        toClient.println("</table>");
        
    
        //Crear una variable JSON partiendo de qualificationsList    
        //JSON variable with qualificationsList
        
        toClient.println("<form method='get' action='EmployeeUpdateQualifications'>"); 
        toClient.println("<input type='hidden' name='employeeId' value='" + employeeId + "'>");
        toClient.println("<input type='hidden' id='selectedQualif' name='selectedQualifId'>");
		toClient.println("<input type='hidden' id='qualificDate' name='qualificationDate'>");
        toClient.println("<div id='divAddQualif' style='text-align:right;'> <button class='button button1' type='submit' onclick='addQualifications()'> Add qualification </button></div>");
        toClient.println("<div id='buttonQualif' style='text-align:right;'> </div>");
        toClient.println("</form>");
        
        toClient.print("<script>data=[");
        Vector<QualificationData> qualificationsList = QualificationData.getQualificationList(connection);
        for(int i=0; i< qualificationsList.size(); i++){
                QualificationData qualification = qualificationsList.elementAt(i);
                if (i!=0) {
                    toClient.print(",");
                    }
                toClient.print("{");
                toClient.print("\"qualificationId\":\"" + qualification.qualificationId + "\"");
                toClient.print(",\"qualification\":\"" + qualification.qualification + "\"");
                toClient.print(",\"description\":\"" + qualification.description + "\"");
                toClient.print("}");
        }
        toClient.println("]</script>");
        
        toClient.println("<script src=addQualifications.js></script>");
    }

    public static void employeeProjects(PrintWriter toClient, Connection connection, String employeeId) {
		toClient.println("<input type='hidden' id='empId' value='" + employeeId + "'> </input>");
        toClient.println("<div align='center' id='employeeProjects'></div>");
        toClient.println("<div style='text-align:right;'>");
        toClient.println("<button class='button button1' id='button1' type='submit' onclick='viewEmployeeProjects()'>View employee's projects</button>");
        toClient.println("</div>");
		toClient.println("<script src=showProjects.js></script>");
    }    
    
    public static void employeeCountries(PrintWriter toClient, Connection connection, String employeeId) {
		toClient.println("<input type='hidden' id='empId' value='" + employeeId + "'> </input>");
        toClient.println("<div align='center' id='employeeCountries'></div>");
        toClient.println("<div style='text-align:right;'>");
        toClient.println("<br><button class='button button1' id='button2' type='submit' onclick='viewEmployeeCountries()'>View countries</button>");
        toClient.println("</div>");
		toClient.println("<script src=showProjects.js></script>");
    }         
}

	

