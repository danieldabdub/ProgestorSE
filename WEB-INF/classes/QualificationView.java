import java.util.Vector;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;


public class QualificationView extends HttpServlet  {
   
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
		
		toClient.println(Utils.header("Qualification : " + qualif.qualification));
   
		
		//tabla
		
        toClient.println("<table class= 'Table1' id='qua' >");
        toClient.println("<tr><td>Description</td>");
        toClient.println("<td>" + qualif.description + "</td></tr>");
		
        toClient.println("</tr>");
        toClient.println("</table>");
		
		//boton
		toClient.println( "<form method='get' action='QualificationEdit'> ");
			toClient.println("<input type= 'hidden' name= 'id' value = " + qualif.qualificationId + ">");
			toClient.println("<div style='text-align: right; '>" );
				toClient.println("<button  class='button button1' type='submit'>Edit Qualification's information</button>");
			toClient.println("</div>" );
		toClient.println("</form>" );

 
		toClient.println(Utils.footer());
			
        

        
    }
}
