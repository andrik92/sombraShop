$(document)
		.ready(
				function() {
					var form = $('#form');
					var uploadButton = $('#submitButton');

					var outerColumnOne;
					var mainform;

					var json;
					var jsonHasItems = false;
					var jsonLength;

					var init = true;
					var id = 0;
					var lotId = $("#lotId").val();
					var deleteVar = $("#deleteVar").val();
					var removeVar = $("#removeVar").val();
					var cancelVar = $("#cancelVar").val();
					var selectVar = $("#selectVar").val();
					var addVar = $("#addVar").val();
					var submitVar = $("#submitVar").val();

					$(".jumbotron").css("background-color", "white");
					$.ajax({

						type : "GET",
						url : "/showLotImages/" + lotId + "/",

						success : function(response) {
							var json_obj = response;
							json = json_obj;

							if (json_obj[0] != null) {

								jsonHasItems = true;
								jsonLength = countProperties(json_obj);
							}
							;

						}
					}).done(function() {

						if (jsonHasItems) {

							for (var iter = 0; iter < jsonLength; iter++) {

								createContainer(true, iter);

							}
						}
					});
					function countProperties(obj) {
						var prop;
						var length = 0;
						for (prop in obj) {
							length++;

						}
						return length;
					}
					;

					uploadButton.hide();
					initMainDivs();

					function initMainDivs() {
						form.remove();
						outerColumnOne = $("<div id='outerColumnOne' class ='col-md-12'>");
						outerColumnOne.appendTo(preview);

						outerColumnTwo = $("<div id='outerColumnTwo' class ='col-md-2'>");

						mainform = $("<form id='formMain' method='POST' action='editLotImages' enctype='multipart/form-data'>");
						mainform.appendTo(outerColumnOne);
						row = $("<div id='row0' class='row' style='margin: 5px; border: 1px solid silver; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; background-color: white;'>");

						var button = $("<button id='buttonOne'class='btn' ></button>");
						button.text(addVar);
						button.prop("id", +id);

						button.addClass("form-control");
						button.appendTo(row);
						row.appendTo($("#outerColumnOne"));
						uploadButton = $("<input id='submitJQ' type='submit' class='btn btn-large btn-primary '>");
						uploadButton.attr('value', submitVar);
						uploadButton.addClass("form-control");
						uploadButton.appendTo(row);
						row.appendTo($("#outerColumnOne"));

						button.click(function() {
							createContainer(null, null);
						});

						uploadButton.click(function() {

							mainform.submit();
						});
					}
					;

					function createContainer(exists, index) {

						var img = $("<img id='' >");
						img.prop("id", "image" + id);
						if (exists != null) {
							img.prop("src", 'data:image/png;base64,'
									+ json[index].image);
						}

						var row = $("<div id='row' class ='row'>");
						row.prop("id", "row" + id);
						row.css("margin", "5px");
						row.css("background-color", "white");
						row.css("border", " 1px solid silver");
						row.css("border-radius", "5px");
						row.appendTo(mainform);

						var outerrow = $("<div id='outerrow' class ='row' style = 'height: 290px'> ");
						outerrow.prop("id", "outerrow" + id);

						var columnOne = $("<div id='col0' class ='col-md-5'>");
						columnOne.css("margin", "10px");
						columnOne.appendTo(row);
						columnTwo = $("<div id='col1' class ='col-md-4'>");
						columnTwo.css("margin", "10px");
						columnTwo.appendTo(row);
						columnThree = $("<div id='col2' class ='col-md-2'>");
						columnThree.css("margin", "10px");
						columnThree.appendTo(row);

						/*
						 * thumbnail div
						 */
						var outerholder = $("<div>");
						if (exists) {
							outerholder.prop("id", "outerholder"
									+ +json[index].pictureId);
						} else {
							outerholder.prop("id", "outerholder" + id);
						}

						outerholder.prop("class", "col-md-12 thumbnail");
						outerholder.css("width", "276px");
						outerholder.css("z-index", "10");
						if (!exists) {
							outerholder.hide();

						} else {
							outerholder.appendTo(columnOne);
						}

						var holder = $("<div>");
						if (exists) {
							outerholder.prop("id", "holder"
									+ +json[index].pictureId);
						} else {
							outerholder.prop("id", "holder" + id);
						}
						holder.prop("class", "image");
						holder.prop("style", +"float: left; z-index: 1;");
						holder.appendTo(outerholder);
						/*
						 * innerholder div
						 */
						var innerholder = $("<div>");
						innerholder.prop("id", 'innerholder' + id);
						innerholder.prop("class", "trick");
						innerholder.appendTo(holder);

						img.appendTo(holder);

						var rad = $("<input type='radio' name='isMain' value =''>");
						var radLabel = $("<label id = 'label'>Mark main</label>");
						radLabel.prop("id", 'label' + id);
						if (exists) {

							rad.prop("id", "existingRadiobutton"
									+ json[index].pictureId);
							radLabel
									.prop("id", "label" + json[index].pictureId);

						} else {
							rad.prop("id", "rad" + id);
							radLabel.prop("id", "label" + id);
							rad.hide();
							radLabel.hide();
						}

						rad.prop("value", +id);

						if (init) {
							rad.prop('checked', true);
							init = false;
						}
						rad.hide();
						rad.appendTo(columnTwo);
						radLabel.appendTo(columnTwo);
						radLabel.hide();
						if (exists != null) {
							hidden = $("<input type='hidden' name='hidden'>");
							hidden.attr("id", "hidden" + json[index].pictureId);

							hidden.appendTo(columnTwo);
						} else {
							var span = $("<span class='btn btn-default btn-file'></span>");
							span.text(selectVar);
							var inputHidden = $("<input type='file' name='multipartFile'>");

							span.appendTo(columnTwo);
							inputHidden.attr("id", "inputFile" + id);
							inputHidden.appendTo(span);
							inputHidden.change(function() {

								pass = inputHidden.attr('id');

								outerholder.appendTo(columnOne);
								outerholder.show();
								setInputToImage(pass, img);

							});

						}
						if (exists != null) {

							var button1 = $("<button id='buttonTwo'class='btn btn-danger' ></button>");
							button1.text(deleteVar);
							button1.attr("id", 'buttonTwo'
									+ json[index].pictureId);
							button1.appendTo(columnThree);
							button1.click(function(e) {
								e.preventDefault();
								var thisId = event.target.id;
								button = $("body").find('#' + thisId);

								button.text(function(i, text) {
									return text === deleteVar ? cancelVar
											: deleteVar;
								});
								thisId = thisId.substring(9, this.length);
								holder = $("body").find('#holder' + thisId);

								holder.toggleClass("fadeRemoved");
								radioButton = $("body").find(
										'#existingRadiobutton' + thisId);
								radioLabel = $("body").find('#label' + thisId);

								var hiddenInput;
								hiddenInput = $("body")
										.find('#hidden' + thisId);
								function buttonValueChange(input) {

									if (input.text() == cancelVar) {
										return thisId;
									} else {
										return "";
									}

								}
								hiddenInput.val(buttonValueChange(button));

							});
						} else {
							var button1 = $("<button id='buttonOne'class='btn btn-danger' ></button>");
							button1.text(removeVar);
							button1.attr("id", 'buttonOne' + id);

							button1.appendTo(columnThree);

							button1.click(function(e) {

								e.stopImmediatePropagation();
								var index = event.target.id;

								var decr;
								var button_id;
								var loop = true;

								var doc;
								var actualId = index.substring(9, 10);
								actualId = parseInt(actualId);

								while (loop) {

									decr = actualId - 1;
									rad_id = 'rad'.concat(actualId);
									button_id = '';
									button_id = 'buttonOne'.concat(actualId);

									doc = $("body").find('#' + rad_id);
									if (!doc.attr('id')) {
										break;
										loop = false;
									}
									button = $("body").find('#' + button_id);
									doc.attr({
										value : (decr)
									});

									newId = "buttonOne".concat(decr);
									button.prop("id", newId);
									if (!doc.attr('id')) {
										loop = false;
									}
									actualId++;
								}

								row.remove();
								outerrow.remove();
								id--;

							});
						}

						id++;
					}

					function setInputToImage(pass, img) {

						var input;
						input = document.getElementById(pass);
						if (!window.File || !window.FileReader
								|| !window.FileList || !window.Blob) {
							alert('The File APIs are not fully supported in this browser.');
							return;
						}

						input = document.getElementById(pass);
						if (!input) {
							alert("Couldn't find the fileinput element.");
						} else if (!input.files) {
							alert("This browser doesn't seem to support the `files` property of file inputs.");
						} else if (!input.files[0]) {

							alert("Please select a file before clicking 'Load'");
						} else {
							if (input.files && input.files[0]) {

								var reader = new FileReader();

								reader.onload = function(e) {

									var file = e.target.result;
									img.attr('src', file);
								};

								reader.readAsDataURL(input.files[0]);
							}
							;
						}
					}

				});
