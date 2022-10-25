$(function(){
	
	var sum =0;
   $('select').change(function(){
   
   var tr = $('<tr/>');

   //2 메뉴 이름 저장
   var name = $(this).parent().find('span:eq(0)').attr('value');
   //3 주문 개수 저장
   var count = $(this).val();
   if(count==0){
	return;
}
   //4 가격 저장 후 총합 계산
   var price = $(this).parent().find('span:eq(1)').attr('value');
      sum+=count*price;
   $('#total').attr('value',sum); 
   
   var tdName =$('<td/>').text(name);
   var tdCount =$('<td/>').text(count);
   var tdPrice =$('<td/>').text(price*count);
   var tdDel =$('<td><button class="del">삭제</button></td>');   
   tr.append(tdName,tdCount,tdPrice,tdDel)
   $('#listTable').append(tr)

	})
	
	/* 주문내역의 삭제 버튼을 누를 경우 */
   $("#listTable").on("click", ".del", function() { //listTable안의 btn을 선택
      sum -= $(this).parent().parent().find('td:eq(2)').text();
      $('#total').attr('value',sum);
          $(this).parent().parent().remove();       //this(btn)의 부모(td)의 부모(tr)를 삭제
     });
})


   

