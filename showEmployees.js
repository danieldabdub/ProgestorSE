function viewProjectEmployees() {
	var projectId = document.getElementById("projId").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button1").innerHTML == "View project's employees") {
                document.getElementById("divProjectEmployees").innerHTML = this.responseText;
                document.getElementById("button1").innerHTML = "Hide project's employees";           
            } else {
            document.getElementById("divProjectEmployees").innerHTML = " ";
            document.getElementById("button1").innerHTML = "View project's employees";    
            }
        }
    };
	//console.log("entra");
    xhttp.open("GET", "ProjectEmployees?projectId="+ projectId+"", true);
    xhttp.send();
	//console.log("entra 2");	
}

