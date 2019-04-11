import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class ProjectInsert extends HttpServlet {
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
        
		
		toClient.println(Utils.header("New Project "));
		
        toClient.println("<form action='ProjectNew' method='GET'>");
		
        toClient.println("<table id='newProjectTable' class='Table1'>");
        toClient.println("<tr><td>ProjectId </td>");
        toClient.println("<td><input name='projectId'></input></td></tr>");
        
		toClient.println("<tr><td>Client</td>");
        toClient.println("<td><select id='companyName' name='clientId'></select></td></tr>");
		toClient.println("<input type='hidden' id='selectedClient' name='selectedClientId'>");
		
		
		toClient.println("<tr><td>Manager</td>");
        toClient.println("<td><select id='manager' name='managerId' ></select></td>");
		toClient.println("<input type='hidden' id='selectedManager' name='managerId'>");		
		
        toClient.println("<tr><td>Start Date</td>");
        toClient.println("<td><input type='date' name='startDate'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Status</td>");
        toClient.println("<td><input name='status'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>DueDate</td>");
        toClient.println("<td><input type='date' name='dueDate'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Country</td>");
        toClient.println("<td><select id='countryName' name='countryName'></td>");
		toClient.println("<input type='hidden' id='selectedCountry' name='countryName'>");
        toClient.println("</tr>");

        toClient.println("</table>");
		toClient.println("<div style='text-align: right;'>");
		toClient.println("<button class='button button1' type='submit'>Save</button>");
		toClient.println("</div>");
        toClient.println("</form>");
		
 
	
	    toClient.print("<script>clientName=[");
        Vector<ClientData> clientsList = ClientData.getClientList(connection);
        for(int i=0; i<clientsList.size(); i++){
                ClientData client = clientsList.elementAt(i);
                if (i!=0) {
                    toClient.print(",");
                    }
                toClient.print("{");
                toClient.print("\"clientId\":\"" + client.clientId + "\"");
                toClient.print(",\"companyName\":\"" + client.companyName + "\"");
                toClient.print(",\"countryHq\":\"" + client.countryHq + "\"");
                toClient.print("}");
		}
		
        toClient.println("]</script>");
        toClient.println("<script src=AddInfo.js></script>");
		
		
		toClient.print("<script>managerName=[");
        Vector<ManagerData> managerList = ManagerData.getManagerList(connection);
        for(int i=0; i< managerList.size(); i++){
                ManagerData manager = managerList.elementAt(i);
                if (i!=0) {
                    toClient.print(",");
                    }
                toClient.print("{");
                toClient.print("\"managerId\":\"" + manager.managerId + "\"");
                toClient.print(",\"managerName\":\"" + manager.first + manager.last + "\"");
                toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src=AddInfo.js></script>");
		
		
		
		toClient.print("<script>data=[");
        Vector<CountryData> countryList = CountryData.getCountryList(connection);
        for(int i=0; i< countryList.size(); i++){
                CountryData country = countryList.elementAt(i);
                if (i!=0) {
                    toClient.print(",");
                    }
                toClient.print("{");
                toClient.print("\"countryName\":\"" + country.countryName+ "\"");
				toClient.print(",\"processing\":\"" + country.processingTime +"\"");
				toClient.print(",\"validity\":\"" + country.validityTime+ "\"");
				
                toClient.print("}");
        }
        toClient.println("]</script>");
        toClient.println("<script src=AddInfo.js></script>");
		
		
		toClient.println(Utils.footer());
		toClient.close();
	}
}