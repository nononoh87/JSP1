$(function(){
	
	$('#bio h3').css({'cursor':'pointer'});
	
	$('#bio div').hide();
	$('#bio h3').show();
	
	// 이름을 클릭하면 해당 사진만 나오도록
	$('#bio h3').click(function(){
		//$(this).next().show();
		$(this).next().animate({'height':'toggle'}, 
		2000, 'easeOutBounce');
	})
	
	
});