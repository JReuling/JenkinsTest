//Assume all buttons have been named according to their relative pits.

document.getElementById(\"pit1\").addEventListener(\"click\",function(){buttonClick(1)});
document.getElementById(\"pit2\").addEventListener(\"click\",function(){buttonClick(2)});
document.getElementById(\"pit3\").addEventListener(\"click\",function(){buttonClick(3)});
document.getElementById(\"pit4\").addEventListener(\"click\",function(){buttonClick(4)});
document.getElementById(\"pit5\").addEventListener(\"click\",function(){buttonClick(5)});
document.getElementById(\"pit6\").addEventListener(\"click\",function(){buttonClick(6)});

document.getElementById(\"pit8\").addEventListener(\"click\",function(){buttonClick(8)});
document.getElementById(\"pit9\").addEventListener(\"click\",function(){buttonClick(9)});
document.getElementById(\"pit10\").addEventListener(\"click\",function(){buttonClick(10)});
document.getElementById(\"pit11\").addEventListener(\"click\",function(){buttonClick(11)});
document.getElementById(\"pit12\").addEventListener(\"click\",function(){buttonClick(12)});
document.getElementById(\"pit13\").addEventListener(\"click\",function(){buttonClick(13)});

function buttonClick(int pit)
{
	//Make Call to corresponding pit
}

                function loadDoc() {
                var xhttp = new XMLHttpRequest(); 
                xhttp.onreadystatechange = function() {
                if (xhttp.readyState == 4 && xhttp.status == 200) {" +
                    document.getElementById(\demo\).innerHTML = xhttp.responseText;
                }}
                xhttp.open(\GET\, \\, false);
                "xhttp.send();" +
                "}" +