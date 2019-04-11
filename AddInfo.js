
console.log("Entra 1");
function fillclientInfo() {
    var clientId = [];
    var companyName = [];
    for (i = 0; i <clientName.length; i++) {
        clientId.push(clientName[i].clientId);
        companyName.push(clientName[i].companyName);
    }
	console.log("Entra 1");
	var x = document.getElementById("companyName");
	//var option = document.createElement("option");
  
	for (i=0; i<clientName.length; i++) {
	var option = document.createElement("option");
      option.text = companyName[i];
      option.value= clientId[i];
      x.add(option);
	}
}

function clientSelected() {
    var cSelected = document.getElementById("companyName").value; 
    document.getElementById("selectedClient").value=cSelected;
}

function fillmanagerInfo() {
    var managerId = [];
    var manager = [];
    for (i = 0; i <managerName.length; i++) {
        managerId.push(managerName[i].managerId);
        manager.push(managerName[i].managerName);
    }

	var x = document.getElementById("manager");
	//var option = document.createElement("option");
  
	for (i=0; i<managerName.length; i++) {
	var option = document.createElement("option");
      option.text = manager[i];
      option.value= managerId[i];
      x.add(option);
	}
}

function mSelected() {
    var mSelected = document.getElementById("manager").value; 
    document.getElementById("selectedManager").value=mSelected;
}


function fillcountryInfo() {
    var country = [];
    for (i = 0; i <data.length; i++) {
        country.push(data[i].countryName);
    }

	var x = document.getElementById("countryName");
	//var option = document.createElement("option");
  
	for (i=0; i<data.length; i++) {
	var option = document.createElement("option");
      option.text = country[i];
      option.value= country[i];
      x.add(option);
	}
}

function cSelected() {
    var cSelected = document.getElementById("countryName").value; 
    document.getElementById("selectedCountry").value=cSelected;
}


fillclientInfo();
fillmanagerInfo();
fillcountryInfo();
