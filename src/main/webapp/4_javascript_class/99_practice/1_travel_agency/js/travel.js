window.onload = function(){

  	var adult = document.getElementById('adult');
 	var child = document.getElementById('child');
 	var baby = document.getElementById('baby');

	adult.oninput = calc;	
	child.oninput = calc;	
	baby.oninput = calc;
	
	function calc(){
		var total =document.getElementById('total');
		total.value =adult.value* 39000 + child.value*29000 + baby.value*19000;	
	}
	var btn1 =document.getElementById(btn1);	
	var btn2 =document.getElementById(btn2);
	
	btn1.onclick = agree1;	
	btn2.onclick = agree2;
	
	var agree1 =document.getElementById(agree1);
	var agree2 =document.getElementById(agree2);
	
	function agree(evt){
		
		if(!agree1.checked ||!agree.checked){
		alert('반드시 체크해주셔야합니다');
		return;
		}alert('확인')
		
	}
	
	


}