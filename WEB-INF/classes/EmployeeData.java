import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeData{
    
    
    String employeeId;
    String firstName;
    String lastName;
    Date hireDate;
    String strHireDate;
    String phone;
    String mail;
    
    
    EmployeeData (String employeeId, String firstName, String lastName, Date hireDate, String phone, String mail){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phone = phone;
        this.mail = mail;
    }
    
    EmployeeData (String employeeId, String firstName, String lastName, String strHireDate, String phone, String mail){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.strHireDate = strHireDate;
        this.phone = phone;
        this.mail = mail;
    }    
    
	//Constructo para getProjectEmployeeList, getQualifiedEmployes
    EmployeeData(String employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
    
    public static Vector<EmployeeData> getEmployeeList(Connection connection){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        
        String sql = "SELECT employeeId, firstName, lastName, hireDate, phone, mail FROM Employees";
        System.out.println("getEmployeeList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {

                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId"),
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getDate("hireDate"),
                    result.getString("phone"),
                    result.getString("mail")
                );
                
                vec.addElement(employee);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getEmployeeList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static Vector<EmployeeData> getProjectEmployeeList(Connection connection, String projectId){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        
        String sql = "SELECT ProjectEmployee.employeeId as employeeId, firstName, lastName FROM ProjectEmployee, Employees";
        sql += "WHERE ProjectEmployee.employeeId=Employees.employeeId AND projectId=?";
        System.out.println("getProjectEmployeeList: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, projectId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId"),
                    result.getString("firstName"),
                    result.getString("lastName")
                );
                vec.addElement(employee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProjectEmployeeList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static EmployeeData getEmployee(Connection connection, String employeeId){
        
        String sql = "SELECT firstName, lastName, hireDate, phone, mail FROM Employees WHERE employeeId=?";
        System.out.println("getEmployee: " + sql);
        
        EmployeeData employee = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                employee = new EmployeeData(
                    employeeId,
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getDate("hireDate"),
                    result.getString("phone"),
                    result.getString("mail")
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
       
        String sql="UPDATE Employees";
        sql += "SET firstName=?, lastName=?, hireDate=?, phone=?, mail=?";
        sql += "WHERE employeeId=?";
        System.out.println("updateEmployee: " + sql);
        
		System.out.println(employee.strHireDate);
		
        int n = 0;
        
        try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilHireDate = formatter.parse(employee.strHireDate);
			java.sql.Date sqlHireDate = new java.sql.Date(utilHireDate.getTime());
			
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,employee.firstName);
            stmtUpdate.setString(2,employee.lastName);
            stmtUpdate.setDate(3,sqlHireDate);
            stmtUpdate.setString(4,employee.phone);
            stmtUpdate.setString(5,employee.mail);

            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in updateEmployee: " + sql + " Exception: " + e);   
        }  catch(ParseException p) {
            p.printStackTrace();
            System.out.println("Error in updateEmployee: " + sql + " Exception: " + p);
        }
        return n;
    }
    
    public static Vector<EmployeeData> getQualifiedEmployee(Connection connection, String qualificationId){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        
        String sql = "SELECT EmployeeQualifications.employeeId as employeeId, firstName, lastName FROM EmployeeQualifications, Employees";
        sql += "WHERE Employees.employeeId=EmployeeQualifications.employeeId AND qualificationId=?";
        System.out.println("getQualifiedEmployee: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, qualificationId);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId"),
                    result.getString("firstName"),
                    result.getString("lastName")
                );
                vec.addElement(employee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getQualifiedEmployee: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static int insertEmployeeQualification(Connection connection, String employeeId, String qualificationId, String qualificationDate) {
		
		String sql="INSERT INTO EmployeeQualifications (employeeId, qualificationId, qualificationDate)";
        sql += "VALUES (?,?,?)";
        System.out.println("insertEmployeeQualifications: " + sql);
        
        int n = 0; 
        
        try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		
			java.util.Date utilQualificationDate = formatter.parse(qualificationDate);
			java.sql.Date sqlQualificationDate = new java.sql.Date(utilQualificationDate.getTime());
		
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,employeeId);
            stmtUpdate.setString(2,qualificationId);
            stmtUpdate.setDate(3,sqlQualificationDate); //Date
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertEmployeeQualification: " + sql + " Exception: " + e);
        } catch(ParseException p) {
            p.printStackTrace();
            System.out.println("Error in insertEmployeeQualification: " + sql + " Exception: " + p);
        }
        return n;
        
    }
	
    public static Vector<EmployeeData> getCountryEmployeeList(Connection connection, String countryName){
        
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        
        
        String sql = "SELECT EmployeeCountry.employeeId as employeeId, firstName, lastName FROM EmployeeCountry, Employees ";
        sql += "WHERE EmployeeCountry.employeeId=Employees.employeeId AND countryName=?";
        System.out.println("getProjectEmployeeList: " + sql);
        
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1, countryName);
            
            ResultSet result = pstmt.executeQuery();
            
            while(result.next()) {
                
                EmployeeData employee = new EmployeeData(
                    result.getString("employeeId"),
                    result.getString("firstName"),
                    result.getString("lastName")
                );
                vec.addElement(employee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProjectEmployeeList: " + sql + " Exception: " + e);
        }
        return vec;
    }	
	
	public static int insertEmployee(Connection connection, EmployeeData employee){
        String sql="INSERT INTO Employees VALUES (?, ?, ?, ?, ?, ?, ? )";
        System.out.println("insertEmployee: " + sql);
        
        int n = 0;
		

		
        try {
			
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date utilhireDate = formatter.parse(employee.strHireDate);
		java.sql.Date sqlhireDate = new java.sql.Date(utilhireDate.getTime());
	
	
            PreparedStatement stmtInsert= connection.prepareStatement(sql);
            
			stmtInsert.setString(1,employee.employeeId);
			stmtInsert.setString(2,employee.firstName);
			stmtInsert.setString(3,employee.lastName);
			stmtInsert.setDate(5, sqlhireDate);
			stmtInsert.setString(6,employee.phone);
			stmtInsert.setString(7,employee.mail);
			
				
            n = stmtInsert.executeUpdate();
            stmtInsert.close();
			
			
        } catch(SQLException e){
            e.printStackTrace();
			
            System.out.println("Error in insertEmployee: " + sql + " Exception: " + e);
        } catch(ParseException pe){
            pe.printStackTrace();
            System.out.println("Error in insertEmployee: " + sql + " Exception: " + pe);   			
        }
        return n;
		

    }
        
}

