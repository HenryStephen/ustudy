// JavaScript Document
function initPage()
{
$(function () {
	//判断是否宽屏
	//var winWide = window.screen.width;
	var winWide = document.body.clientWidth
	//alert(winWide)
	var wideScreen = false;
	if (winWide <= 1200) {//1024及以下分辨率
		$("#skin").attr("href", "/public/css/offcn_style960.css");
		
	} else {
		$("#skin").attr("href", "/public/css/offcn_style1200.css");
		wideScreen = true; //是宽屏
	}
})
}

window.onload =function(){

	initPage();
	$(window).resize(function(){
		initPage();
	});
}
