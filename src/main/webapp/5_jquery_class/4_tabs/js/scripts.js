$(function(){
	
	var topDiv = $('.tabSet');
	var anchors = topDiv.find('ul.tabs a'); //('ul.tabs>li>a')
	var panelDiv = topDiv.find('div.panels>div.panel');
	
	anchors.show();
	panelDiv.hide();
	
	var lastAnchor = anchors.filter('.on');
	var lastPanel = $(lastAnchor.attr('href'));
	//alert(lastPanel);
	lastPanel.show();
	
	anchors.click(function(){
		var currentAnchor = $(this);
		var currentPanel = $(currentAnchor.attr('href'));
	
		lastPanel.hide();	
		currentPanel.show();
		
		lastAnchor.removeClass('on');
		currentAnchor.addClass('on');
		
		lastAnchor = currentAnchor;
		lastPanel = currentPanel;
			
		})
	
	
	
})