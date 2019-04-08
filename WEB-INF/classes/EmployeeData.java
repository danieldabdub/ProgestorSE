import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EmployeeData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String employeeId;
    String firstName;
    String lastName;
    
    
    // Primer Constructor
    //PONER TODOS LOS QUE FALTAN ABAJO
    EmployeeData (String employeeId){
        this.employeeId = employeeId;
    }
    
/*     public static Vector<EmployeeData> getEmployeeList(Connection connection){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getEmployeeList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId")
                );
                
                vec.addElement(employee);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getEmployeeList: " + sql + " Exception: " + e);
        }
        return vec;
    } */
    
    public static Vector<EmployeeData> getProjectEmployeeList(Connection connection, String projectId){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getProjectEmployeeList: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, projectId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId")
                );
                vec.addElement(employee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProjectEmployeeList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static Vector<EmployeeData> getEmployee(Connection connection, String employeeId){
        
        
        //ESCRIBIR SQL!
        String sql = "";
        System.out.println("getEmployee: " + sql);
        
        EmployeeData employee = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                employee = new EmployeeData(
                    //ME FALTARIAN COSAS AQUI!
                    result.getString("employeeId")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getEmployee: " + sql + " Exception: " + e);
        }
        
        return employee;
        
    }
    
    public static int updateEmployee(Connection connection, EmployeeData employee){
        //ESCRIBIR SQL!!!!
        String sql="";
        System.out.println("updateEmployee: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
//            stmtUpdate.setString(1,employee.employeeId);
//            stmtUpdate.setInt(2,product.supplierId);
//            stmtUpdate.setFloat(3,product.unitPrice);
//            stmtUpdate.setString(4,product.productId); CAMBIAR ESTO!
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateEmployee: " + sql + " Exception: " + e);   
        }
        return n;
    }
    
    public static Vector<EmployeeData> getQualifiedEmployee(Connection connection, String qualificationId){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getQualifiedEmployee: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, qualificationId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId")
                );
                vec.addElement(employee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getQualifiedEmployee: " + sql + " Exception: " + e);
        }
        return vec;
    }
        
}
