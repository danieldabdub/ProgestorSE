function viewQualifEmployees() {
	var qualif = document.getElementById("qualif").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button1").innerHTML == "View qualified employees") {
            document.getElementById("qualifiedEmployees").innerHTML = this.responseText;
                document.getElementById("button1").innerHTML = "Hide qualified employees";           
            } else {
            document.getElementById("qualfiedEmployees").innerHTML = " ";
            document.getElementById("button1").innerHTML = "View qualified employees";    
            }
        }
    };
    xhttp.open("GET", "QualifEmployees?id="+qualif+"", true);
    xhttp.send(); 
}

