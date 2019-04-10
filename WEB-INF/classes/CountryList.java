import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

public class CountryList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
        toClient.println(Utils.header("Countries"));
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Country Name</th>");
		toClient.println("<th>Processing Time</th>");
        toClient.println("<th>Validity Time</th>");
		
		
        Vector<CountryData> CountryList;
        CountryList= CountryData.getCountryList(connection);
        for(int i=0; i< CountryList.size(); i++){
                CountryData country =CountryList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='CountryView?id=" + country.countryName + "'>"+ country.countryName +"</a></td>");
                toClient.println("<td>" + country.processingTime + " </td>");
                toClient.println("<td>" + country.validityTime + " </td>");
                toClient.println("</tr>");


        }
		
		toClient.println(Utils.footer());
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
		toClient.println("<form method='get' action='NewCountry.html'>"); /*a donde se dirige*/
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Add Country</button>");
		toClient.println("</div>");
		toClient.println("</form>");
		toClient.println("</body>");
        toClient.close();
    }
}