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