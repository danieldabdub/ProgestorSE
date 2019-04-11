//package dom.mykong.date;
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


public class ProjectData{
    
    // Poner aqui los nombres que finalmente se usen.
    
    String projectId;
    String companyName;
    String last;
    String first;
    String status;
    Date startDate;
	Date dueDate;
	String startDateStr;
	String dueDateStr;
    String countryName;
    int startMonth;
    int dueMonth;
	String clientId;
	String managerId;
    
    
    // Primer Constructor: - getProjectList
    
    ProjectData (String projectId, String companyName, String first, String last, String status, Date dueDate){
        
        this.projectId = projectId;
		this.companyName = companyName;
        
        // PM Complete Name
        
        this.last = last;
		this.first = first;
		
        
		this.status = status;
        this.dueDate = dueDate;
        
    }
    
    // Segundo Constructor: - getActiveProjectList
    
    ProjectData (String projectId, String companyName, int startMonth, int dueMonth){
        
        this.projectId = projectId;
		this.companyName = companyName;
        this.startMonth = startMonth;
        this.dueMonth = dueMonth;
    
    }
	
	//Tercer Constructor: - ProjectUpdate/ProjectEdit
    
	    ProjectData (String projectId, String companyName,String first, String last, Date startDate, String status, Date dueDate){
        
        this.projectId = projectId;
		this.companyName = companyName;
		this.first=first;
		this.last=last;
        this.startDate = startDate;
		this.status=status;
        this.dueDate = dueDate;
    
    }
	  ProjectData (String projectId, String companyName,String first, String last, Date startDate, String status, Date dueDate, String countryName){
        
        this.projectId = projectId;
		this.companyName = companyName;
		this.first=first;
		this.last=last;
        this.startDate = startDate;
		this.status=status;
        this.dueDate = dueDate;
		this.countryName=countryName;
    
    }
	//para imprimir fechas-strings
	 ProjectData (String projectId, String companyName,String first, String last, String startDateStr, String status, String dueDateStr, String countryName){
        
        this.projectId = projectId;
		this.companyName = companyName;
		this.first=first;
		this.last=last;
        this.startDateStr = startDateStr;
		this.status=status;
        this.dueDateStr = dueDateStr;
		this.countryName=countryName;
		}
		
	ProjectData (String projectId, String clientId, String managerId, String startDateStr, String status, String dueDateStr, String countryName){
        
        this.projectId = projectId;
		this.clientId = clientId;
		this.managerId=managerId;
        this.startDateStr = startDateStr;
		this.status=status;
        this.dueDateStr = dueDateStr;
		this.countryName=countryName;
		}
     
    
    
    
    public static Vector<ProjectData> getProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        String sql = "SELECT projectId, companyName, first, last, status, dueDate FROM Projects, Clients, ProjectManagers WHERE Clients.clientId=Projects.clientId AND ProjectManagers.managerId=Projects.managerId;";

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
					result.getString("status"),
					result.getDate("dueDate")
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
   
     public static Vector<ProjectData> getActiveProjectList(Connection connection){
        
        Vector<ProjectData> vec = new Vector<ProjectData>();
        
        String sql = "SELECT projectId, companyName, MONTH(startDate) AS startMonth, MONTH(dueDate) AS dueMonth FROM Projects, Clients WHERE Clients.clientId=Projects.clientId AND status='In progress'";
        
        System.out.println("getActiveProjectList: " + sql);
        
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()) {
                
                // CAMBIAR! SEGUN LAS BD
                
                ProjectData project = new ProjectData(
                    result.getString("projectId"),
                    result.getString("companyName"),
                    Integer.parseInt(result.getString("startMonth")),
                    Integer.parseInt(result.getString("dueMonth"))
                );
                
                vec.addElement(project);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getActiveProjectList: " + sql + " Exception: " + e);
        }
        return vec;
    }
     
    // Ojo: cuando este listo Clients hacerlo.
    
//    public static Vector<ProjectData> getClientProjectList(Connection connection, String clientId){
//        
//        Vector<ProjectData> vec = new Vector<ProjectData>();
//        
//        //ESCRIBIR SQL
//        
//        String sql = "SELECT ";
//        System.out.println("getClientProjectList: " + sql);
//        
//        try {
//            PreparedStatement pstmt=connection.prepareStatement(sql);
//            pstmt.setString(1, clientId);
//            
//            ResultSet result = pstmt.executeQuery();
//            
//            while(result.next()) {
//                
//                ProjectData project = new ProjectData(
//                    result.getString("projectId")
//                );
//                vec.addElement(project);
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error in getClientProjectList: " + sql + " Exception: " + e);
//        }
//        return vec;
//    }
    
    // Ojo: terminar
    
    public static ProjectData getProject(Connection connection, String projectId){
        String sql = "SELECT projectId, companyName, first, last, startDate, status, dueDate, countryName FROM Projects, Clients, ProjectManagers WHERE Clients.clientId=Projects.clientId AND ProjectManagers.managerId=Projects.managerId";
        sql += " AND projectId=?";

        System.out.println("getProject: " + sql);
        
        ProjectData project = null;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, projectId);
            
            ResultSet result = pstmt.executeQuery();
            
            if(result.next()){
                project = new ProjectData(
                    result.getString("projectId"),
					result.getString("companyName"),
					result.getString("first"),
					result.getString("last"),
					result.getDate("startDate"),
					result.getString("status"),
					result.getDate("dueDate"),
					result.getString("countryName")
                );  
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProject: " + sql + " Exception: " + e);
        }
        
        return project;
        
    }
    
	//SQL Editado : first = ?, last = ?, startDate=?, status = ?, dueDate = ?, countryName = ? 
	
    public static int updateProject(Connection connection, ProjectData project){
        String sql="UPDATE Projects SET status = ?, startDate=?, dueDate = ? WHERE projectId = ?";
        System.out.println("updateProject: " + sql);
        
        int n = 0;
		

		
        try {
			
		//SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date utilStartDate = formatter.parse(project.startDateStr);
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

		java.util.Date utilDueDate = formatter.parse(project.dueDateStr);
		java.sql.Date sqlDueDate = new java.sql.Date(utilDueDate.getTime());		
	
	
		
		//java.sql.Date sDate = DatesConversion.convertUtilToSql(startDateUp);
		//java.sql.Date dDate = DatesConversion.convertUtilToSql(dueDateUp);
		
		//java.sql.Date	sqlstarDate = new java.sql.Date(startDateUp);	
		
		
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
           stmtUpdate.setString(1,project.status);	
		   stmtUpdate.setDate(2, sqlStartDate);
		   stmtUpdate.setDate(3,sqlDueDate);
			stmtUpdate.setString(4,project.projectId);
			
			
          /*  stmtUpdate.setString(2,project.first);
           stmtUpdate.setString(3,project.last);
		   stmtUpdate.setDate(4, sqlStartDate);
           stmtUpdate.setString(5,project.status);
		   stmtUpdate.setDate(6,sqlDueDate);
		   stmtUpdate.setString(7,project.countryName);
		   stmtUpdate.setString(8,project.projectId); */
				
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
			
			
        } catch(SQLException e){
            e.printStackTrace();
			
            System.out.println("Error in updateProject: " + sql + " Exception: " + e);
        } catch(ParseException pe){
            pe.printStackTrace();
            System.out.println("Error in updateProject: " + sql + " Exception: " + pe);   			
        }
        return n;
		

    }
	
	public static int insertProject(Connection connection, ProjectData project){
        String sql="INSERT INTO Projects VALUES (?, ?, ?, ?, ?, ?, ? )";
        System.out.println("insertProject: " + sql);
        
        int n = 0;
		

		
        try {
			
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date utilStartDate = formatter.parse(project.startDateStr);
		java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

		java.util.Date utilDueDate = formatter.parse(project.dueDateStr);
		java.sql.Date sqlDueDate = new java.sql.Date(utilDueDate.getTime());		
	
            PreparedStatement stmtInsert= connection.prepareStatement(sql);
            
			stmtInsert.setString(1,project.projectId);
			stmtInsert.setString(2,project.clientId);
			stmtInsert.setString(3,project.managerId);
			stmtInsert.setString(4,project.status);
			stmtInsert.setDate(5, sqlStartDate);
			stmtInsert.setDate(6,sqlDueDate);
			stmtInsert.setString(7,project.countryName);
			
				
            n = stmtInsert.executeUpdate();
            stmtInsert.close();
			
			
        } catch(SQLException e){
            e.printStackTrace();
			
            System.out.println("Error in insertProject: " + sql + " Exception: " + e);
        } catch(ParseException pe){
            pe.printStackTrace();
            System.out.println("Error in insertProject: " + sql + " Exception: " + pe);   			
        }
        return n;
		

    }
        
}

class EmployeeProjectData {
    String employeeId;
    String    projectId;
    String    countryName;
    String status;

    
    EmployeeProjectData (String employeeId, String projectId, String countryName, String status) {
        this.employeeId    = employeeId;
        this.projectId   = projectId;
        this.countryName = countryName;
        this.status = status;
    }
        
    public static Vector<EmployeeProjectData> getEmployeeProjects(Connection connection, String employeeId) {
        Vector<EmployeeProjectData> vec = new Vector<EmployeeProjectData>();
        String sql = "SELECT ProjectEmployee.projectId as projectId, ProjectEmployee.countryName as countryName, status FROM ProjectEmployee, Projects";
        sql += " WHERE ProjectEmployee.projectId = Projects.projectId AND employeeId = ?";
        System.out.println("getEmployeeProjects: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, employeeId);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                EmployeeProjectData employeeProjects = new EmployeeProjectData(
                    employeeId,
                    result.getString("projectId"),
                    result.getString("countryName"),
                    result.getString("status")
                );
                vec.addElement(employeeProjects);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getEmployeeProjects: " + sql + " Exception: " + e);
        }
        return vec;
    }    
}

class ClientProjectData {
    String clientId;
    String projectId;
    String countryName;
    String status;
    
        ClientProjectData (String clientiD, String projectId, String countryName, String status) {
        this.clientId    = clientId;
        this.projectId   = projectId;
        this.countryName = countryName;
        this.status = status;
    }
        
    public static Vector<ClientProjectData> getClientProjects(Connection connection, String clientId) {
        Vector<ClientProjectData> vec = new Vector<ClientProjectData>();
        String sql = "SELECT projectId, countryName, status FROM Projects";
        sql += " WHERE clientId = ?";
        System.out.println("getClientProjects: " + sql);
        try {
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1, clientId);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                ClientProjectData clientProjects = new ClientProjectData(
                    clientId,
                    result.getString("projectId"),
                    result.getString("countryName"),
                    result.getString("status")
                );
                vec.addElement(clientProjects);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getClientProjects: " + sql + " Exception: " + e);
        }
        return vec;
    }   
    

}