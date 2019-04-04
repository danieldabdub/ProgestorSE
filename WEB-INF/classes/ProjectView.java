import java.util.Vector;

public class ProjectView {
    public static String projectHeader(ProjectData project) {
        StringBuilder str = new StringBuilder();
        str.append("<!DOCTYPE HTML>");
        str.append("<table border='0'>");
        str.append("<tr><td>Id</td>");
        str.append("<td>" + project.projectId + "</td></tr>");
		//modificar datos siguientes
        
		/*
		str.append("<tr><td>Client</td>");
        str.append("<td>" + project.clientId + "</td>");
        str.append("<tr><td>Date</td>");
        str.append("<td>" + project.dueDate + "</td>");
		*/
		
        str.append("</tr>");
        str.append("</table>");
        return str.toString();
    }

    public static String projectDetail(Vector<ProjectDetailData> projectDetail) {
        StringBuilder str = new StringBuilder();
        str.append("<table border='0'>");
        for(int i=0; i< projectDetail.size(); i++){
            ProjectDetailData detail = projectDetail.elementAt(i);
            str.append("<tr>");
            str.append("<td>" + detail.projectId + " </td>");
			//modificar datos siguientes
			
            /*
			str.append("<td>" + detail. + " </td>");
            str.append("<td class='number'>" + detail.unitPrice + " </td>");
            str.append("<td class='number'>" + detail.quantity + " </td>");
            str.append("<td class='number'>" + detail.discount + " </td>");
            //str.append("<td><a href='EditProject?id=" + detail.projectId + "'>Edit</a></td>");
            str.append("<td><img style='height:50px;' src='http://northbrick1.appspot.com/images/" + detail.projectId + ".png'></td>");
            str.append("</tr>"); 
			*/
        }
        str.append("</table>");
        return str.toString();
    }
}
