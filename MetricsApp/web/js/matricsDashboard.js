var md = {
	test:"test",
	process:function(){
		//validate
		if(document.getElementById("title").value==""){
			alert("Please enter Title.");
			return false;
		}
		if(document.getElementById("wrNum").value==""){
			alert("Please enter WR#.");
			return false;
		}
		if(document.getElementById("filter").value==""){
			alert("Please enter Filter.");
			return false;
		}
		document.getElementById("responseTxt").innerHTML = "";
		// Create a variable to refer to our request object:
	    var xmlhttp;
	     
	    if (window.XMLHttpRequest){
	        // code for IE7+, Firefox, Chrome, Opera, Safari
	        xmlhttp=new XMLHttpRequest();
	    }else{
	        // code for IE
	        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    
	    xmlhttp.onreadystatechange = function(){
	        // This code will be executed each time the readyState changes
	        if(xmlhttp.readyState == 4){
	            //alert("11xmlhttp.responseText="+xmlhttp.responseText);
	        	var returnObj = JSON.parse(xmlhttp.responseText);
	        	if(returnObj.lineCount!=undefined)
	        		document.getElementById("responseTxt").innerHTML = "Count:"+returnObj.lineCount;
	        	else if(returnObj.gridDate!=undefined)
	        		document.getElementById("responseTxt").innerHTML = "Count:"+returnObj.gridDate;
	        	returnObj = null;
	        }
	    }
	    var param ="title="+document.getElementById("title").value;
	    param =param + "&wrNum="+document.getElementById("wrNum").value
	    param =param + "&fileName="+document.getElementById("fileName").value;
	    param =param + "&filter="+document.getElementById("filter").value;
	    param =param + "&date="+document.getElementById("date").value;
	    xmlhttp.open("GET","/firatApp-0.1-dev/MatricsDashboardAction.do?"+param,true);
	    xmlhttp.send();

	    
	    

	}
};