import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class CountryData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String countryName;
	int processingTime;
	int validityTime;
    
    // Primer Constructor: - getQualificationList
    //                     - getQualification
    
    CountryData (String countryName, int processingTime, int validityTime){
        this.countryName = countryName;
		this.processingTime = processingTime;
		this.validityTime = validityTime;
    }
    
    CountryData (String countryName){
        this.countryName = countryName;
    }
    
    public static Vector<CountryData> getCountryList(Connection connection){
        
        Vector<CountryData> vec = new Vector<CountryData>();
        
        String sql = "SELECT countryName, processingTime, validityTime FROM Countries";
        System.out.println("getCountryList: " + sql);
        
        try {
            
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {

                
                CountryData country = new CountryData(
                    result.getString("countryName"), Integer.parseInt(result.getString("processingTime")),
                    Integer.parseInt(result.getString("validityTime"))
                );
                
                vec.addElement(country);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCountryList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    
    public static Vector<CountryData> getEmployeeCountryList(Connection connection, String employeeId){
        Vector<CountryData> vec = new Vector<CountryData>();
        
        String sql = "SELECT EmployeeCountry.countryName as country FROM EmployeeCountry, Countries";
        sql += "WHERE EmployeeCountry.countryName=Countries.countryName AND employeeId=?";
        
        System.out.println("getCountryEmployeeList: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                CountryData country = new CountryData(
                    result.getString("countryName")
                );
                vec.addElement(country);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCountryEmployeeList: " + sql + " Exception: " + e);
        }
        return vec;
        
    }
    
    public static CountryData getCountry(Connection connection, String countryName){
        
        String sql = "SELECT countryName, processingTime, validityTime FROM Countries WHERE countryName=?";
        
        System.out.println("getCountry: " + sql);
        
        CountryData country = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, countryName);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                country = new CountryData(
                    result.getString("countryName"), Integer.parseInt(result.getString("processingTime")),
                    Integer.parseInt(result.getString("validityTime"))
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCountry: " + sql + " Exception: " + e);
        }
        
        return country;
        
    }
    
    public static int updateCountry(Connection connection, CountryData country){

        String sql="UPDATE Countries";
        sql += "SET processingTime=?, validityTime=?";
        sql += "WHERE countryName=?";
        
        System.out.println("updateCountry: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
            stmtUpdate.setInt(1,country.processingTime);
            stmtUpdate.setInt(2,country.validityTime);
            stmtUpdate.setString(2,country.countryName);
            
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
            
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateCountry: " + sql + " Exception: " + e);   
        }
        return n;
    }
        
}
