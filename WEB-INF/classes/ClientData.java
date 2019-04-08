import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ClientData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String clientId;
	String companyName;
    
    
    // Primer Constructor
    //PONER TODOS LOS QUE FALTAN ABAJO
    ClientData (String clientId){
        this.clientId = clientId;
    }
	
	    ClientData (String clientId, String CompanyName){
        this.clientId = clientId;
		this.companyName = companyName;
    }
    
     public static Vector<ClientData> getClientList(Connection connection){
        
        Vector<ClientData> vec = new Vector<ClientData>();
        
        //ESCRIBIR SQL
        
        String sql = "";
        System.out.println("getClientList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                ClientData client = new ClientData(
                    result.getString("clientId"),
					result.getString("companyName")
                );
                
                vec.addElement(client);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getClientList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static ClientData getClient(Connection connection, String clientId){
        
        
        //ESCRIBIR SQL!
        String sql = "";
        System.out.println("getClient: " + sql);
        
        ClientData client = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, clientId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                client = new ClientData(
                    //ME FALTARIAN COSAS AQUI!
                    result.getString("clientId")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getClient: " + sql + " Exception: " + e);
        }
        
        return client;
        
    }
    
    public static int updateClient(Connection connection, ClientData client){
        //ESCRIBIR SQL!!!!
        String sql="";
        System.out.println("updateClient: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setString(1,client.clientId);
//            stmtUpdate.setInt(2,product.supplierId);
//            stmtUpdate.setFloat(3,product.unitPrice);
//            stmtUpdate.setString(4,product.productId); CAMBIAR ESTO!
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateClient: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}
