function show_div(obj){
  for(i=1;i<=2;i++){
    document.getElementById("jjx"+i).className=""
	document.getElementById("jjx"+obj).className="span_hover"
	document.getElementById("jjxkc"+i).style.display="none"
	document.getElementById("jjxkc"+obj).style.display=""
  }
}
function show_wx(obj)
	{
			document.getElementById("wbxx"+obj).style.display="";
			document.getElementById("weixb"+obj).className="weibb";
		}
	function close_wx(obj)
	{
			document.getElementById("wbxx"+obj).style.display="none";
			document.getElementById("weixb"+obj).className="weibo";
		}
	
$(function(){
   $(".offcn_yxss_tab span").each(function(d) {
		$(this).hover(function(){
			$(".offcn_yxss_tab span").removeClass("hover");
			$(this).addClass("hover");
			$(".offcn_table_yx").hide();
			$(".offcn_table_yx:eq("+d+")").show();
		})
	});
	$("#kygkk a").each(function(x){
		$(this).mousemove(function(){
			$("#kygkk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#kygkk_box .kywx_box").hide();
			$("#kygkk_box .kywx_box:eq("+x+")").show();
			})
		})
	$("#jjxzyk a").each(function(x){
		$(this).mousemove(function(){
			$("#jjxzyk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#jjxzyk_box .kywx_box").hide();
			$("#jjxzyk_box .kywx_box:eq("+x+")").show();
			})
		}) 	 
	$("#glxzyk a").each(function(x){
		$(this).mousemove(function(){
			$("#glxzyk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#glxzyk_box .kywx_box").hide();
			$("#glxzyk_box .kywx_box:eq("+x+")").show();
			})
		}) 	 
	$("#xlxzyk a").each(function(x){
		$(this).mousemove(function(){
			$("#xlxzyk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#xlxzyk_box .kywx_box").hide();
			$("#xlxzyk_box .kywx_box:eq("+x+")").show();
			})
		}) 	 
	$("#fszyk a").each(function(x){
		$(this).mousemove(function(){
			$("#fszyk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#fszyk_box .kywx_box").hide();
			$("#fszyk_box .kywx_box:eq("+x+")").show();
			})
		}) 	 
	$("#fysszyk a").each(function(x){
		$(this).mousemove(function(){
			$("#fysszyk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#fysszyk_box .kywx_box").hide();
			$("#fysszyk_box .kywx_box:eq("+x+")").show();
			})
		}) 	 
	$("#qtzyk a").each(function(x){
		$(this).mousemove(function(){
			$("#qtzyk a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#qtzyk_box .kywx_box").hide();
			$("#qtzyk_box .kywx_box:eq("+x+")").show();
			})
		})
	$("#kyms a").each(function(x){
		$(this).mousemove(function(){
			$("#kyms a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#kyms_box .offcn_demo").hide();
			$("#kyms_box .offcn_demo:eq("+x+")").show();
			})
		})
	$("#kybook a").each(function(x){
		$(this).mousemove(function(){
			$("#kybook a").removeClass("kywx_dq");
			$(this).addClass("kywx_dq");
			$("#kybook_box .offcn_demo").hide();
			$("#kybook_box .offcn_demo:eq("+x+")").show();
			})
		})	
	$(".offcn_people li").each(function(x){
		$(this).mouseover(function(){
			$(".offcn_people li span:eq("+x+")").stop().animate({top:78},50);
		})
		$(this).mouseout(function(){
			$(".offcn_people li span:eq("+x+")").stop().animate({top:98},50);
		})
	})	
	$("#kcfl").mouseenter(function(){
		$("#aside").show();
		}).mouseleave(function(){
		$("#aside").hide();
		})
	$(".fw_con4_right table img").each(function(x) {
        $(this).mousemove(function(){
			$(".fw_con4_right table img").removeClass("pic");
			$(this).addClass("pic");
			$(".fw_con4_right .huat").hide();
			$(".fw_con4_right .huat:eq("+x+")").show();
			})
    });
	/*2015-03-10*/
	$("#offcn_ggk a").each(function(x){
		$("#offcn_ggk a").eq(0).addClass("kyhover")
		$("#offcn_ggk_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_ggk a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_ggk_right .offcn_tab").hide();
			$("#offcn_ggk_right .offcn_tab:eq("+x+")").show();
	   })
	})	
	
	$("#offcn_kygg_left a").each(function(x){
		$("#offcn_kygg_left a").eq(0).addClass("kyhover")
		$("#offcn_kyggk_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_kygg_left a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_kyggk_right .offcn_tab").hide();
			$("#offcn_kyggk_right .offcn_tab:eq("+x+")").show();
	   })
	})
	$("#offcn_zyss_left a").each(function(x){
		$("#offcn_zyss_left a").eq(0).addClass("kyhover")
		$("#offcn_zyss_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_zyss_left a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_zyss_right .offcn_tab").hide();
			$("#offcn_zyss_right .offcn_tab:eq("+x+")").show();
	   })
	})
	$("#offcn_zzky_left a").each(function(x){
		$("#offcn_zzky_left a").eq(0).addClass("kyhover")
		$("#offcn_zzky_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_zzky_left a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_zzky_right .offcn_tab").hide();
			$("#offcn_zzky_right .offcn_tab:eq("+x+")").show();
	   })
	})
	
	$("#offcn_tdxl_left a").each(function(x){
		$("#offcn_tdxl_left a").eq(0).addClass("kyhover")
		$("#offcn_tdxl_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_tdxl_left a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_tdxl_right .offcn_tab").hide();
			$("#offcn_tdxl_right .offcn_tab:eq("+x+")").show();
	   })
	})
	$("#offcn_lxxl_left a").each(function(x){
		$("#offcn_lxxl_left a").eq(0).addClass("kyhover")
		$("#offcn_lxxl_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_lxxl_left a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_lxxl_right .offcn_tab").hide();
			$("#offcn_lxxl_right .offcn_tab:eq("+x+")").show();
	   })
	})
	$("#offcn_lnzt_left a").each(function(x){
		$("#offcn_lnzt_left a").eq(0).addClass("kyhover")
		$("#offcn_lnzt_right .offcn_tab").eq(0).show();
		$(this).click(function(){
			$("#offcn_lnzt_left a").removeClass("kyhover");
			$(this).addClass("kyhover");
			$("#offcn_lnzt_right .offcn_tab").hide();
			$("#offcn_lnzt_right .offcn_tab:eq("+x+")").show();
	   })
	})
	$(".offcn_tab .offcn_ckgd").toggle(function(){
	  $(this).parent(".offcn_tab").addClass("offcn_tab_click")
	  $(this).siblings("div").addClass("div_click")
	  $(this).addClass("offcn_ckgd_hover");
	},function(){
	  $(".offcn_tab").removeClass("offcn_tab_click")
	  $(".offcn_tab div").removeClass("div_click")
	  $(".offcn_tab .offcn_ckgd").removeClass("offcn_ckgd_hover");
	})
	
	/*2015-03-10*/		
//	$("#demo1").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo2").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo3").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo4").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo5").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo6").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo7").bxCarousel({display_num: 6, move: 6,margin: 0 });	 	 
//	$("#demo8").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo9").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo10").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo11").bxCarousel({display_num: 6, move: 6,margin: 0 });
//	$("#demo12").bxCarousel({display_num: 6, move: 6,margin: 0 });	 
	 	
	//考研咨询框
	$(".oPubFixBtnClose").click(function(){
		$(".oPubFixBtnOpen").show();
		$(".oPubFixContact").hide();
	});
	$(".oPubFixBtnOpen").click(function(){
		$(".oPubFixContact").show();
		$(this).hide();
	});
	 					
})

