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
			$("#button-form-event").bind('click',function(){
				start = moment(start).format('YYYY-MM-DD HH:mm');
				end = moment(end).format('YYYY-MM-DD HH:mm');
				
				var classeId = $("input[name='classe_id']").val(),
					etabId = $("input[name='etab_id']").val(),
					profId = $("select[name='personne_id'] option:selected").val(),
					matId = $("select[name='matiere_id'] option:selected").val(),
					salleId = $("select[name='salle_id'] option:selected").val();
			
				
				$.ajax({
					url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/add',
					method:'POST',
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
						
						$("#calendar").fullCalendar('refetchEvents');	
					}
				});
				$("#button-form-event").off("click");
				
			});
					
			$('#calendar').fullCalendar('unselect');
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		events: {
			url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/getEvents',
			type:"POST",
			dataType:"json",
			data:{classe_id:$("input[name='classe_id']").val()},
	        error: function() {
	            alert('Erreur pendant le chargement des evenements');
	        },
	    },
	    eventDrop: function(event, delta) {
	    	start = moment(event.start).format('YYYY-MM-DD HH:mm');
			end = moment(event.end).format('YYYY-MM-DD HH:mm');
			id = event.evenementid.toString();

	    	$.ajax({
				url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/edit',
				method:'POST',
				data:{	
					eventid:id,
					dateD: start,
					dateF: end
				},
				success:function(datas){
					$("#calendar").fullCalendar('refetchEvents');	
				}
			});
			console.log(event);
	    },
	    eventResize: function(event) {
	    	start = moment(event.start).format('YYYY-MM-DD HH:mm');
			end = moment(event.end).format('YYYY-MM-DD HH:mm');
			id = event.evenementid.toString();
			
	    	$.ajax({
				url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/edit',
				method:'POST',
				data:{	
					eventid:id,
					dateD: start,
					dateF: end
				},
				success:function(datas){
					
					$("#calendar").fullCalendar('refetchEvents');	
				}
			});
	    },
		eventRender: function(event, element) {
	        element.append("<div data-id='"+event.evenementid+"'></div>");
	        element.append(event.prof);
	        element.append(event.matiere); 
	        element.append('<button type="button" class="close rm-event" aria-label="Close"><span aria-hidden="true">&times;</span></button>')
	        element.find(".close").click(function() {
	        	id = event.evenementid.toString();
				
		    	$.ajax({
					url:'http://localhost:8080/GestionScolaireSpringWeb/evenement/delete',
					method:'POST',
					data:{	
						eventid:id,
					},
					success:function(datas){
			           $('#calendar').fullCalendar('removeEvents',event._id);	
					}
				});
            })
		}

	});

	$('input#couleurMatiere').simpleColorPicker();

});