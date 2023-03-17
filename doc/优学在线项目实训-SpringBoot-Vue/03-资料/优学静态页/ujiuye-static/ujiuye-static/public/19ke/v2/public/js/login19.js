// JavaScript Document
$(function(){
//登录后
	$(".zg_personal").mouseenter(function(){
		$(this).addClass("zg_perHov");
		$(".zg_person_list").show();
	}).mouseleave(function(){
		$(".zg_person_list").hide();
		$(this).removeClass("zg_perHov");
	});
	//密码睁眼闭眼效果
	$(".eyes").click(function () {
		if ($(this).hasClass("eyes_open")) {
			mimaVal = $(".zg_pwd").val();
			$(".eyes").removeClass("eyes_open");
			$(".zg_pwd").hide();
			$(".zg_pws").show();
			$(".zg_pws").focus();
			$(".zg_pws").val(mimaVal);

		} else {
			var defaultVal = $(".zg_pws").val();
			console.log(defaultVal);
			$(".eyes").addClass("eyes_open");
			$(".zg_pwd").show();
			$(".zg_pws").hide();
			$(".zg_pwd").focus();
			$(".zg_pwd").val(defaultVal);
		}

	});
	$(".zg19_pop .areaClose").click(function(){
		$(".mask,.zg19_pop").hide();	
	});
	$(".zg19_reg").click(function(){
		$(".zg19_zcpop").show();
		$(".zg19_loginpop").hide();
	});
	$(".zg19_log").click(function(){
		$(".zg19_zcpop").hide();
		$(".zg19_loginpop").show();
	});
});
