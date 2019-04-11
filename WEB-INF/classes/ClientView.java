import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ClientView extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String clientId = req.getParameter("id");
        toClient.println(Utils.header("Client " + clientId));
        toClient.println("<body>");
        clientBasic(toClient, connection, clientId);
        clientProjects(toClient, connection, clientId);
        toClient.println("</body>");
        toClient.println(Utils.footer());
        toClient.close();
    }
    
    
    public static void clientBasic(PrintWriter toClient, Connection connection, String clientId) {
        ClientData client = ClientData.getClient(connection, clientId);  
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>Company Name</th>");
        toClient.println("<th>Headquarters</th>");
        toClient.println("<th>Contact</th>");
        toClient.println("<th>Phone</th>");
        toClient.println("<th>Mail</th>");
        toClient.println("</tr>");
        toClient.println("</thead>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td>" + client.companyName + "</td>");
        toClient.println("<td>" + client.countryHq + "</td>");   
        toClient.println("<td>" + client.phone + "</td>");
        toClient.println("<td>" + client.mail + "</td>");
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        
        toClient.println("<form method='get' action='ClientEdit'>");
        toClient.println("<input type='hidden' name='clientId' value='" + clientId + "'>");
        toClient.println("<div style='text-align:right;'> <button class='button button1' type='submit'> Edit client's information </button></div>");
        toClient.println("</form>");
        
    }

    public static void clientProjects(PrintWriter toClient, Connection connection, String clientId) {
		toClient.println("<input type='hidden' id='clId' value='" + clientId + "'> </input>");
        toClient.println("<div align='center' id='clientProjects'></div>");
        toClient.println("<div style='text-align:right;'>");
        toClient.println("<button class='button button1' id='button1' type='submit' onclick='viewClientProjects()'>View client's projects</button>");
        toClient.println("</div>");
		toClient.println("<script src=showClientProjects.js></script>");
    }    
           
}
