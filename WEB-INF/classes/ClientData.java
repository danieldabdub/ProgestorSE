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
    String countryHq;
    String contact;
    String phone;
    String mail;
        
    // Primer Constructor: -getClientList

    ClientData (String clientId, String companyName, String countryHq){
        this.clientId = clientId;
        this.companyName = companyName;
        this.countryHq = countryHq;
    }
    
    // Segundo Contructor: -getClient
    
    ClientData (String clientId, String companyName, String countryHq, String contact, String phone, String mail){
        this.clientId = clientId;
        this.companyName = companyName;
        this.countryHq = countryHq;
        this.contact = contact;
        this.phone = phone;
        this.mail = mail;
    }
    
     public static Vector<ClientData> getClientList(Connection connection){
        
        Vector<ClientData> vec = new Vector<ClientData>();
        
        //ESCRIBIR SQL
        
        String sql = "SELECT clientId, companyName, countryHq FROM Clients";
         
        System.out.println("getClientList: " + sql);
        
        try {
            
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                ClientData client = new ClientData(
                    result.getString("clientId"),
					result.getString("companyName"),
                    result.getString("countryHq")
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
        
        String sql = "SELECT clientId, companyName, countryHq, contact, phone, mail FROM Clients WHERE clientId=?";
        
        System.out.println("getClient: " + sql);
        
        ClientData client = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, clientId);
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                client = new ClientData(
                    result.getString("clientId"),
                    result.getString("companyName"),
                    result.getString("countryHq"),
                    result.getString("contact"),
                    result.getString("phone"),
                    result.getString("mail")
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
        
        String sql="UPDATE Clients ";
        sql += "SET companyName=?, countryHq=?, contact=?, phone=?, mail=? ";
        sql += "WHERE clientId=?";
        System.out.println("updateClient: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setString(1,client.companyName);
            stmtUpdate.setString(2,client.countryHq);
            stmtUpdate.setString(3,client.contact);
            stmtUpdate.setString(4,client.phone);
            stmtUpdate.setString(5,client.mail);
            stmtUpdate.setString(6,client.clientId);
            
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateClient: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}
