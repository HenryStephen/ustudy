// JavaScript Document
//我的直播课程表
$(function() {
	var page = 1;
	//var i = 7; //每版放7个日期
	var sli_w = $(".live_sli").width();
	
	var old_v_width = $(".live_slider_cont").width();//日期列表实际宽度
	var s_licont = Math.ceil( old_v_width / sli_w ); 
	//实际将能盛下的日期 li 个数（固定li个数宽）
	var dis_w = old_v_width -( sli_w * s_licont);//实际宽与 li固定个数宽 的差值
	var v_width = old_v_width - dis_w;   
/*	console.log( old_v_width );
	console.log(v_width);
*/	var $v_show = $("div.live_s_list");
	var len = $v_show.find("li").length;
	var page_count = Math.ceil(len / s_licont );
	$(".live_libg").width(sli_w);
	
/*		console.log(s_licont);
		console.log(page_count);
*/		
	//点击右侧
	$(".live_slide_r").click(function() {
		/*var w =  $(".advlive_slider_cont").width();
		alert( w );*/
		
		if (!$v_show.is(":animated")) { //判断“视频内容展示区域”是否正在处于动画
			if (page == page_count) { //已经到最后一个版面了,如果再向后，必须跳转到第一个版面。
				$(this).addClass("live_slide_r_disabled");
			} else {
				$(this).removeClass("live_slide_r_disabled");
				$v_show.animate({left: '-=' + v_width}, "slow"); //通过改变left值，达到每次换一个版面
				page++;

				$(".live_back").show();
				$(".live_libg").animate({left: sli_w}, "fast");
				
				var curindex = (page - 1) * s_licont + 1;
				$v_show.find(".live_sli").eq(curindex).addClass("live_sli_selected").siblings().removeClass("live_sli_selected")
				$(".live_calendar_cont").find(".live_cont_li").eq(curindex).show().siblings().hide();
			}

		}


	});

	//返回今天
	$(".live_back").click(function() {
		page = 1;
		$v_show.animate({left: "0px"}, "slow");
		$(this).hide();
		$(".live_libg").animate({left: '0px'}, "fast");
		$v_show.find(".live_sli").eq(0).addClass("live_sli_selected").siblings().removeClass("live_sli_selected")
		$(".live_calendar_cont").find(".live_cont_li").eq(0).show().siblings().hide();
		$(".live_slide_l").addClass("live_slide_l_disabled");
	});

	//点击左侧
	$(".live_slide_l").click(function() {
		if (!$v_show.is(":animated")) { //判断“视频内容展示区域”是否正在处于动画
			if (page == 1) { //已经到第一个版面了,如果再向前，必须跳转到最后一个版面。
				$(this).addClass("live_slide_l_disabled");
			} else {
				$(this).removeClass("live_slide_l_disabled");
				$v_show.animate({
					left: '+=' + v_width
				}, "slow");
				page--;
				if (page == 1) {
					$(".live_back").hide();
					$(".live_libg").animate({left: '0'}, "fast");
					curindex = 0;
					$v_show.find(".live_sli").eq(0).addClass("live_sli_selected").siblings().removeClass("live_sli_selected");
					$(".live_calendar_cont").find(".live_cont_li").eq(curindex).show().siblings().hide();
				}else{
					$(".live_libg").animate({left: sli_w}, "fast");
					var curindex = (page - 1) * s_licont + 1;
					$v_show.find(".live_sli").eq(curindex).addClass("live_sli_selected").siblings().removeClass("live_sli_selected")
					$(".live_calendar_cont").find(".live_cont_li").eq(curindex).show().siblings().hide();
				}
			}
		}
	});

	//单个点击
	$(".live_sli").each(function(sli_index) {
		$(this).click(function() {
			$(this).addClass("live_sli_selected").siblings().removeClass("live_sli_selected");
			var curleft = sli_w * ((sli_index - 1) % s_licont + 1) + "px";
			$(".live_libg").animate({left: curleft}, "fast");
			$(".live_calendar_cont").find(".live_cont_li").eq(sli_index).show().siblings().hide();
		})

	});

 	// 选课中心
 	$(".offcn-courseTable table tr").each(function () {
        $(this).hover(function(){
            $(".offcn-courseTable table tr").removeClass("on");
            $(this).addClass("on");
            //$(".offcn-courseName").css("z-index","2");
            //$(".offcn-courseName span").css("display","none");
            //$(this).find(".offcn-courseName").css("z-index","5").children("span").css("display","block");
        },function(){
            $(".offcn-courseTable table tr").removeClass("on");
            //$(".offcn-courseName").css("z-index","2");
            //$(".offcn-courseName span").css("display","none");
        })
    });
    $(".offcn-courseHover").hover(function(){
        $(".offcn-courseName").css("z-index","2");
        $(".offcn-courseName span").css("display","none");
        $(this).find(".offcn-courseName").css("z-index","5").children("span").css("display","block");
    },function(){
        $(".offcn-courseName").css("z-index","2");
        $(".offcn-courseName span").css("display","none");
    })
    // 选课中心end

})