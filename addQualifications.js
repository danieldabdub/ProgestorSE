function addQualifications() {
	changeButton();
	var tableRef = document.getElementById("qualificationsTable").getElementsByTagName("tbody")[0];
	var row = tableRef.insertRow(-1);
	var qualification = row.insertCell(0);
	var date = row.insertCell(1);
	qualification.innerHTML="<select onchange='qualificationSelected()' id='selectQualification'> </select>";
	fillQualifInfo();
	date.innerHTML = "<input onchange='dateChosen()' type='date' id='qualifDate' name='qualificationDate'> </select>";
}

//var qualif = ["Computer Technician", "Electrical", "Mechanics"];
//var qualifId = ["CompTech","Elec","Mech"];

function fillQualifInfo() {
    var qualifId = [];
    var qualif = [];
    for (i = 0; i <data.length; i++) {
        qualifId.push(data[i].qualificationId);
        qualif.push(data[i].qualification);
    }

	var x = document.getElementById("selectQualification");
	var option = document.createElement("option");
  
	for (i=0; i<data.length; i++) {
	var option = document.createElement("option");
      option.text = qualif[i];
      option.value= qualifId[i];
      x.add(option);
	}
}

function qualificationSelected() {
    var qualifSelected = document.getElementById("selectQualification").value; 
    document.getElementById("selectedQualif").value=qualifSelected;
}

function dateChosen() {
	var qualifDate = document.getElementById("qualifDate").value;
	document.getElementById("qualificDate").value = qualifDate;
}

function changeButton() {
    document.getElementById("buttonQualif").innerHTML="<button class='button button1' type='submit'> Save changes </button></div>";
    document.getElementById("divAddQualif").innerHTML=" ";
}



