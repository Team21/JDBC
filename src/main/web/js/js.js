function clearPlaceHolder(x) {
	x.placeholder = "";
};

function writePlaceHolderNumber(x) {
	if(x.placeholder == "")
		x.placeholder = "Введите номер";
}

function writePlaceHolderName(x) {
	if(x.placeholder == "")
		x.placeholder = "Введите имя";
}

function checkNumber() { 
   var n = parseInt(document.getElementById("number").value); 
   if (isNaN(n)) 
	   alert('Error! Please write number!'); 
}

function pseudoDelete(x) {
	var isDelete = confirm("Are you sure?");
	if(isDelete == true)

		document.getElementById(x).style.display='none';
}


function openEdit() {
	var name = encodeURIComponent(document.getElementById("name").value);
	window.location.href = "save.html?" + name;
}


function remR(){
var allRows = document.getElementById('tab').getElementsByTagName('tr');
var root = allRows[0].parentNode;
var allInp = root.getElementsByTagName('input');
var isDelete = confirm("Are you sure?");
	if(isDelete == true)
		for(var i=allInp.length-1;i>=0;i--){
			if((allInp[i].getAttribute('type')=='checkbox')&&(allInp[i].checked)){
				root.removeChild(allInp[i].parentNode.parentNode)
	}
}
}
