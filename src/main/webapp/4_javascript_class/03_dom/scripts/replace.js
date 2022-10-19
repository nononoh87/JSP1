
// window.onload = function(){	
		
  var list = document.getElementById('list');
  var pic = document.getElementById('pic');
  var del = document.getElementById('del');

  // 리스트에서 선택(클릭했을 때)
  list.onclick = function(evt){
	var isbn = evt.target.getAttribute('data-isbn');
	//alert(isbn);
	if(isbn){
		var img = document.createElement('img');
		img.src = 'images/' +isbn+'.jpg';
		img.height =150;
		img.width =100;
		
		if(pic.getElementsByTagName("img").length==0){
			pic.appendChild(img);
			del.disabled = false;
			var btn = document.getElementById('del');
			btn.onclick = function () {
			pic.removeChild(img)
		}
			
			
		}else{
			pic.replaceChild(img, pic.lastChild);
			var btn = document.getElementById('del');
			btn.onclick = function () {
			pic.removeChild(img)
		}
		}
		
	}
}

  
  // 삭제 버튼 누르면 pic 아래 자식(img 태그)를 지운다
 /* del.onclick =function(){
	let btn = document.getElementById('del');
	pic.removeChild(img);
	
}*/
  
  
//};
