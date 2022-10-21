window.onload = function() {

	var adult = document.getElementById('adult');
	var child = document.getElementById('child');
	var infant = document.getElementById('infant');
	adult.oninput = calc;
	child.oninput = calc;
	infant.oninput = calc;

	function calc() {
		document.getElementById('total').value = adult.value * 39000 + child.value * 29000 + infant.value * 19000 + '원';
	}

	var submit = document.getElementById('submit');

	submit.onclick = function(evt) {
		evt.preventDefault();
		evt.stopImmediatePropagation();


		if (!checkbox.checked) {
			alert('반드시 여행약관에 동의하셔야 합니다.');
			return;
		}
		if (!checkbox2.checked) {
			alert('반드시 개인정보보호정책에 동의하셔야 합니다.');
			return;
		}
		if (!radio1.checked && !radio2.checked) {
			alert('반드시 성별을 체크하셔야 합니다.');
			return;
		}
		if (adult.value == 0 && child.value == 0 && infant.value == 0) {
			alert('반드시 0명 이상이셔야 합니다.');
			return;
		}
		if (!koreanName.value) {
			alert('한글 이름을 입력하셔야 합니다.');
			return;
		}
		if (!englishName1.value || !englishName2.value) {
			alert('영문 이름을 입력하셔야 합니다.');
			return;
		}
		if (!jumin1.value || !jumin2.value) {
			alert('주민번호를 입력하셔야 합니다.');
			return;
		}
		if (!email.value) {
			alert('이메일 주소를 입력하셔야 합니다.');
			return;
		}
		if (!tel1.value || !tel2.value || !tel3.value) {
			alert('전화번호를 바르게 입력하셔야 합니다.');
			return;
		}

		alert("submitted successfully");
	}
}