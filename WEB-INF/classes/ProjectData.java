import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ProjectData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    int projectId;
    String manager;
    String status;
    
    
    // Primer Constructor
    //PONER TODOS LOS QUE FALTAN ABAJO
    ProjectData (int projectId){
        this.projectId = projectId;
    }
    
    public static Vector<ProductData> getProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getProjectList: "+ sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                ProjectData project = new ProjectData(
                    Integer.parseInt(result.getString("projectId"))
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
                    Integer.parseInt(result.getString("projectId"))
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getActiveProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    
    public static Vector<ProjectData> getClientProjectList(Connection connection, int clientId){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getClientProjectList: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setInt(1, clientId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                ProjectData project = new ProjectData(
                    Integer.parseInt(result.getString("projectId"))
                );
                vec.addElement(project);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getClientProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static Vector<ProjectData> getProject(Connection connection, int projectId){
        
        
        //ESCRIBIR SQL!
        String sql = "";
        System.out.println("getProject: " + sql);
        
        ProductData product = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                project = new ProjectData(
                    //ME FALTARIAN COSAS AQUI!
                    Integer.parseInt(result.getString("projectId"))
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
            
            stmtUpdate.setString(1,product.productName);
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
