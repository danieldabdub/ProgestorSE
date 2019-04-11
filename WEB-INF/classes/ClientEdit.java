import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ClientEdit extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println("<form action='ClientUpdate' method='GET'>");
        
        String idStr = req.getParameter("clientId");

        ClientData client = ClientData.getClient(connection, idStr);
		
		toClient.println(Utils.header("Edit " + client.companyName));
		
        toClient.println("<form action='ClientUpdate' method='GET'>");
		
        toClient.println("<table class='Table1'>");
        toClient.println("<tr><td>clientId</td>");
        toClient.println("<td><input name='clientId' value='" + client.clientId + "'></td></tr>");
        
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td><input name='companyName' value='" + client.companyName + "'></td></tr>");

		toClient.println("<tr><td>HQ</td>");
        toClient.println("<td><input name='countryHq' value='" + client.countryHq + "'></td>");		
	
	    toClient.println("<tr><td>Contact</td>");
        toClient.println("<td><input name='contact' value='" + client.contact + "'></td>");
		
        toClient.println("<tr><td>Phone</td>");
        toClient.println("<td><input name='phone' value='" + client.phone + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Mail</td>");
        toClient.println("<td><input name='mail' value='" + client.mail + "'></td>");
        toClient.println("</tr>");

        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Submit</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer());
        toClient.close();
    }
}