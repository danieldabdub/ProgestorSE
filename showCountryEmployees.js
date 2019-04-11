function viewCountryEmployees() {
	var countryId = document.getElementById("countryId").value;
    var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (document.getElementById("button1").innerHTML == "View Employees Available for " + countryId) {
                document.getElementById("countryEmployees").innerHTML = this.responseText;
                document.getElementById("button1").innerHTML = "Hide Employees Available";           
            } else {
            document.getElementById("countryEmployees").innerHTML = " ";
            document.getElementById("button1").innerHTML = "View Employees Available for " + countryId;    
            }
        }
    };
    xhttp.open("GET", "CountryEmployees?id="+countryId+"", true);
    xhttp.send(); 
}

