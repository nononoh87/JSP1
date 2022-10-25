window.onload = function(){

		var frm = document.getElementById('frm');
		var inputs = document.querySelectorAll("input");
		
		frm.onsubmit = function(evt){
			evt.stopPropagation(); //이벤트 전달 취소
				
			evt.preventDefault(); //이벤트 본래의 동작 취소
			
			//alert(frm.agree.checked);
			if(!frm.agree.checked){
				alert('반드시 체크해주셔야만 합니다.');
				return;
			}
			
			frm.onsubmit();
		}

	}