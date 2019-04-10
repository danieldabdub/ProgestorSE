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
    int startMonth;
    int dueMonth;
    
    
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
    
    ProjectData (String projectId, String companyName, int startMonth, int dueMonth){
        
        this.projectId = projectId;
		this.companyName = companyName;
        this.startMonth = startMonth;
        this.dueMonth = dueMonth;
    
    }
	
	//Tercer Constructor: - ProjectUpdate/ProjectEdit
    
	    ProjectData (String projectId, String companyName,String first, String last, Date startDate, String status, Date dueDate){
        
        this.projectId = projectId;
		this.companyName = companyName;
		this.first=first;
		this.last=last;
        this.startDate = startDate;
		this.status=status;
        this.dueDate = dueDate;
    
    }
	  ProjectData (String projectId, String companyName,String first, String last, Date startDate, String status, Date dueDate, String countryName){
        
        this.projectId = projectId;
		this.companyName = companyName;
		this.first=first;
		this.last=last;
        this.startDate = startDate;
		this.status=status;
        this.dueDate = dueDate;
		this.countryName=countryName;
    
    }
    
    
    public static Vector<ProjectData> getProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        String sql = "SELECT projectId, companyName, first, last, status, dueDate FROM Projects, Clients, ProjectManagers WHERE Clients.clientId=Projects.clientId AND ProjectManagers.managerId=Projects.managerId;";

        System.out.println("getProjectList: "+ sql);
       
        try {
            
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
               
                ProjectData project = new ProjectData(
                    result.getString("projectId"),
					result.getString("companyName"),
					result.getString("first"),
					result.getString("last"),
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
        
        String sql = "SELECT projectId, companyName, MONTH(startDate) AS startMonth, MONTH(dueDate) AS dueMonth FROM Projects, Clients WHERE Clients.clientId=Projects.clientId AND status='In progress'";
        
        System.out.println("getActiveProjectList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                ProjectData project = new ProjectData(
                    result.getString("projectId"),
                    result.getString("companyName"),
                    Integer.parseInt(result.getString("startMonth")),
                    Integer.parseInt(result.getString("dueMonth"))
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
    
//    public static Vector<ProjectData> getClientProjectList(Connection connection, String clientId){
//        
//        Vector<ProjectData> vec = new Vector<ProjectData>();
//        
//        //ESCRIBIR SQL
//        
//        String sql = "SELECT ";
//        System.out.println("getClientProjectList: " + sql);
//        
//        try {
//            PreparedStatement pstmt=connection.prepareStatement(sql);
//            pstmt.setString(1, clientId);
//            
//            ResultSet result = pstmt.executeQuery();
//            
//            while(result.next()) {
//                
//                ProjectData project = new ProjectData(
//                    result.getString("projectId")
//                );
//                vec.addElement(project);
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error in getClientProjectList: " + sql + " Exception: " + e);
//        }
//        return vec;
//    }
    
    // Ojo: terminar
    
    public static ProjectData getProject(Connection connection, String projectId){
        String sql = "SELECT projectId, companyName, first, last, startDate, status, dueDate, countryName FROM Projects, Clients, ProjectManagers WHERE Clients.clientId=Projects.clientId AND ProjectManagers.managerId=Projects.managerId";
        sql += " AND projectId=?";

        System.out.println("getProject: " + sql);
        
        ProjectData project = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, projectId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                project = new ProjectData(
                    result.getString("projectId"),
					result.getString("companyName"),
					result.getString("first"),
					result.getString("last"),
					result.getDate("startDate"),
					result.getString("status"),
					result.getDate("dueDate"),
					result.getString("countryName")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProject: " + sql + " Exception: " + e);
        }
        
        return project;
        
    }
    
    public static int updateProject(Connection connection, ProjectData project){
        String sql="UPDATE Projects SET companyName = ?, first = ?, last = ?, stardate=?, status = ?, dueDate = ?, countryName = ? WHERE projectId = ?";
        System.out.println("updateProject: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
           stmtUpdate.setString(1,project.companyName);
           stmtUpdate.setString(2,project.first);
           stmtUpdate.setString(3,project.last);
		   stmtUpdate.setDate(4,project.startDate);
           stmtUpdate.setString(5,project.status);
		   stmtUpdate.setDate(6,project.dueDate);
		   stmtUpdate.setString(7,project.countryName);
		   stmtUpdate.setString(8,project.projectId);
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateProject: " + sql + " Exception: " + e);   
        }
        return n;
    }
	
	public static int updateProject(Connection connection, ProjectData project){
        String sql="INSERT INTO Projects VALUES(?,?,?,?,?,?,?,?)";
        System.out.println("updateProject: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
			
           stmtUpdate.setString(1,project.projectId) 
           stmtUpdate.setString(2,project.companyName);
           stmtUpdate.setString(3,project.first);
           stmtUpdate.setString(4,project.last);
		   stmtUpdate.setDate(5,project.startDate);
           stmtUpdate.setString(6,project.status);
		   stmtUpdate.setDate(7,project.dueDate);
		   stmtUpdate.setString(8,project.countryName);
		   ;
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateProject: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}