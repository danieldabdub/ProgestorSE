import java.util.Vector;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;
public class ProjectView extends HttpServlet  {
   
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		String idStr = req.getParameter("id");
		
		ProjectData project = ProjectData.getProject(connection, idStr);
		
		toClient.println(Utils.header("Project's information  " + project.projectId));
   
		
		//tabla
		
        toClient.println("<table class= 'Table1' id='project' >");
        toClient.println("<tr><td>ProjectId</td>");
        toClient.println("<td>" + project.projectId + "</td></tr>");
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td>" + project.companyName + "</td>");
		toClient.println("<tr><td>Project Manager</td>");
        toClient.println("<td>" + project.first + project.last +"</td></tr>");
		toClient.println("<tr><td>Start Date</td>");
        toClient.println("<td>" + project.startDate + "</td></tr>");
		toClient.println("<tr><td>Status</td>");
        toClient.println("<td>" + project.status + "</td></tr>");
		toClient.println("<tr><td>Due Date</td>");
        toClient.println("<td>" + project.dueDate + "</td>");
		toClient.println("<tr><td>Country</td>");
        toClient.println("<td>" + project.countryName + "</td>");
		
        toClient.println("</tr>");
        toClient.println("</table>");
		
		//boton
		toClient.println( "<form method='get' action='ProjectEdit'> ");
			toClient.println("<input type= 'hidden' name= 'id' value = " + project.projectId + ">");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Edit project's information</button>");
			toClient.println("</div>" );
		toClient.println("</form>" );

     
    

/*     public static String getProjectEmployees(Vector<EmployeeData> employeesProject) {
        StringBuilder str = new StringBuilder();
		
		//tabla lista empleados
        toClient.println("<table class='Table1'>");
		
        for(int i=0; i< employeesProject.size(); i++){
            EmployeeData employees = employeesProject.elementAt(i);
            toClient.println("<tr>");
            toClient.println("<td>" + employees.firstName + " " + employee.LastName + "</td>");			

			
			//agregar mas atributos	
			toClient.println("</tr>");
        }   
		
		toClient.println("</table>"); */
		
		//boton
		toClient.println(Utils.footer());
		toClient.println( "<form method='get' action='AddEmployeeProject'>");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Add new employee</button>");
			toClient.println("</div>" );
		toClient.println("</form>" );
			
        

        
    }
}
