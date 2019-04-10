import java.util.Vector;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;


public class CountryView extends HttpServlet  {
   
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		String idStr = req.getParameter("countryName");
		
		CountryData country = CountryData.getCountry(connection, idStr);
		
		toClient.println(Utils.header("Employees Availiable for  " + country.countryName));
   
		
		//tabla
		
        toClient.println("<table class= 'Table1' id='countryName' >");
  
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Employee ID</th>");
		toClient.println("<th>First Name </th>");
		toClient.println("<th>Last Name </th>");
		
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
		
		//boton
		toClient.println( "<form method='get' action='QualificationEdit'> ");
			toClient.println("<input type= 'hidden' name= 'id' value = " + qualification.qualificationId + ">");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Edit Qualification's information</button>");
			toClient.println("</div>" );
		toClient.println("</form>" );

 
		toClient.println(Utils.footer());
			
        

        
    }
}
