import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ClientUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String idStr = req.getParameter("clientId");

		
        ClientData client = new ClientData(
		//ahora recogeré la info de la pantalla segun el name="__" asignado dentro de cada input
            
                    req.getParameter("clientId"),
                    req.getParameter("companyName"),
					req.getParameter("countryHq"),
					req.getParameter("contract"),
					req.getParameter("startDate"),
					req.getParameter("phone"),
					req.getParameter("mail"),
                );
				
    int n = ClientData.updateClient(connection, client);
    res.sendRedirect("ClientView?id=" + idStr + "&a=" + Math.random());
        
    }
}