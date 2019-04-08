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
    String first;
    String last;
    String status;
    
    
    // Primer Constructor
    //PONER TODOS LOS QUE FALTAN ABAJO
    ProjectData (String projectId, String companyName, String first, String last, String status){
        this.projectId = projectId;
		this.companyName = companyName;
		this.first = first;
		this.last = last;
		this.status = status;
    }
    
    public static Vector<ProjectData> getProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        String sql = "SELECT projectId, companyName, first, last, status FROM Projects, Clients, ProjectManagers WHERE Clients.clientId=Projects.clientId AND ProjectManagers.managerId=Projects.managerId;";

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
					result.getString("status")
					
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
   
    public static Vector<ProductData> getActiveProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        //ESCRIBIR SQL CON LA CONDICION DE QUE ESTE ACTIVO
        
        String sql = "";
        System.out.println("getActiveProjectList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                ProjectData project = new ProjectData(
                    result.getString("projectId")
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getActiveProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    
    public static Vector<ProjectData> getClientProjectList(Connection connection, String clientId){
        
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
    }
    
    public static Vector<ProjectData> getProject(Connection connection, String projectId){
        
        
        //ESCRIBIR SQL!
        String sql = "";
        System.out.println("getProject: " + sql);
        
        ProductData product = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, projectId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                project = new ProjectData(
                    //ME FALTARIAN COSAS AQUI!
                    result.getString("projectId")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProduct: " + sql + " Exception: " + e);
        }
        
        return project;
        
    }
    
    public static int updateProject(Connection connection, ProjectData project){
        //ESCRIBIR SQL!!!!
        String sql="";
        System.out.println("updateProject: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setString(1,project.projectId);
//            stmtUpdate.setInt(2,product.supplierId);
//            stmtUpdate.setFloat(3,product.unitPrice);
//            stmtUpdate.setString(4,product.productId); CAMBIAR ESTO!
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateProject: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}