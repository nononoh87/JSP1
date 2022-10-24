$(function() {
	$('#hideButton').click(function() {
		$('#intro').hide();
		$('#navigation').hide();
	});

	$('#showButton').click(function() {
		$('#intro').show();
		$('#navigation').show();
	});

	$('#toggleButton').click(function() {
		$('#intro').toggle();
		$('#navigation').toggle();
		// fadeToggle(2000)
		// slideToggle(2000)

	});

	$('#toggleButton').click(function() {
		if ($('#intro').is(': vasible')) {
			$('#intro').fadeOut(1000)
		} else {
			$('#intro').fadeIn(1000)
		}
	});
	
	// 테이블의 내용중 홀수행의 배경색 지정
	//$('#celebs table.data >  tbody > tr:odd').css({'background':'lightblue'})
	$('#celebs table.data >  tbody > tr:odd').addClass('table_striping')
	
	//마우스 올라갔을때 배경색 바뀌고 다시 나오면 원색으로
	$('#celebs tr').hover(function(){
		$(this).addClass('td_mouseover');
	}, function(){
		$(this).removeClass('td_mouseover');
	})


});
	





