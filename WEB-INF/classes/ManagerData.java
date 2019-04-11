import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ManagerData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String managerId;
    String last;
    String first;
    
    
    // Primer Constructor: - getProjectList
    
    ProjectData (String managerId, String first, String last){
        
        this.managerId = managerId;
        this.last = last;
		this.first = first;
        
    }
   
    
    
    public static Vector<ManagerData> getManagerList(Connection connection){
        
        Vector<ManagerData> vec = new Vector<ManagerData>();
        
        String sql = "SELECT managerId, first, last FROM ProjectManagers ";

        System.out.println("getManagerList: "+ sql);
       
        try {
            
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
               
                ManagerData manager = new ManagerData(
                    result.getString("managerId"),
					result.getString("first"),
					result.getString("last")
                );
                
                vec.addElement(manager);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getManagerList: " + sql + " Exception: " + e);
        }
        return vec;
    }
  }