window.onload = function() {
let tds = document.getElementsByClassName("item");
for (let i = 0; i < tds.length; i++) {
tds[i].onclick = function(evt) {
let tdprice = evt.target.getAttribute('data-price');
window.alert(tdprice);
}
}
}