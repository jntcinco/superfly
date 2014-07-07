//Tool scripts for the superfly pages.

// Use tekusource variable instead using jQuery or $ to avoid conflict.
var tekusource = jQuery.noConflict();
var paramPage;
// All ajax implementations are inside superflyAjax variable closure.
var superflyAjax = {
	getContent : function(page) {
		tekusource.ajax({
			url : "/superfly-webapp/tekusource/admin/getContent",
			/*url : "/tekusource/admin/getContent",*/
			data : {page : page},
			type : "GET",
			success : function(response) {
				CKEDITOR.instances.editor1.setData(response.textContent, "info");
			},
			error : function(xhr) {
				superflyEffects.showMessageDialog("Error Code: "+xhr.status, "error");
			}
		});
	},
	saveConent : function() {
		var content = CKEDITOR.instances.editor1.getData();
		tekusource.ajax({
			url : "/superfly-webapp/tekusource/admin/saveContent",
			/*url : "/tekusource/admin/saveContent",*/
			type : "GET",
			data : {contentParam : content, page : paramPage},
			success : function(response) {
				superflyEffects.showMessageDialog(response.message, "info");
			},
			error : function(xhr) {
				superflyEffects.showMessageDialog("Error Code: "+xhr.status, "error");
			}
		});
	},
	uploadImage : function() {
		tekusource("#fileUploadForm").submit(function(e) {
			var formObj = tekusource(this);
			var formData = new FormData(this);
			tekusource.ajax({
				url : "/superfly-webapp/tekusource/file/upload",
				/*url : "/tekusource/file/upload",*/
				type: 'POST',
				data:  formData,
				mimeType:"multipart/form-data",
				contentType: false,
				cache: false,
				processData:false,
				success: function(data, status, req) {
					var json = tekusource.parseJSON(data);
					superflyEffects.showMessageDialog(json.message, "info");
				},
				error: function(req, status, error) {
					superflyEffects.showMessageDialog(status, "error");
				}        
			});
			e.preventDefault(); //Prevent Default action.
		});
		tekusource("#fileUploadForm").submit(); //Submit the form
	},
	addSalePackage : function() {
		var modelAttribute = tekusource("#addSalePackageForm").serialize();
		tekusource.ajax({
			url : "/superfly-webapp/tekusource/salePackagePrice/addSalePackage",
			/*url : "/tekusource/salePackagePrice/addSalePackage",*/
			type : "POST",
			data : modelAttribute,
			success : function(response, textStatus, xhr) {
				superflyEffects.showMessageDialog(response.message, "info");
			},
			error : function(response, textStatus, xhr) {
				superflyEffects.showMessageDialog(textStatus, "error");
			}
		});
	},
};

// All effect,css,forms,attributes,etc implementations are inside superflyEffects variable closure.
var superflyEffects = {
		fileUploadDialog : function() {
			tekusource("#fileUploadDiv").dialog({
				resizable: true,
				autoOpen:false,
				modal: true,
				width:400,
				height:300,
				buttons: {
					'Upload': function() {
						superflyAjax.uploadImage();
						tekusource(this).dialog('close');
					},
					'Cancel': function() {
						tekusource(this).dialog('close');
					}
				}
			});
		},
		contentDialog : function() {
			tekusource("#contentDiv").dialog({
				resizable: true,
				autoOpen:false,
				modal: false,
				width:600,
				height:400,
				buttons: {
					'Save': function() {
						superflyAjax.saveConent();
						tekusource(this).dialog('close');
					},
					'Cancel': function() {
						tekusource(this).dialog('close');
					}
				}
			});
		},
		addSalePackageDialog : function() {
			tekusource("#addSalePackageDiv").dialog({
				resizable: true,
				autoOpen:false,
				modal: false,
				width:400,
				height:300,
				buttons: {
					'Save': function() {
						superflyAjax.addSalePackage();
						tekusource(this).dialog('close');
					},
					'Cancel': function() {
						tekusource(this).dialog('close');
					}
				}
			});
		},
		messageDialog : function() {
			tekusource("#msgDiv").dialog({
				resizable: true,
				autoOpen:false,
				modal: false,
				width:400,
				height:200,
				buttons: {
					'Ok': function() {
						tekusource(this).dialog('close');
						location.reload();
					}
				}
			});
		},
		populateDialog : function(pageName) {
			if(tekusource.trim(pageName) == "cms_page") {
				superflyEffects.fileUploadDialog();
				superflyEffects.contentDialog();
				superflyEffects.addSalePackageDialog();
			}
			superflyEffects.messageDialog();
			tekusource('#progress').hide();
			
			tekusource(document).ajaxStart(function () {
				tekusource("#progress").show();
			});
			tekusource(document).ajaxStop(function () {
				tekusource('#progress').hide();
			});
		},
		showPopupFileUploadForm : function() {
			tekusource("#fileDescription").val("");
			var controlInput = tekusource("#file");
	        controlInput.replaceWith(controlInput = controlInput.val('').clone(true));
			tekusource('#fileUploadDiv').dialog('open');
		},
		showPopupHomeContentForm : function() {
			paramPage = "About";
			superflyAjax.getContent(paramPage);
			tekusource('#contentDiv').dialog('open');
		},
		showPopupAwardsContentForm : function() {
			paramPage = "Awards";
			superflyAjax.getContent(paramPage);
			tekusource('#contentDiv').dialog('open');
		},
		showAddSalePackageForm : function() {
			tekusource("#addSalePackageDiv").dialog("open");
		},
		showMessageDialog : function(msg, type) {
			tekusource(".msgDivClass").dialog('open');
			tekusource(".msgDivClass").css({"font-size":"14px",
									        "font-weight":"bold",
									        "padding-top":"20px",
									        "text-align":"center"});
			if(tekusource.trim(type) == "error") {
				tekusource(".msgDivClass").css({"color":"#FF0000"});
			} else {
				tekusource(".msgDivClass").css({"color":"#AA9550"});
			}
			tekusource(".msgDivClass .msgBox").html(msg);
		}
}


/*jQuery.fn.center = function () {
    this.css("position", "absolute");
    this.css("top", ($(window).height() - this.height()) / 2 + $(window).scrollTop() + "px");
    this.css("left", ($(window).width() - this.width()) / 2 + $(window).scrollLeft() + "px");
    return this;
}*/