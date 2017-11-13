

function srch() {
	var xhttp = new XMLHttpRequest(); 
		xhttp.open("GET", "homeSearch.jsp?searchText=" +  document.getElementById("searchText").value, true); 
		xhttp.send(); 
		xhttp.onreadystatechange = function updateResults() {
			if(this.readyState == 4 && this.status == 200) {
				document.getElementById("cards").innerHTML = xhttp.responseText; 
			}
		};
}