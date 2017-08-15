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

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		locale:'fr',
		defaultDate: '2017-05-12',
		navLinks: true, // can click day/week names to navigate views
		selectable: true,
		selectHelper: true,
		select: function(start, end) {
			$('#modal').modal('show');
			var eventData;
			
			$("#button-form-event").click(function(){
				start = $.fullCalendar.formatDate(start, "yyyy-MM-dd HH:mm:ss");
				end = $.fullCalendar.formatDate(end, "yyyy-MM-dd HH:mm:ss");
				
				var classeId = $("input[name='classe_id']").val(),
					etabId = $("input[name='etab_id']").val(),
					profId = $("select[name='personne_id'] option:selected").val(),
					matId = $("select[name='matiere_id'] option:selected").val(),
					salleId = $("select[name='salle_id'] option:selected").val();
				/*
				$.ajax({
					url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/test',
					method:'GET',
					data:{
						personne_id:profId,
						salle_id:salleId,
						matiere_id:matId,
						classe_id:classeId, 
						etab_id:etabId,
						dateD: start,
						dateF: end
					},
					success:function(datas){
						alert(datas);
					}
				});
				*/
			});
					
			eventData = {
				start: start,
				end: end
			};
			$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
			$('#calendar').fullCalendar('unselect');
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		events: [
			{
				title: 'All Day Event',
				start: '2017-05-01'
			},
			{
				title: 'Long Event',
				start: '2017-05-07',
				end: '2017-05-10'
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: '2017-05-09T16:00:00'
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: '2017-05-16T16:00:00'
			},
			{
				title: 'Conference',
				start: '2017-05-11',
				end: '2017-05-13'
			},
			{
				title: 'Meeting',
				start: '2017-05-12T10:30:00',
				end: '2017-05-12T12:30:00'
			},
			{
				title: 'Lunch',
				start: '2017-05-12T12:00:00'
			},
			{
				title: 'Meeting',
				start: '2017-05-12T14:30:00'
			},
			{
				title: 'Happy Hour',
				start: '2017-05-12T17:30:00'
			},
			{
				title: 'Dinner',
				start: '2017-05-12T20:00:00'
			},
			{
				title: 'Birthday Party',
				start: '2017-05-13T07:00:00'
			},
			{
				title: 'Click for Google',
				url: 'http://google.com/',
				start: '2017-05-28'
			}
		]

	});

});