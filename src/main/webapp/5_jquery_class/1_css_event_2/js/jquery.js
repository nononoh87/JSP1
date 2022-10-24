$(function() {
	$('.header > .menu img').hover(function(){
		var src = $(this).attr('src');
		$(this).attr('src', src.replace('off','on'))
	},function(){
		var src = $(this).attr('src');
		$(this).attr('src', src.replace('on','off'))
	})
});
	