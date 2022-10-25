$(document).ready(function(){
	
	
	$('p#intro').animate({
		padding : '20px',	
		fontSize: '30px'
	}, 2000);
	
	$('#navigation >ul > li').hover(function(){
		$(this).animate({paddingLeft :'+=15px'}, 200);
	}, function(){
		
	});
	
	// 고정 퀵메뉴 원리
	$(window).scroll(function(){
		$('#navigation').css({'top':$(document).scrollTop()});	
	});
});