function viewClientProjects() {
	var client_Id = document.getElementById("clId").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button1").innerHTML == "View client's projects") {
                document.getElementById("clientProjects").innerHTML = this.responseText;
                document.getElementById("button1").innerHTML = "Hide client's projects";           
            } else {
            document.getElementById("clientProjects").innerHTML = " ";
            document.getElementById("button1").innerHTML = "View client's projects";    
            }
        }
    };
    xhttp.open("GET", "ClientProjects?clientId="+client_Id+"", true);
    xhttp.send(); 
}
