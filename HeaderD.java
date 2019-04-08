public class HeaderD {
    public static String HeaderD(String title) {
        StringBuilder str = new StringBuilder();
        str.append("<head>");
        str.append("<A HREF='Menu.html'>");
        str.append("<IMG SRC='Logo.png' style='left;width:266,7px;height:150px;'>");
        str.append("</A>");
        str.append("<div class='dropdown' style='float:right;'>");
        str.append("<button class='dropbtn'><b>__<br>__<br>__</button>");
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
        str.append("<div class='header'>");
        str.append("<h2>Dashboard</h2>");
        str.append("</div>");
        str.append("<title>"+ title +"</title>");
        return str.toString();
    }
}