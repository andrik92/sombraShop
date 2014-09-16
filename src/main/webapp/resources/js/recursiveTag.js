
	window.onload = function() {
		var hash = (window.location.hash);
		if (hash == '#98') {

			$("#dialog").dialog({
				dialogClass : 'no-close',
				modal : true,
				width:'34%'
			});
			$(".btn").click(function(event) {
				$("#dialog").dialog('close');
						});
		};
		if (hash == '#88'){

			$("#error").dialog({
				dialogClass : 'no-close',
				modal : true,
				width:'30%',
				height: 129
			});
			$(".btn").click(function(event) {
				$("#error").dialog('close');
						});
		}
		if (hash == '#addcat'){
			$("#addCategory").dialog({
			dialogClass : 'no-close',
			modal : true,
			width:'24%'
		});
$(".btn").click(function(event) {
	$("#addCategory").dialog('close');
			});
		}

	};
	