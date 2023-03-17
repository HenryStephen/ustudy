// JavaScript Document
$(function(){
	/*input-focus*/
	$('.soInpt').each(function(){  
		var txt = $(this).val();  
		$(this).focus(function(){  
		if(txt === $(this).val()) $(this).val("");  
		}).blur(function(){  
		if($(this).val() == "") $(this).val(txt);  
		});  
	}) 
	/*首页加入我们--图片列表*/
	$(".zg_tab td").hover(function() {
		$(".zg_tab td img").css("opacity",0.8);
		$(this).children("img").css("opacity",1);
		$(this).find(".school").show();
	},function() {
		$(".zg_tab td img").css("opacity",1);
		$(this).find(".school").hide();
	});	
	/*顶部导航下拉菜单*/
	$(".top_list li").each(function(){
		$(this).mousemove(function(){
			$(this).find(".dropbox").show();
		}).mouseout(function(){
			$(this).find(".dropbox").hide();
		})
	});
	$(".ing_li").hover(function(){
		$(this).addClass("ing_li_on").siblings().removeClass("ing_li_on");
	})
	
	/*首页课程模块滑过*/
	$(".kc270_li").each(function(){
		$(this).mousemove(function(){
			$(this).find(".li_inbg").show();
			$(this).find(".li_info").show();
		}).mouseout(function(){
			$(this).find(".li_inbg").hide();
			$(this).find(".li_info").hide();
		})
	});
	
	
})
