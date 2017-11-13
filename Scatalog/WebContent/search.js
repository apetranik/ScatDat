function initialize() {
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].onclick = function(){
        /* Toggle between adding and removing the "active" class,
        to highlight the button that controls the panel */
        this.classList.toggle("active");

        /* Toggle between hiding and showing the active panel */
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    }
}
}

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