import java.util.Vector;

public class ProjectView {
    public static String getProjectInfo(ProjectData project) {
		
		//esto funcionará con javascript------falta por hacer
		
        StringBuilder str = new StringBuilder();
		
		//titulo
		str.append(Utils.header(project.projectId));
		
		//tabla
		//aqui cojo el javascript que falta
        str.append("<table class= 'Table1' id='project' >");
        str.append("<tr><td>ProjectId</td>");
        str.append("<td>" + project.projectId + "</td></tr>");
		str.append("<tr><td>Client</td>");
        str.append("<td>" + project.clientId + "</td>");
		str.append("<tr><td>Project Manager</td>");
        str.append("<td>" + project.projectManager + "</td></tr>");
		str.append("<tr><td>Start Date</td>");
        str.append("<td>" + project.startDate + "</td></tr>");
		str.append("<tr><td>Status</td>");
        str.append("<td>" + project.status + "</td></tr>");
		str.append("<tr><td>Due Date</td>");
        str.append("<td>" + project.dueDate + "</td>");
        str.append("<tr><td>Number of employees</td>");
        str.append("<td>" + project.numberOfEmployees + "</td>");
		
        str.append("</tr>");
        str.append("</table>");
		
		//boton
		str.append( "<form method='get' action='ProjectEdit'> ");
			str.append("<input type= 'hidden' name= 'id' value = " + project.projectId + ">");
			str.append("<div style='text-align: right; '>" );
				str.append("<button  class='button button1' type='submit'>Edit project's information</button>");
			str.append("</div>" );
		str.append("</form>" );

        return str.toString();
    }

    public static String getProjectEmployees(Vector<EmployeeData> employeesProject) {
        StringBuilder str = new StringBuilder();
		
		//tabla lista empleados
        str.append("<table class='Table1'>");
		
        for(int i=0; i< employeesProject.size(); i++){
            EmployeeData employees = employeesProject.elementAt(i);
            str.append("<tr>");
            str.append("<td>" + employees.FirstName + " </td>");			
   			str.append("<td>" + employees.LastName + " </td>");
			
			//agregar mas atributos	
			str.append("</tr>");
        }   
		
		str.append("</table>");
		
		//boton
		str.append( "<form method='get' action='AddEmployeeProject'>");
			str.append("<div style='text-align: right; '>" );
				str.append("<button  class='button button1' type='submit'>Add new employee</button>");
			str.append("</div>" );
		str.append("</form>" );
			
        

        return str.toString();
    }
}
