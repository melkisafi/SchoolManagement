$(document).ready(function(){
	//Ajax Get prob by etab
	if($("input#mode").val() == "edit") $("#profs").show();
	
	$("#etab").change(function(){
		if(this.value == ""){
			$("#professeurs").find('option.opts-pp').remove().end();
			$("#profs").hide();
		}
		else {
			$.ajax({
			    type: 'GET',
			    url: 'http://localhost:8080/GestionScolaireSpringWeb/classe/getEtabProf',
			    data:{idetab:this.value}
			}).done(function(data){
				$("#professeurs").find('option.opts-pp').remove().end();
				$("#professeurs").append(data);
			});
			
			$("#profs").show();
		}
	});
});