$(function() {

$('.zg19new_ctname').each(function(){
	if($(this).find('span').size()==0 ){
		$(this).addClass('zg19new_ctname1');
	}else if($(this).find('span').size()==1){
		if($(this).find('span').hasClass('zg19new_live')){
			$(this).addClass('zg19new_ctname2');
		}else if($(this).find('span').hasClass('offcn19_free') || $(this).find('span').hasClass('offcn19_soon')){
			$(this).addClass('zg19new_ctname3');
		}
	}else{
		$(this).removeClass('zg19new_ctname3 zg19new_ctname2 zg19new_ctname1');
	}
});


	//版权年份
	$('.zg17_footer_box .year').html(new Date().getFullYear());

	if(navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion.split(";")[1].replace(/[ ]/g, "").replace("MSIE",""))<9){
		$('.video_tipsIE').show();
		$('.offcn19_spplay').hide();
		alert("您的浏览器版本过低，为了更好的浏览体验，请下载IE9及以上版本！若您使用的是双核浏览器，请切换到非IE内核。");
	};

	//登录后
	$(".zg_personal").mouseenter(function() {
		$(this).addClass("zg_perHov");
		$(".zg_person_list").show();
	}).mouseleave(function() {
		$(".zg_person_list").hide();
		$(this).removeClass("zg_perHov");
	});



	$('.offcn19_coumore').on('click', function() {
		$(this).parent().css('overflow','visible');
		$(this).addClass('on').siblings().addClass('b_shadow');
	});
	$('.offcn19_couplebox').on('mouseleave', function() {
		$(this).css('overflow', 'hidden').find('a').removeClass('on').siblings().removeClass('b_shadow');
	});

	// 课程特色
	$('.offcn19_feature_more').on('click', function() {
		$(this).siblings('.offcn19_feature').hide().siblings('.offcn19_feature_all').show();
		$(this).hide();
	});
	$('.offcn19_feature_less').on('click', function() {
		$(this).parent('.offcn19_feature_all').hide().siblings().show();
	});
	$('.offcn19_feature_all').mouseleave(function() {
		$(this).hide().siblings().show();
	});


	//套餐
	$('.offcn19_cpgr').each(function() {
		var _this = $(this);
		if ( _this.find('li').size() > 3) {
	// 		jQuery(".offcn19_cpgr").slide({mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:3,pnLoop: false});
		} else {
			if(_this.find('li').size() == 3){
				_this.find('li').eq(2).css('marginRight',"0px")
			}
			_this.find('.prev,.next').hide();
		}
		_this.find('li').last().find('.offcn19_next_row').hide();
	});
	jQuery(".offcn19_cpgr").slide({mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:3,pnLoop: false});
	//套餐内课程
	$('.offcn19_pglist li').each(function(i) {
		if ($(this).index() % 3 == 2) {
			$(this).addClass('marr0');
		}
	});


	// 详情页导航
	var nav_top = 0;
	if ($('.offcn19_zqleft .offcn19_nav').length == 0) {
		nav_top = 0;
	} else {
		var nav_top = $('.offcn19_zqleft .offcn19_nav').offset().top;
	}

	$('.offcn19_szxx').tinyscrollbar();
	$('.offcn19_szxx').hide();

	$('.offcn19_zqleft .offcn19_anchor,.offcn19_xfbot .offcn19_anchor').on('click', function() {
		$('.offcn19_xfbot .offcn19_anchor').eq($(this).index()).addClass('on').siblings().removeClass('on');
		$('.offcn19_zqleft .offcn19_anchor').eq($(this).index()).addClass('on').siblings().removeClass('on');
		$('.offcn19_wrap').eq($(this).index()).show().siblings('.offcn19_wrap').hide();
		if($(window).scrollTop()>nav_top){
			$('html,body').animate({'scrollTop': nav_top + 1}, 1);
		}
	});

	//课程详情师资部分
	$('.offcn19_teachers li').each(function(i) {
		if (i % 4 == 3) {
			$('.offcn19_teachers li').eq(i).addClass('marr0');
		}
	});
	$('.offcn19_teachers li').on('mouseenter', function() {
		$(this).find('.offcn19_szxx').show()
		$(this).find('.offcn19_szxx').tinyscrollbar();
	});
	$('.offcn19_teachers li').on('mouseleave', function() {
		$(this).find('.offcn19_szxx').hide();
	});

	// 联系电话
	$('.offcn19_zxtel').on('click', function() {
		$('.mask,.consult_layer').show();
	});
	$('.consult_layer .tips span,.consult_layer .link a').on('click', function() {
		$('.mask,.consult_layer').hide();
	});

	// 顶部导航悬浮和课程类型悬浮
	var beforeScrollTop = $(window).scrollTop();
	window.addEventListener("scroll", function(event) {
	    event = event || window.event;
	    var afterScrollTop = $(window).scrollTop();
	    delta = afterScrollTop - beforeScrollTop;
	    beforeScrollTop = afterScrollTop;
		var nav_top = 0;
		if ($('.offcn19_zqleft .offcn19_nav').length == 0) {
			nav_top = 0;
		} else {
			var nav_top = $('.offcn19_zqleft .offcn19_nav').offset().top;
		}

		var win_top = $(this).scrollTop();
			if (nav_top < win_top) {
				$('.offcn19_xfbot').show();
					if (delta > 0 ) {
						$('.zg19new_typebox_wide').removeClass('offcn19_fixtab');
		            } else if(delta < 0) {
						$('.zg19new_typebox_wide').addClass('offcn19_fixtab');
		            }
			} else {
				$('.offcn19_xfbot').hide();
				$('.zg19new_typebox_wide').removeClass('offcn19_fixtab');
			}
	}, false);

//课程介绍字段高度对齐
$('.offcn19_kcjs li').each(function(i){
	var i = $(this).index();
	if($(this).index()%2==0){
		$(this).addClass('offcn19_kcjs1');
	}
})



	// 章 收起
	$(document).on("click", ".zg19new_chaptit .zg19new_zjnm", function() {
		var self = $(this);
		if (self.hasClass("on")) {
			self.parent('.zg19new_chaptit').next('.zg19new_chpcont').slideUp();
		} else {
			self.parent('.zg19new_chaptit').next('.zg19new_chpcont').slideDown();
		}
		$(this).toggleClass('on');
	});

	// 章--节 收起
	$(document).on("click", ".zg19new_sectit .zg19new_zjnm", function() {
		var self = $(this);
		if (self.hasClass("on")) {
			self.parent('.zg19new_sectit').next().slideUp();
		} else {
			self.parent('.zg19new_sectit').next().slideDown();
		}
		$(this).toggleClass('on');
	});

	// 课程类型筛选
	$('.zg19new_typebox a').on('click', function(i) {
		$(this).addClass('on').siblings().removeClass('on');
		
		//展开课表章节部分
		$('.zg19new_sectit').each(function() {
			if (!$(this).find('.zg19new_zjnm').hasClass('on')) {
				$(this).find('.zg19new_zjnm').addClass('on');
				$(this).next('.zg19new_class').slideDown();
			}
		});
		$('.zg19new_chaptit').each(function() {
			if (!$(this).find('.zg19new_zjnm').hasClass('on')) {
				$(this).find('.zg19new_zjnm').addClass('on');
				$(this).next('.zg19new_chpcont').slideDown();
			}
		});

		// 防止选完没有的选项之后 有内容显示不出来
		$('.zg19new_wrap').find('.zg19new_class li,.zg19new_sectit,.zg19new_chaptit,.zg19new_chapter').show();
		var i = ".zg19new_ct" + $(this).attr('class').toString().slice(12, 13);

		if (i == ".zg19new_ct0") {
			$('.zg19new_wrap').find('.zg19new_class li,.zg19new_sectit,.zg19new_chaptit,.zg19new_chapter').show();
		} else {
			$('.zg19new_wrap').find('.zg19new_class li').hide();
			$('.zg19new_wrap').find(i).show();
			$('.zg19new_class').each(function() {
				if ($(this).find('li:visible').size() == 0) {
					$(this).prev().hide();
				} else {
					$(this).prev().show();
				}
			});
			$('.zg19new_chapter').each(function() {
				if ($(this).find('li:visible').size() == 0) {
					$(this).hide();
				}
			});
		}
	});


	//评价
	$('.zg19_valuation').on('click', 'a', function() {
		$(this).addClass('on').siblings().removeClass('on');
	});


	/* 星星 */
	var str_num = '',
		score_arr=['非常差','差','一般','好','非常好'];
	$(".mBotL_Comment .zg_comstar span").click(function() {
		var $index = $(this).index();
		$(this).parent(".zg_comstar").attr("class", "zg_comstar");
		$(this).parent(".zg_comstar").addClass("zg_comstar" + ($index + 1) + "");
		var str_num = $index + 1;
		$("#content").attr("data-star", str_num);
		$('.value_result').html(score_arr[$index]);
	});


//banner今日直播
	if ($('.offcn19_showzrzb li').size() > 2) {
		jQuery(".offcn19_showzrzb").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"left",autoPlay:true,vis:2,pnLoop: false});
	} else if ($('.offcn19_showzrzb li').size() == 2) {
		$('.offcn19_showzrzb li').eq(1).addClass('marr0');
		$('.offcn19_showzrzb .next,.offcn19_showzrzb .prev').hide();
	} else if ($('.offcn19_showzrzb li').size() < 2) {
		$('.offcn19_showzrzb li').addClass('offcn19_jrzb_alone');
		$('.offcn19_showzrzb').addClass('offcn19_showzrzb_one');
		$('.offcn19_showzrzb .next,.offcn19_showzrzb .prev').hide();
	}
	$('.offcn19_xftop').click(function(){$("html,body").animate({scrollTop:"0px"},500);});

$('.areaClose').on('click',function(){
	$('.mask,.offcn19_sort').hide();
});

    $('.offcn19_nav li').last().css({'marginRight':'0px'});


   if($('.offcn19_kcjs li').size()==1){
        $('.offcn19_kcjs li').css({'width':'710px','marginRight':'0px'})
    }

});