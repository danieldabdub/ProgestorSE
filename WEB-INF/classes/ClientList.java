import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

public class ClientList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
		toClient.println(Utils.header("Clients"));
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Client ID</th>");
		toClient.println("<th>Company Name</th>");
		toClient.println("</thead>");
		
		
        Vector<ClientData> ClientList;
        ClientList= ClientData.getClientList(connection);
        for(int i=0; i< ClientList.size(); i++){
                ClientData client =ClientList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='ClientView?id=" + client.clientId + "'>"+ client.clientId +"</a></td>");
                toClient.println("<td>" + client.companyName + " </td>");
                toClient.println("</tr>");


        }
		
		toClient.println(Utils.footer());
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
		toClient.println("<form method='get' action='NewClient.html'>"); /*a donde se dirige*/
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Create Client</button>");
		toClient.println("</div>");
		toClient.println("</form>");
		toClient.println("</body>");
        toClient.close();
    }
}