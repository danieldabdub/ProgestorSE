import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class QualificationEdit extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

		//desde ProjectView, al darle al boton paso un hidden value llamado "id".
        String idStr = req.getParameter("id");
		

        QualificationData qualif = QualificationData.getQualification(connection, idStr);
		
		toClient.println(Utils.header("Edit Qualification: " + qualif.qualification));
		
        toClient.println("<form action='QualificationUpdate' method='GET'>");
		
        toClient.println("<table class='Table1'>");
        toClient.println("<tr><td>QualificationId</td>");
        toClient.println("<td><input  name='qualificationId'  value='" + qualif.qualificationId + "'></td></tr>");
        
		toClient.println("<tr><td>Qualification</td>");
        toClient.println("<td><input  name='qualificationName'  value=' " + qualif.qualification + "'></td></tr>");

		toClient.println("<tr><td>Description</td>");
        toClient.println("<td><input name='description' value='" + qualif.description + "'></td>");		
		

        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1'  type='submit'>Submit</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer());
        toClient.close();
    }
}