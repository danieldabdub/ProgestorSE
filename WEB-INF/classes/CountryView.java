import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class CountryView extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String idStr = req.getParameter("id");
        
        CountryData country = CountryData.getCountry(connection, idStr);
        
        toClient.println(Utils.header("Country " + country.countryName));
        
        toClient.println("<body>");
        
        countryBasic(toClient, connection, country);
        
        countryEmployees(toClient, connection, country);
        
        toClient.println("</body>");
        toClient.println(Utils.footer());
        toClient.close();
    }
    
    
    public static void countryBasic(PrintWriter toClient, Connection connection, CountryData country) {
      
        
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Country Name</th>");
		toClient.println("<th>Processing Time</th>");
        toClient.println("<th>Validity Time</th>");
        toClient.println("</tr>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td><a href='CountryView?id=" + country.countryName + "'>"+ country.countryName +"</a></td>");
        toClient.println("<td>" + country.processingTime + " </td>");
        toClient.println("<td>" + country.validityTime + " </td>");
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        
        toClient.println( "<form method='get' action='CountryEdit'> ");
		toClient.println( "<input type='hidden' name='id' value= " + country.countryName + ">");
        toClient.println("<div style='text-align: right; '>" );
        toClient.println("<button  class='button button1' type='submit'>Edit Country's information</button>");
        toClient.println("</div>" );
		toClient.println("</form>" );
        
    }
    
    public static void countryEmployees(PrintWriter toClient, Connection connection, CountryData country) {
		toClient.println("<input type='hidden' id='countryId' value='" + country.countryName + "'> </input>");
        toClient.println("<div align='center' id='countryEmployees'></div>");
        toClient.println("<div style='text-align:right;'>");
        toClient.println("<button class='button button1' id='button1' type='submit' onclick='viewCountryEmployees()'>View Employees Available for " + country.countryName +"</button>");
        toClient.println("</div>");
		toClient.println("<script src=showCountryEmployees.js></script>");
    }    
}

	

