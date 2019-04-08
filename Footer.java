public class Footer {
    
    public static String Footer() {
        StringBuilder str = new StringBuilder();
        str.append("<div style='text-align: right;' class='navbar'>");
        str.append("<a href='#'><b>About Us</b></a>");
        str.append("<a href='Search.html'>Search</a>");
        str.append("<a href='Notifications.html'>Notifications</a>");
        str.append("<a href='Dashboard.html'>Dashboard</a>");
        str.append("<a href='Projects.html'>Projects</a>");
        str.append("<a href='Clients.html'>Clients</a>");
        str.append("<a href='Employees.html'>Employees</a>");
        str.append("<a href='Qualifications.html'>Qualifications</a>");
        str.append("<p> <font color='white'>Sancho el sabio nº8 8 Izq. </font></p>");
        str.append("<p> <font color='white'>654-321-000 </font></p>");
        str.append("<p> <font color='white'>help@progestor.es</font></p>");
        str.append("<p> <font color='white'>help@progestor.es</font></p>");
        return str.toString();
    }
}