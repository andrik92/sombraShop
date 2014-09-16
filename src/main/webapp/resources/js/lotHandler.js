$(document).ready(
		function() {
			//Disable place bid button by default
			$(".placeBidButton").addClass("disabled");
			
			//handle click to modal window
			$("#biddingForm .placeBidButton").click(
					function(event) {
						event.preventDefault();
						resetOrButton();
						$(".modal-body").find(".biddingInput").slideUp();
						$(".modal-body").find("#block1").slideDown();
						var bidAmount = $("#biddingForm input[name=bidAmount]").val();
						$("#block1").find("em").text(bidAmount+" UAH");
						$("#block1").find("input[name=bidAmount]").val(bidAmount);
						$("#modalPlaceBid").modal();
			});

			//Validation of inputs
			$("input[name=bidAmount]").on("change keyup",function() {
				var minBid = +$(this).parent().find("input[name=minBid]").val();
				if (+$(this).val() < minBid) {
					$(".biddingInput").addClass("has-error");
					$(this).parent().parent().find(".placeBidButton").addClass("disabled");
				} else {
					$(".biddingInput").removeClass("has-error");
					$(this).parent().parent().find(".placeBidButton").removeClass("disabled");
				}
			});
			
			//shift bettwen different optins in modal window
			$("#anotherBid").click(function() {
				toggleOrButton();
				$(this).parent().find(".panel-body").slideToggle("slow");
			});
			
			//Toggle shift button text
			function toggleOrButton() {
				var btn = $("#anotherBid");
				var mainText = btn.data("main");
				var altText = btn.data("shift");
				if (btn.text() == mainText) {
					btn.text(altText);
				} else if (btn.text() == altText) {
					btn.text(mainText);
				}
			}
			
			//Reset shift button text to default
			function resetOrButton() {
				var btn = $("#anotherBid");
				var mainText = btn.data("main");
				if (btn.text() != mainText) {
					btn.text(mainText);
				}
			}
			

			//Watchlist buttons
			$(".watchButton").click(function(event) {
				event.preventDefault();
				var btn = $(this);
				btn.button("loading");
				var url = $(this).attr("href");
				$.get(url, function(response) {
					// $(".watchButton").toggle();
					$(".watchButton").toggleClass("hide");
					btn.button("reset");
				});
			});
		});