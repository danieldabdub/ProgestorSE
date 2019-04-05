import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class QualificationData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String qualificationId;
    
    // Primer Constructor
    //PONER TODOS LOS QUE FALTAN ABAJO
    QualificationData (String qualificationId){
        this.qualificationId = qualificationId;
    }
    
    public static Vector<QualificationData> getQualificationList(Connection connection){
        
        Vector<QualificationData> vec = new Vector<QualificationData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getQualificationList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                QualificationData qualification = new QualificationData(
                    result.getString("qualificationId")
                );
                
                vec.addElement(qualification);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getQualificationList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static Vector<QualificationData> getQualification(Connection connection, String qualificationId){
        
        
        //ESCRIBIR SQL!
        String sql = "";
        System.out.println("getQualification: " + sql);
        
        QualificationData qualification = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, qualificationId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                qualification = new QualificationData(
                    //ME FALTARIAN COSAS AQUI!
                    result.getString("qualificationId")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getQualification: " + sql + " Exception: " + e);
        }
        
        return qualification;
        
    }
    
    public static int updateQualification(Connection connection, QualificationData qualification){
        //ESCRIBIR SQL!!!!
        String sql="";
        System.out.println("updateQualification: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setString(1,qualification.qualificationId);
//            stmtUpdate.setInt(2,product.supplierId);
//            stmtUpdate.setFloat(3,product.unitPrice);
//            stmtUpdate.setString(4,product.productId); CAMBIAR ESTO!
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateQualification: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}
