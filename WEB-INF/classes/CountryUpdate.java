import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class CountryUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        
        // No estoy tan seguro de que sea con el ID asi
        
        String idStr = req.getParameter("countryName");

		
        CountryData country = new CountryData(
                    req.getParameter("countryName"),
                    Integer.parseInt(req.getParameter("processingTime")),
					Integer.parseInt(req.getParameter("validityTime"))
                );
				
        int n = CountryData.updateCountry(connection, country);
        
        res.sendRedirect("CountryList");
    }
}