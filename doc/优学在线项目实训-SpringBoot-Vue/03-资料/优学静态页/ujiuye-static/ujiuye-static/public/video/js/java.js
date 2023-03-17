

$(function(){

	//线路
	$(".xianlu a").hover(function(){
		
		$(this).addClass("hover").siblings().removeClass("hover");
	
    });

	
	//视频底部小图标
	$(".zg_video_bot ul li").each(function(){
		$(this).click(function(){
			$(this).addClass("hover");
			
			  
		})
		$(this).mouseleave(function(){
			$(this).removeClass("hover");
			
		})
	})
	$(".guanbi").click(function(event){
		$(this).parents(".fenxiang").hide();
		  event.stopPropagation();	
	})
	
	$(".mulunr li").each(function(){
		$(this).hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
		})
	})
	
	//目录
	$(".zg_mulu_bt span").each(function(c) {
        $(this).click(function(){
		$(".zg_mulu_bt span").removeClass("on");
		$(this).addClass("on");
		$(".tablecont").hide();
		$(".tablecont:eq("+c+")").show();
	})
    });
	
	//我的笔记
	$(".biji span").each(function(c) {
        $(this).click(function(){
		$(".biji span").removeClass("on");
		$(this).addClass("on");
		$(".bijinr").hide();
		$(".bijinr:eq("+c+")").show();
	})
    });

	//我的提问
	$(".tiwen span").each(function(c) {
        $(this).click(function(){
		$(".tiwen span").removeClass("on");
		$(this).addClass("on");
		$(".tiwennr").hide();
		$(".tiwennr:eq("+c+")").show();
	})
    });
	
	//点击显示隐藏
	// $(".zg_tiwen a.huida").click(function(){
		// $(this).parent().next().fadeIn()
	// })
	// $(".zg_huida .but2").click(function(){
		// $(this).parent().fadeOut()
	// })


	//本节介绍
	$(".zg_jieshao_bt span").each(function(c) {
        $(this).click(function(){
		$(".zg_jieshao_bt span").removeClass("hover");
		$(this).addClass("hover");
		$(".zg_jieshao_cont").hide();
		$(".zg_jieshao_cont:eq("+c+")").show();
	})
    });

	//小视频划过
	$(".zg_shipin_cont").hover(function(){
		$(this).children(".vidmail").show();
	},function(){
		$(this).children(".vidmail").hide();
	})

})

//小屏消失功能
// var small_open_flag = true; //小窗口开启 确定开关
// $(function(){
		// var topMain=$(".zg_video").height()//是头部的高度加头部与nav导航之间的距离。
		// var nav=$(".zg_shipin_cont");
		// var display_flag = true;
		// var display_none_flag = true;
		// var display_open_flag = false;
		// setTimeout(function(){
		// if (login_flag == true && open_status == true){
			// //getuflowplayers();//
			// $(window).scroll(function(){
				// if ($(window).scrollTop()>topMain && small_open_flag){//如果滚动条顶部的距离大于topMain则就nav导航就添加类.nav_scroll，否则就移除。
					// nav.addClass("samll_video");
					// nav.css({cursor:"move",position: "fixed"});
					// nav.attr({"onMousedown":'MoveDiv(this, event);'});
					// $(".vidmail em").css({cursor:"default",position:"absolute"});

					// $(".vidmail em").click(function(){
						// $(this).css({cursor:"default",position:"static"});
						// nav.removeClass("samll_video");
						// nav.css({cursor:"default",position:"static"});
						// nav.attr({"onMousedown":''});
						// small_open_flag = false;//关闭开关
					// })
				// }else{
					// nav.removeClass("samll_video");
					// nav.css({cursor:"default",position:"static"});
					// nav.attr({"onMousedown":''});
					// nav.show()
					// $(".vidmail em").css({cursor:"default",position:"absolute"});
				// }
				
				
				// $open_num = $(window).scrollTop() - topMain;
				// if($open_num <= -3 && display_open_flag == true){
					// if(display_flag == true){
						// getuflowplayers();//
						// display_flag = false;
						// display_none_flag = true;
					// }
				// }else{
					// if(display_none_flag == true){
						// setuflowplayers();//
						// display_flag = true;
						// display_none_flag = false;
					// }
					// display_open_flag = true;
				// }
				
				// if(small_open_flag == false){//窗口关闭后，检验是否拥有开启条件
					// if($open_num <= -3){
						// small_open_flag = true;//开启开关
					// }
				// }
			// });
		// }
		// },1000);
		
// })