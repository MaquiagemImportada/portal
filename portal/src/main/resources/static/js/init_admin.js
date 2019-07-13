var MI = MI || {};

MI.Security = (function(){
	function Security(){
		//this token = $('input[name=_csrf]').val();
		//this header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function(){
		$(document).ajaxSend(function(event, jqxhr, settings){
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
}());

(function($){
  $(function(){

    ajustarBody();
    $(window).resize(function(){
    	ajustarBody();
    });
    $('.tabs').tabs();
    $('.sidenav').sidenav();
    $('.dropdown-trigger').dropdown();
    
    try{
    	$("#checkAtivo").change(function(){setarAtivo()});
    }catch(e){
    	console.log(e);
    }

  }); // end of document ready
})(jQuery); // end of jQuery name space

function ajustarBody(){
	if($( window ).width() < 992){
		$("div#breadcrumb-container").css("padding-left","20");
		$("a#logo-container").css("padding-left","0");
		$("div#body").css("padding-left","0");
		$("footer").css("padding-left","0");
	}else{
		$("div#breadcrumb-container").css("padding-left","320px");
		$("a#logo-container").css("padding-left","300px");
		$("div#body").css("padding-left","300px");
		$("footer").css("padding-left","300px");
	}
}

function addCsrfToken(xhr){
	var token = $('input[name=_csrf]').val();
	var header = $('input[name=_csrf_header]').val();
	xhr.setRequestHeader(header,token);
}

function aviso(mensagem){
	M.toast({html: mensagem});
}

function setarAtivo(){
	if($("#checkAtivo:checked").length > 0){
		$("#ativo").val("true");
	}else{
		$("#ativo").val("false");
	}
}