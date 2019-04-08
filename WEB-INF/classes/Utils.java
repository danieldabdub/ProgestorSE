public class Utils {
    public static String header(String title) {
        StringBuilder str = new StringBuilder();
        str.append("<head>");
        str.append("<A HREF='Menu.html'>");
        str.append("<IMG SRC='logo.png' style='left;width:266,7px;height:150px;'>");
        str.append("</A>");
        str.append("<title>"+ title +"</title>");
        str.append("<link rel='stylesheet' type='text/css' href='style.css'></head>");
        str.append("<div class='dropdown' style='float: right;'>");
        str.append("<button  class='dropbtn'><b>__<br>__<br>__</b></button>");
        str.append("<div class='dropdown-content'>");
        str.append("<a href='Search.html'>Search</a>");
        str.append("<a href='Notifications.html'>Notifications</a>");
        str.append("<a href='Dashboard.html'>Dashboard</a>");
        str.append("<a href='Projects.html'>Projects</a>");
        str.append("<a href='Clients.html'>Clients</a>");
        str.append("<a href='Employees.html'>Employees</a>");
        str.append("<a href='Qualifications.html'>Qualifications</a>");
        str.append("</div>");
        str.append("</div>");           
        str.append("<h1 align ='center'>" + title + "</h1>");
        return str.toString();
    }
    
    public static String headerD(String title) {
        StringBuilder str = new StringBuilder();
        str.append("<head>");
        str.append("<A HREF='Menu.html'>");
        str.append("<IMG SRC='logo.png' style='left;width:266,7px;height:150px;'>");
        str.append("</A>");
        str.append("<title>"+ title +"</title>");
        str.append("<link rel='stylesheet' type='text/css' href='style.css'></head>");
        str.append("<link rel='stylesheet' type='text/css' href='style.css'>")
        
        str.append("<div class='dropdown' style='float: right;'>");
        str.append("<button  class='dropbtn'><b>__<br>__<br>__</b></button>");
        str.append("<div class='dropdown-content'>");
        str.append("<a href='Search.html'>Search</a>");
        str.append("<a href='Notifications.html'>Notifications</a>");
        str.append("<a href='Dashboard.html'>Dashboard</a>");
        str.append("<a href='ProjectList'>Projects</a>");
        str.append("<a href='Clients.html'>Clients</a>");
        str.append("<a href='Employees.html'>Employees</a>");
        str.append("<a href='Qualifications.html'>Qualifications</a>");
        str.append("</div>");
        str.append("</div>");           
        str.append("<h1 align ='center'>" + title + "</h1>");
        return str.toString();
    }
    
    
    public static String footer(){
        StringBuilder str = new StringBuilder();
        str.append("<div style='text-align: right;' class='navbardash'>");
        str.append("<a href='#'><b>About Us</b></a>");
        str.append("<a href='Search.html'>Search</a>");
        str.append("<a href='Notifications.html'>Notifications</a>");
        str.append("<a href='Dashboard.html'>Dashboard</a>");
        str.append("<a href='ProjectList'>Projects</a>");
        str.append("<a href='Clients.html'>Clients</a>");
        str.append("<a href='Employees.html'>Employees</a>");
        str.append("<a href='Qualifications.html'>Qualifications</a>");
        str.append("<p> <font color='white'>Sancho el sabio nº8 8 Izq. </font></p>");
        str.append("<p> <font color='white'>654-321-000 </font></p>");
        str.append("<p> <font color='white'>help@progestor.es</font></p>");
        return str.toString();
		
		
    }
	

    }

