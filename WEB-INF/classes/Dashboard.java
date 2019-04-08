import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.Date;

@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
//        String categoryId = req.getParameter("id");
// OJO! Aqui como seria el header?
        toClient.println(Utils.header("DashBoard"));
        toClient.println("<h1>Dashboard</h1>");
        Vector<ProjectData> activeProjectList;
        
        activeProjectList = ProjectData.getActiveProjectList(connection);
        
        toClient.println("<div class='gantt'>");
        toClient.println("<div class='gantt__row gantt__row--months'>");
        toClient.println("<div class='gantt__row-first'></div>");
        toClient.println("<span>Jan</span><span>Feb</span><span>Mar</span>");
        toClient.println("<span>Apr</span><span>May</span><span>Jun</span>");
        toClient.println("<span>Jul</span><span>Aug</span><span>Sep</span>");
        toClient.println("<span>Oct</span><span>Nov</span><span>Dec</span>");
        toClient.println("</div>");
        
        // OJO CON ESTE 5 ABAJO!
        
        toClient.println("<div class='gantt__row gantt__row--lines' data-month='5'>");
        toClient.println("<span></span><span></span><span></span>");
        toClient.println("<span></span><span></span><span></span>");
        toClient.println("<span></span><span></span><span></span>");
        toClient.println("<span></span><span></span><span></span>");
        toClient.println("</div>");
        
        for(int i=0; i< activeProjectList.size(); i++){
            
                ProjectData project = activeProjectList.elementAt(i);
            
            // OJO CON EL CLIENT ID! y OJO! Aqui tuviera que hacer otro constructor!
            // Seria interesante cambiar de color!
            
                toClient.println("<div class='gantt__row'>");
                toClient.println("<div class=gantt__row-first'>" + project.clientId);
                toClient.println("</div>");
            
                toClient.println("<ul class='gantt__row-bars'>");
            
// Ojo aqui con los MESES!

// Seria interesante jugar con los colores
            
                toClient.println("<li style='grid-column: "+ project.StartMonth +"/"+ project.DueMonth+"; background-color: #2ecaac;'>" + project.projectId + "</li></ul></div>");
                
        }

        toClient.println("</div>");
        
        toClient.println(Utils.footer("Dashboard"));
        toClient.close();
    }
}