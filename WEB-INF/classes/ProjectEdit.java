

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ProductEdit extends HttpServlet {
    Connection connection;

	//the method init is executed when the servlet starts working
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header("Edit Project"));
        toClient.println("<form action='ProjectUpdate' method='GET'>");
        toClient.println("<table border='1'>");
		
		//desde ProjectView, al darle al boton paso un hidden value llamado "id".
        String idStr = req.getParameter("id");
		
		//i call the method getProduct from the ProductData (in ProductData.java file)  class to grab info from the DATABASE 
		//in the method definition we defined two parameters:
		//1. connection =to establish connection with the DATABASE
		//2. idStr = an identifier that I will use to execute a pre-made SQL-SELECT instruction to get info from the Database
        ProjectData project = ProjectData.getProject(connection, idStr);
		
        toClient.println("<tr><td>ProjectId</td>");
        toClient.println("<td><input name='projectId' value='" + project.projectId + "'></td></tr>");
        toClient.println("<tr><td>Client</td>");
		
        String client = project.client;
		
        System.out.println("Client: " + client);
		
		//----------------me quede aqui.
        name = name.replace("'","&#39;");
        System.out.println("Name: " + name);
        toClient.println("<td><input name='productName' value='" + name + "'></td></tr>");
		
        toClient.println("<tr><td>Supplier</td>");
        toClient.println("<td><input name='supplierId' value='" + product.supplierId + "'></td>");
		
        toClient.println("<tr><td>Price</td>");
        toClient.println("<td><input name='unitPrice' value='" + product.unitPrice + "'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>categoryId</td>");
        toClient.println("<td><input name='CategoryId' value='" + product.CategoryId + "'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>QuantityPerUnit</td>");
        toClient.println("<td><input name='QuantityPerUnit' value='" + product.QuantityPerUnit + "'></td>");
        toClient.println("</tr>");
		
		 toClient.println("<tr><td>UnitsInStock</td>");
        toClient.println("<td><input name='UnitsInStock' value='" + product.UnitsInStock + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>UnitsOnOrder</td>");
        toClient.println("<td><input name='UnitsOnOrder' value='" + product.UnitsOnOrder + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>ReorderLevel</td>");
        toClient.println("<td><input name='ReorderLevel' value='" + product.ReorderLevel + "'></td>");
        toClient.println("</tr>");
		
		toClient.println("<tr><td>Discontinued</td>");
        toClient.println("<td><input name='Discontinued' value='" + product.Discontinued + "'></td>");
        toClient.println("</tr>");
		
        toClient.println("<tr><td>Image</td>");
        toClient.println("<td><img src='http://northbrick1.appspot.com/images/" + product.productId + ".png'></td>");
        toClient.println("</tr>");
		
        toClient.println("</table>");
        toClient.println("<input type='submit'>");
        toClient.println("</form>");
        toClient.println(Utils.footer("Product Form"));
        toClient.close();
    }
}