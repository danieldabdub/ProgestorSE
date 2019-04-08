import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EmployeeData{
    
    
    String employeeId;
    String firstName;
    String lastName;
    Date hireDate;
    String hiredDate;
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
    
    EmployeeData (String employeeId, String firstName, String lastName, String hiredDate, String phone, String mail){
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hiredDate = hiredDate;
        this.phone = phone;
        this.mail = mail;
    }    
    
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
        //ESCRIBIR SQL!!!!
        String sql="UPDATE Employees";
        sql += "SET firstName=?, lastName=?, hireDate=?, phone=?, mail=?";
        sql += "WHERE employeeId=?";
        System.out.println("updateEmployee: " + sql);
        
        int n = 0;
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,employee.firstName);
            stmtUpdate.setString(2,employee.lastName);
            stmtUpdate.setDate(3,employee.hireDate);
            stmtUpdate.setString(4,employee.phone);
            stmtUpdate.setString(5,employee.mail);

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
        String sql="INSERT INTO EmployeeQualificatons (employeeId, qualificationId, qualificationDate)";
        sql += "VALUES (?,?,?)";
        System.out.println("insertEmployeeQualifications: " + sql);
        
        int n = 0; 
        
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,employeeId);
            stmtUpdate.setString(2,qualificationId);
            stmtUpdate.setString(3,qualificationDate); //Duda con date
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertEmployeeQualification: " + sql + " Exception: " + e);
        }
        return n;
        
    }
        
}

