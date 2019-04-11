import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class QualificationView extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
       res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String idStr = req.getParameter("id");
        
        QualificationData qualif = QualificationData.getQualification(connection, idStr);
        
        toClient.println(Utils.header("Qualification " + qualif.qualification));
        
        toClient.println("<body>");
        
        qualifBasic(toClient, connection, qualif);
        qualifEmployees(toClient, connection, qualif);
       
        
        toClient.println("</body>");
        toClient.println(Utils.footer());
        toClient.close();
    }
    
    
    public static void qualifBasic(PrintWriter toClient, Connection connection, QualificationData qualif) {
        
        
        toClient.println("<table class='Table1'>");   
        toClient.println("<thead>");
        toClient.println("<tr>");
        toClient.println("<th>QualificationID</th>");
        toClient.println("<th>Qualification</th>");
        toClient.println("<th>Description</th>");
        toClient.println("</tr>");
        toClient.println("<tbody>");
        toClient.println("<tr>");
        toClient.println("<td>" + qualif.qualificationId + "</td>");
        toClient.println("<td>" + qualif.qualification + "</td>");   
        toClient.println("<td>" + qualif.description + "</td>");
        toClient.println("</tr>");
        toClient.println("</tbody>");
        toClient.println("</table>");
        
        toClient.println( "<form method='get' action='QualificationEdit'> ");
        toClient.println("<input type= 'hidden' name= 'id' value = " + qualif.qualificationId + ">");
        toClient.println("<div style='text-align: right; '>" );
        toClient.println("<button  class='button button1' type='submit'>Edit Qualification's information</button>");
        toClient.println("</div>" );
		toClient.println("</form>" );
        
    }
    
    public static void qualifEmployees(PrintWriter toClient, Connection connection, QualificationData qualif) {
        
		toClient.println("<input type='hidden' id='qualif' value='" + qualif.qualificationId + "'> </input>");
        toClient.println("<div align='center' id='qualifiedEmployees'></div>");
        toClient.println("<div style='text-align:right;'>");
        toClient.println("<button class='button button1' id='button1' type='submit' onclick='viewQualifEmployees()'>View qualified employees</button>");
        toClient.println("</div>");
		toClient.println("<script src=showQualifiedEmployees.js></script>");
    }   
}	
    