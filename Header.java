public class Header {
    public static String Header(String title) {
        StringBuilder str = new StringBuilder();
        str.append("<head>");
        str.append("<A HREF='Menu.html'>");
        str.append("<IMG SRC='Logo.png' style='left;width:266,7px;height:150px;'>");
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
}