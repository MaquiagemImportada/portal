(function($){
  $(function(){

    ajustarBody();
    $(window).resize(function(){
    	ajustarBody();
    });
    $('.tabs').tabs();
    $('.sidenav').sidenav();
    $('.dropdown-trigger').dropdown();

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