function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#mainForm").submit();
}

function sort(orderBy, defaultOrder) {
	if ($("#orderBy").val() == orderBy) {
		if ($("#order").val() == "") {
			$("#order").val(defaultOrder);
		}
		else if ($("#order").val() == "desc") {
			$("#order").val("asc");
		}
		else if ($("#order").val() == "asc") {
			$("#order").val("desc");
		}
	}
	else {
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}
	$("#mainForm").submit();
}

function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$("#mainForm").submit();
}
function getOs() 
{ 
	var OsObject = ""; 
	if(navigator.userAgent.indexOf("MSIE")>0) { 
		return "MSIE"; 
	} 
} 
var  highlightcolor='#c1ebff';
var  clickcolor='#51b2f6';
function  changeto(event){
	if(navigator.userAgent.indexOf("MSIE")>0){
		source=event.srcElement;
		if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
		while(source.tagName!="TD")
		source=source.parentElement;
		source=source.parentElement;
		cs  =  source.children;
		if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0; i<cs.length; i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
	}
}

function  changeback(event){
	if(navigator.userAgent.indexOf("MSIE")>0){
		if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
		return
		if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
}