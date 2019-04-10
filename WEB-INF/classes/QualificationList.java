import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

public class QualificationList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		
        toClient.println(Utils.header("Qualifications"));
		toClient.println("<table class='Table1'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th>Qualification</th>");
		toClient.println("<th>Description</th>");
		
		
        Vector<QualificationData> QualificationList;
        QualificationList= QualificationData.getQualificationList(connection);
        for(int i=0; i< QualificationList.size(); i++){
                QualificationData qua =QualificationList.elementAt(i);
                toClient.println("<tr>");
				toClient.println("<td><a href='QualificationView?id=" + qua.qualification + "'>"+ qua.qualification +"</a></td>");
                toClient.println("<td>" + qua.description + " </td>");
                toClient.println("</tr>");


        }
		
		toClient.println(Utils.footer());
		toClient.println("</tbody>");
		toClient.println("</tr>");
        toClient.println("</table>");
		toClient.println("<form method='get' action='NewQualification.html'>"); /*a donde se dirige*/
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Create Qualification</button>");
		toClient.println("</div>");
		toClient.println("</form>");
		toClient.println("</body>");
        toClient.close();
    }
}