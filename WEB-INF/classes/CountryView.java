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
   
         
        //tabla 1
         
        toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Country Name</th>");
		toClient.println("<th>Processing Time</th>");
        toClient.println("<th>Validity Time</th>");
        toClient.println("<tr>");
        toClient.println("<td><a href='CountryView?id=" + country.countryName + "'>"+ country.countryName +"</a></td>");
        toClient.println("<td>" + country.processingTime + " </td>");
        toClient.println("<td>" + country.validityTime + " </td>");
        toClient.println("</tr>");

        toClient.println("<br>");
        toClient.println("<br>");
         
		
		//tabla 2
		
        toClient.println("<table class= 'Table2' id='countryName' >");
  
		toClient.println("<table class='Table2'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Employee ID</th>");
		toClient.println("<th>First Name </th>");
		toClient.println("<th>Last Name </th>");
        toClient.println("</tbody>");
        toClient.println("</table>");
		
        Vector<EmployeeData> EmployeeList;
        EmployeeList= EmployeeData.getCountryEmployeeList(connection, country.countryName);
        for(int i=0; i< EmployeeList.size(); i++){
                EmployeeData employee =EmployeeList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='EmployeeView?id=" + employee.employeeId + "'>"+ employee.employeeId +"</a></td>");
                toClient.println("<td>" + employee.firstName + " </td>");
				toClient.println("<td>" + employee.lastName + " </td>");
                toClient.println("</tr>");


        }
		
		toClient.println("</tbody>");
        toClient.println("</table>");
         
         
        //boton
         
        toClient.println( "<form method='get' action='CountryEdit'> ");
        toClient.println("<div style='text-align: right; '>" );
        toClient.println("<button  class='button button1' type='submit'>Edit Country's information</button>");
        toClient.println("</div>" );
		toClient.println("</form>" );
         
        toClient.println(Utils.footer());
		toClient.println("</body>");
        toClient.close();
        
    }
}
