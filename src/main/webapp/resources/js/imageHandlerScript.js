$(document)
		.ready(

				function() {

					var onse = true;

					$('#imageOne')
							.click(
									function() {
										if (onse) {

											for (var id = 0; id < jsonLength; id++) {

												var element = $("<div  class='item'>");
												if (id == 0) {
													element.attr("id",
															'activeItems');

													element.addClass('active ');
												}

												element
														.attr("activeItems",
																+id);

												var img = $("<img class='img-responsive center-block'>");
												img.attr("id", +id);
												img
														.attr(
																"src",
																'data:image/png;base64,'
																		+ json[id].image);
												img.appendTo(element);

												element
														.appendTo("#thisCarousel");

												$("#copynext").click(
														function() {
															nextImage();
														});
												$("#copyprevious").click(
														function() {

															previousImage();
														});
											}
											onse = false;
										}

									});

					$('#product-details').on('hidden', function() {
						$('#product-details .inner').remove();
					});

					getImages(null, null);

					holders = [];
					var json;
					var jsonHasItems = false;
					var jsonLength = 0;
					var imageIndex = 0;

					$("[name='next']").click(function() {
						nextImage();
					});

					$("[name='previous']").click(function() {
						previousImage();
					});

					var initDone = false;
					function smallPreview(json) {

						var mainWidth;
						$("#mainMove").css("margin-left", "0");
						$("#mainMove").css("margin-right", "0");

						/*
						 * Init elements depending on json length
						 * 
						 */
						for (var id = 0; id < jsonLength; id++) {

							/*
							 * thumbnail div
							 */
							var outerholder = $("<div id='outerholder' >");
							outerholder.prop("id", "outerholder" + id);
							outerholder.prop("class", "col-md-2 thumbnail");
							outerholder.css("padding-right", "0");
							outerholder.css("padding-left", "0");
							outerholder.css("width", "90px");
							outerholder.css("z-index", "10");
							outerholder.css("opacity", "0.5");
							holders[id] = outerholder;

							/*
							 * add click events
							 */
							(function(_outerholder) {
								outerholder.click(function() {

									var thisId = _outerholder.attr("id")
											.replace("outerholder", "");
									// alert(ident);
									imageIndex = thisId;
									showImage(imageIndex, json);

								}

								);
							})(outerholder);

							/*
							 * holder div
							 */
							var holder = $("<div id='holder' >");
							holder.prop("id", +id);
							holder.prop("class", "imageSmall");
							holder.prop("style", +"float: left; z-index: 1;'");
							holder.appendTo(outerholder);

							/*
							 * innerholder div
							 */
							var innerholder = $("<div id='innerholder' >");
							innerholder.prop("id", +id);
							innerholder
									.prop("class",
											"trickSmall  class='img-responsive center-block; display:table-cell;");
							innerholder.appendTo(holder);

							/*
							 * image
							 */
							var img = $("<img id='dynamic'  >");
							img.attr("id", +id);

							img.attr("src", 'data:image/png;base64,'
									+ json[id].image);
							img.prop("style", "z-index: 1;");
							img.addClass('img');
							// display:inline-block;margin:auto;
							img.appendTo(holder);

							outerholder.appendTo('#mainMove');
							initDone = true;
						}
						if (initDone) {
							mainWidth = parseInt(holders.length)
									* (parseInt(holder.width()));
						} else {
							mainWidth = 0;
						}

						
						var button1 = $("<button id='buttonOne'   ><</button>");
						var button2 = $("<button id='buttonTwo'   >></button>");
						button2.attr("style",
								"glyphicon glyphicon-chevron-right");
						button1
								.attr("style",
										"position:absolute; bottom:20%;left:0%; opacity: 0.6;");
						button2
								.attr("style",
										"position:absolute; bottom:20%;right:0%; opacity: 0.6;");
						button1.appendTo('#outerVisbility');
						button2.appendTo('#outerVisbility');

						$("#outerVisbility").mouseenter(function() {
							$("#mainMove").show();
							$("#buttonOne").show();
							$("#buttonTwo").show();
						});
						$("#outerVisbility").mouseleave(function() {
							$("#mainMove").hide();
							$("#buttonOne").hide();
							$("#buttonTwo").hide();
						});

						$("#buttonOne").mouseup(function() {
							var move = $("#movedContainer");
							var left = move.position().left;

							if (left < 0) {
								move.animate({
									left : '+=30'
								}, 150, 'swing', true);
							}

						});
						$("#buttonTwo").mouseup(function() {
							var move = $("#movedContainer");
							var width = move.width();
							var dif = mainWidth - width;
							var left = move.position().left;
							var execute = false;

							if (left <= 0) {

								if (Math.abs(left) < dif) {
									execute = true;
								}
								;
							} else {

								execute = true;

							}

							if (execute) {
								move.animate({
									left : '-=30'
								}, 150, 'swing', true);
							}

						});

					}
					function getImages(url, id) {

						var id = $("input[name=lotId]").val();

						$.ajax({

							type : "GET",
							url : "/showLotImages/" + id + "/",

							success : function(response) {
								var json_obj = response;
								json = json_obj;
								if (json_obj[0] != null) {

									jsonHasItems = true;
									jsonLength = countProperties(json_obj);
								}

							}

						}).done(function() {

							showImage(0, json);
							
							if (jsonHasItems) {
								smallPreview(json);
							}
						});
						;
					}
					;

					function nextImage() {
						var j = imageIndex + 1;
						if (json[j] == null) {
							j = 0;
						}

						if (j > (jsonLength - 1)) {
							j = 0;
						}

						showImage(j, json);
						imageIndex = j;

					}
					;

					function previousImage() {

						var j = imageIndex - 1;

						if (j < 0) {
							j = jsonLength - 1;
						}

						showImage(j, json);
						imageIndex = j;

					}
					;

					function showImage(index, json) {
						if (jsonHasItems) {
							if (index == 0) {
								index = 0;
							}

							
							
							$("[name='imageOne']").attr(
									"src",
									'data:image/png;base64,'
											+ json[index].image);
						}

						else {
							

							$("[name='imageOne']").attr("src",
									"/resources/images/no-image-blog-one.png");

						}
					}
					;
					function countProperties(obj) {
						var prop;
						var length = 0;
						for (prop in obj) {
							length++;

						}
						return length;
					}
					;

				});
