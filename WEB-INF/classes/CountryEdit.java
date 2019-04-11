import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class CountryEdit extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println("<form action='CountryUpdate' method='GET'>");
        
        String idStr = req.getParameter("id");

        CountryData country = CountryData.getCountry(connection, idStr);
		
		toClient.println(Utils.header("Edit Country " + country.countryName));
		
        toClient.println("<form action='CountryUpdate' method='GET'>");
		
        toClient.println("<table class='Table1'>");
        
        toClient.println("<tr><td>Country Name</td>");
        toClient.println("<td><input name='countryName' value='" + country.countryName + "'></td></tr>");
        
		toClient.println("<tr><td>Processing Time</td>");
        toClient.println("<td><input name='processingTime' value='" + country.processingTime + "'></td></tr>");

		toClient.println("<tr><td>Validity Time</td>");
        toClient.println("<td><input name='validityTime' value='" + country.validityTime + "'></td>");		
	
        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Submit</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer());
        toClient.close();
    }
}