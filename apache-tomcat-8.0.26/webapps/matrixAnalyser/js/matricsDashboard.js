var md = {
	test:"test",
	loadOnReady:function(){
		var d1 = [[1262304000000, 6], [1264982400000, 3057], [1267401600000, 20434], [1270080000000, 31982], [1272672000000, 26602], [1275350400000, 27826], [1277942400000, 24302], [1280620800000, 24237], [1283299200000, 21004], [1285891200000, 12144], [1288569600000, 10577], [1291161600000, 10295]];
	    var d2 = [[1262304000000, 5], [1264982400000, 200], [1267401600000, 1605], [1270080000000, 6129], [1272672000000, 11643], [1275350400000, 19055], [1277942400000, 30062], [1280620800000, 39197], [1283299200000, 37000], [1285891200000, 27000], [1288569600000, 21000], [1291161600000, 17000]];
	 
	    var data1 = [
	        { label: "Label 1", data: d1, points: { fillColor: "#4572A7", size: 5 }, color: '#4572A7' },
	        { label: "Label 2", data: d2, points: { fillColor: "#89A54E", size: 5 }, color: '#89A54E' }
	    ];
	 
	    $.plot($("#placeholder"), data1, {
	        xaxis: {
	            min: (new Date(2009, 12, 1)).getTime(),
	            max: (new Date(2010, 11, 1)).getTime(),
	            mode: "time",
	            tickSize: [1, "month"],
	            monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
	            tickLength: 0,
	            axisLabel: 'Date',
	            axisLabelUseCanvas: true,
	            axisLabelFontSizePixels: 12,
	            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
	            axisLabelPadding: 5
	        },
	        yaxis: {
	            axisLabel: 'Count',
	            axisLabelUseCanvas: true,
	            axisLabelFontSizePixels: 12,
	            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
	            axisLabelPadding: 5
	        },
	        series: {
	            lines: {
	                show: true, fill: true
	            },
	            points: {
	                show: false
	            },
	        },
	        grid: {
	            borderWidth: 1
	        },
	        legend: {
	            labelBoxBorderColor: "none",
	            position: "right"
	        }
	    });
	},
	jqOptions:{
        datatype: "local",
        colNames: ["TITLE","WR_NUM","FILTER","DATE","LOG","COUNT"],
        colModel: [
            {name: "TITLE",width:150,sortable:false},
            {name: "WR_NUM", width:90,sortable:false},
            {name: "FILTER",width:150,sortable:false},
            {name: "DATE",width:90,sortable:false},
            {name: "FILENAME",width:120,sortable:false},
            {name: "COUNT",width:60,sortable:false}
        ],
        pager: false,
        rowNum: 200,
        loadonce:true,
        viewrecords: true,
        altRows:true,
        altClass:'altRowClass',
        editurl:'clientArray',
        caption: "",
        height: 300,
        onSelectRow: function (rowid) {},
        loadComplete:function(){}
    },
    loadFlexBoxes:function(){
    	var xmlhttp;
	    if (window.XMLHttpRequest){
	        xmlhttp=new XMLHttpRequest();
	    }else{
	        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    
	    xmlhttp.onreadystatechange = function(){
	        if(xmlhttp.readyState == 4){
	        	var returnObj = JSON.parse(xmlhttp.responseText);
	        	if(returnObj.logTypes!=undefined){
	        		$('#logType').flexbox(returnObj.logTypes,{
	        			width:200,
	        			paging:false,
	        			maxVisibleRows:15,
	        			onSelect:function(){}
	        		});
	        	}
	        	if(returnObj.wrNames!=undefined){
	        		$('#wrName').flexbox(returnObj.wrNames,{
	        			width:200,
	        			paging:false,
	        			maxVisibleRows:15,
	        			onSelect:function(){}
	        		});
	        	}
	        	returnObj = null;
	        }
	    }
	    xmlhttp.open("GET",contextPath+"/matrixAnalyser/MatricsDashboardAction.do?action=loadFlexBox",true);
	    xmlhttp.send();


    },
	process:function(){
		//validate
		if(document.getElementById("title").value==""){
			alert("Please enter Title.");
			return false;
		}
		if($("input[name=wrName]").val()==""){
			alert("Please enter WR#.");
			return false;
		}
		var selectedFilters = "";
		$("#selectedFilter option").each(function(i){
			if(selectedFilters=="")
				selectedFilters = $(this).val();
			else
				selectedFilters = selectedFilters + "|"+ $(this).val();
	    });
		if(selectedFilters==""){
		//	alert("Please enter Filter."); 
		//	return false;
		}
		//document.getElementById("responseTxt").innerHTML = "";
		$('#matricsGrid').html('');
		jQuery('#matricsGrid').clearGridData(true);
		$.jgrid.gridUnload('#matricsGrid');
		//document.getElementById("matricsGridDiv").style.display='none';
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
	            //alert("response="+xmlhttp.responseText);
	        	var returnObj = JSON.parse(xmlhttp.responseText);
	        	if(returnObj.lineCount!=undefined){
	        		//document.getElementById("responseTxt").innerHTML = "Count:"+returnObj.lineCount;
	        		var selectedFilters = "";
	        		$("#selectedFilter option").each(function(i){
	        			if(selectedFilters=="")
	        				selectedFilters = $(this).val();
	        			else
	        				selectedFilters = selectedFilters + "|"+ $(this).val();
	        	    });
	        		var data1 = [ { "TITLE" : document.getElementById("title").value , "WR_NUM" : $("input[name=wrName]").val() , "FILENAME" : $("input[name=logType]").val() , "FILTER" : selectedFilters , "DATE" : document.getElementById("date").value , "FILENAME" : $("input[name=logType]").val() , "COUNT" : returnObj.lineCount}];
	        		
	        		//jQuery('#matricsGrid').jqGrid('GridUnload');
	        		//document.getElementById("matricsGridDiv").style.display='block';
	        		$('#matricsGrid').html('');
	        		jQuery('#matricsGrid').clearGridData(true);
	        		//jQuery('#matricsGrid').jqGrid('GridUnload');
	        		$.jgrid.gridUnload('#matricsGrid'); 
	        		var myGrid = $("#matricsGrid");
	        		var alGrid=myGrid.jqGrid($.extend(md.jqOptions,{data:data1}));
	        		
	        	}else if(returnObj.gridData!=undefined){
	        		$('#matricsGrid').html('');
	        		jQuery('#matricsGrid').clearGridData(true);
	        		$.jgrid.gridUnload('#matricsGrid'); 
	        		var myGrid = $("#matricsGrid");
	        		var alGrid=myGrid.jqGrid($.extend(md.jqOptions,{data:JSON.parse(returnObj.gridData)}));
	        		  
	        		//draw graph
	        		//md.loadOnReady();
	        		var keyWords = JSON.parse(returnObj.keyWords);
	        		var dates1 = JSON.parse(returnObj.dates);
	        		var data2 =[];
	        		var data = eval(returnObj.datas);
	        		var color=["#4572A7","#89A54E","#19A54E","#29A54E","#39A54E","#29F54E","#29C84A","#D9C54A"];
	        		for(var i =0;i<keyWords.length;i++){
	        			var indx = i%9;
	        			var ithData ={ label: keyWords[i], data: data[i], points: { fillColor: color[indx], size: 5 }, color: color[indx] };
	        			data2.push();
	        		}
	        		var datesArr =[];
	        		for(var i =0;i<dates1.length;i++){
	        			datesArr.push(dates1[i]);
	        		}
	        		
	        		var d1 = [[1262304000000, 6], [1264982400000, 30], [1267401600000, 20], [1270080000000, 31], [1272672000000, 26], [1275350400000, 27], [1277942400000, 24], [1280620800000, 29], [1283299200000, 21], [1285891200000, 13], [1288569600000, 10], [1291161600000, 10]];
	        	    var d2 = [[1262304000000, 5], [1267401600000, 16], [1270080000000, 61], [1272672000000, 11], [1275350400000, 19], [1277942400000, 30], [1280620800000, 39], [1283299200000, 37], [1285891200000, 27], [1288569600000, 21], [1291161600000, 17]];
	        	    var d3 = [[1262304000000, 15], [1264982400000, 10], [1267401600000, 10], [1270080000000, 40], [1272672000000, 31], [1275350400000, 21], [1277942400000, 13], [1280620800000, 59], [1283299200000, 21], [1285891200000, 27], [1288569600000, 31], [1291161600000, 21]];
	        	 
	        	    var data1 = [
	        	        { label: "keyword 1", data: d1, points: { fillColor: "#4572A7", size: 5 }, color: '#4572A7' },
	        	        { label: "keyword 2", data: d2, points: { fillColor: "#89A54E", size: 5 }, color: '#89A54E' },
	        	        { label: "keyword 3", data: d3, points: { fillColor: "#89D54E", size: 5 }, color: '#89D54E' }
	        	    ];
	        	 
	        	    $.plot($("#placeholder"), data2, {
	        	        xaxis: {
	        	            mode: "time",
	        	            tickSize: [1, "date"],
	        	            tickLength: 0,
	        	            axisLabel: 'Date',
	        	            axisLabelUseCanvas: true,
	        	            axisLabelFontSizePixels: 12,
	        	            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
	        	            axisLabelPadding: 5
	        	        },
	        	        yaxis: {
	        	            axisLabel: 'Count',
	        	            axisLabelUseCanvas: true,
	        	            axisLabelFontSizePixels: 12,
	        	            axisLabelFontFamily: 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
	        	            axisLabelPadding: 5
	        	        },
	        	        series: {
	        	            lines: {
	        	                show: true, fill: true
	        	            },
	        	            points: {
	        	                show: false
	        	            },
	        	        },
	        	        grid: {
	        	            borderWidth: 1
	        	        },
	        	        legend: {
	        	            labelBoxBorderColor: "none",
	        	            position: "right"
	        	        }
	        	    });
	        	    
	        	    
	        	}
	        	returnObj = null;
	        }
	    }
	    var param ="title="+document.getElementById("title").value;
	    param =param + "&wrNum="+$("input[name=wrName]").val();
	    param =param + "&fileName="+$("input[name=logType]").val();
	    param =param + "&filter="+selectedFilters;
	    param =param + "&date="+document.getElementById("date").value;
	    var dd = new Date();
	    param =param + "&time="+dd.getTime();
	    xmlhttp.open("GET",contextPath+"/matrixAnalyser/MatricsDashboardAction.do?"+param,true);
	    xmlhttp.send();

	    
	    

	},
	addKeyword:function(){
		var addVal = document.getElementById("filter").value;
		var flag = false;
		$("#selectedFilter option").each(function(i){
	        if($(this).val()==addVal)
	        	flag = true;
	    });
		if(!flag)
			$("#selectedFilter").append("<option value="+addVal+">"+addVal+"</option>");
	},
	removeKeyword:function(){
		$("#selectedFilter option").each(function(i){
	        if($(this).val()==$("#selectedFilter").val())
	        	$(this).remove();
	    });
	}
};