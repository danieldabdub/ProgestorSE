import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ProjectData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String projectId;
    String companyName;
    String last;
    String first;
    String status;
    Date startDate;
	Date dueDate;
    String countryName;
    
    
    // Primer Constructor: - getProjectList
    
    ProjectData (String projectId, String companyName, String first, String last, String status, Date dueDate){
        
        this.projectId = projectId;
		this.companyName = companyName;
        
        // PM Complete Name
        
        this.last = last;
		this.first = first;
		
        
		this.status = status;
        this.dueDate = dueDate;
        
    }
    
    // Segundo Constructor: - getActiveProjectList
    
    ProjectData (String projectId, String companyName, Date startDate, Date dueDate){
        
        this.projectId = projectId;
		this.companyName = companyName;
        this.startDate = startDate;
        this.dueDate = dueDate;
    
    }
    
    public static Vector<ProjectData> getProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        String sql = "SELECT projectId, companyName, last, first, status, dueDate FROM Projects, Clients, ProjectManagers WHERE Clients.clientId=Projects.clientId AND ProjectManagers.managerId=Projects.managerId;";

        System.out.println("getProjectList: "+ sql);
       
        try {
            
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
               
                ProjectData project = new ProjectData(
                    result.getString("projectId"),
					result.getString("companyName"),
					result.getString("last"),
					result.getString("first"),
					result.getString("status"),
					result.getDate("dueDate")
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
   
    public static Vector<ProjectData> getActiveProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        //ESCRIBIR SQL CON LA CONDICION DE QUE ESTE ACTIVO
        
        // Verificar el "In progress"
        
        String sql = "SELECT projectId, companyName, startDate, dueDate FROM Projects, Clients WHERE Clients.clientId=Projects.clientId AND status='In progress'";
        
        System.out.println("getActiveProjectList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                ProjectData project = new ProjectData(
                    result.getString("projectId"),
                    result.getString("companyName"),
                    result.getDate("startDate"),
                    result.getDate("dueDate")
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getActiveProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    // Ojo: cuando este listo Clients hacerlo.
    /*public static Vector<ProjectData> getClientProjectList(Connection connection, String clientId){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getClientProjectList: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, clientId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                ProjectData project = new ProjectData(
                    result.getString("projectId")
                );
                vec.addElement(project);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getClientProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }*/
    
    // Ojo: terminar
    
    public static ProjectData getProject(Connection connection, String projectId){
        
        
        //ESCRIBIR SQL!
        String sql = "";
        System.out.println("getProject: " + sql);
        
        ProjectData project = null;
        
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, projectId);
//            
//            ResultSet result = pstmt.executeQuery();
//            
//            if(result.next()){
//                project = new ProjectData(
//                    //ME FALTARIAN COSAS AQUI!
//                    result.getString("projectId")
//                );  
//            }
//            result.close();
//            pstmt.close();
//        } catch(SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error in getProject: " + sql + " Exception: " + e);
//        }
        
        return project;
        
    }
    
    public static int updateProject(Connection connection, ProjectData project){
        //ESCRIBIR SQL!!!!
        String sql="";
        System.out.println("updateProject: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
//            stmtUpdate.setString(1,project.projectId);
//            stmtUpdate.setInt(2,project.supplierId);
//            stmtUpdate.setFloat(3,project.unitPrice);
//            stmtUpdate.setString(4,project.projectId); CAMBIAR ESTO!
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateProject: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}