function viewEmployeeProjects() {
	var employeeId = document.getElementById("empId").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button1").innerHTML == "View employee's projects") {
                document.getElementById("employeeProjects").innerHTML = this.responseText;
                document.getElementById("button1").innerHTML = "Hide employee's projects";           
            } else {
            document.getElementById("employeeProjects").innerHTML = " ";
            document.getElementById("button1").innerHTML = "View employee's projects";    
            }
        }
    };
    xhttp.open("GET", "EmployeeProjects?employeeId="+employeeId+"", true);
    xhttp.send(); 
}

