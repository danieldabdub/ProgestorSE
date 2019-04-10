import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class menu extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        
        String managerId = req.getParameter("managerId");
        
        
        
        toClient.println(Utils.header("Home"));
        
        toClient.println("<body>");
        toClient.println("<div class='navbar-inner'>");
        toClient.println("<div class='container'>");
        toClient.println("<a class='btn btn-navbar' data-toggle='collapse' data-target='.nav-collapse'>");
        toClient.println("<span class='icon-bar'></span>");
        toClient.println("<span class='icon-bar'></span>");
        toClient.println("<span class='icon-bar'></span>");
        toClient.println("<A HREF='Menu.html' >");
        toClient.println("<IMG SRC='logoMenu.png' style='float:left;width:798px;height:450px;'></A>");
        toClient.println("<div class='nav-collapse collapse pull-right'>");
        toClient.println("<ul class='nav'></ul></div></div></div>");
        toClient.println("<div class='main'>");
        toClient.println("<br><br><br><br>");
        toClient.println("<h1><font size = '30'>Welcome "+ managerId +"</font></h1></div>");
        toClient.println("");
        toClient.println("");
        toClient.println("");
        
        toClient.println(Utils.footer());
    }
}