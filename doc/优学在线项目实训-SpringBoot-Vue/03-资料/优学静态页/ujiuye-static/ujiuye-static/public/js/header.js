// JavaScript Document
// 头部特效和登录检测
$(function(){
    //搜索
    $(".formInp").show();
    $(".eSub02").hide();
    $(".eSub").hover(function(event){
        event.stopPropagation();
        $(this).parents().removeClass("eSecher1");
        //$(this).prev().show();
        $(".eTxt").stop().animate({width:"107px"},400);
        $(".eSecher").stop().animate({width:"144px"},400);
        setTimeout(function(){
            $(".eTxt").focus();
        },400);
    });
    
    $('.eSub').click(function(){
        if($(".eTxt").is(":visible")){
           if($(".eTxt").val().length<=0){
                $(".eTxt").focus();
                return false;
          }
        }
    })

    $(document).click(function(event){ 
        if($(".eTxt").is(":visible")){
            if($(".eTxt").val().length<=0){
                var target=$(event.target)
                if(target.closest(".eSecher").length==0){
                    $(".eSecher").addClass("eSecher1");
                    $(".eSecher").stop().animate({width:"30px"},400);
                    $(".eTxt").stop().animate({width:"0"},400);
                }
                
            }
        }                                     
    });
    
    $(".e_nav span").each(function(d) {
        $(this).hover(function(){
            $(this).append("<em></em>");
        },function(){
            $(this).find("em").remove();
        });
    });

    //登录
    $("#eUserpic").mouseover(function(){
      $(".eUserBox").css('display','block');
    });
    $(".eUserBox").mouseleave(function(){
      $(".eUserBox").css('display','none');
    });
	$(".zg_index_sys").mouseover(function(){
      $(".eUserBox").css('display','none');
    });
    var login_flag = false;
    // $(function(){
	// 	$.get("/foreuser/checklogin/", function(data){
	// 		obj = eval('('+data+')');
	// 		if(obj.status == 1){
	// 			$("#eUserpic").css('display', 'block');
	// 			$(".eLogin").css('display', 'none');
    //             $('.yikao_index_outlogin').css('display','block');//医考首页
    //             $('#yikao_index_login').css('display','none');//医考首页
	// 			$('.user_phone').html(obj.info['phone']);//考研
    //             $('#yikao_userinfo').html(obj.info['nickname']);
	// 			login_flag = true;
	// 			if(obj.shopcartnum > 0){
	// 				$("#J-shoping-num").css('display', '');
	// 				$("#J-shoping-num").html(obj.shopcartnum);
	// 			}
	// 			if(obj.headpic){
	// 				$("#eUserpic img").attr('src',obj.headpic);
    //                 $("#yikao_index_headpic").attr('src',obj.headpic);//医考首页
    //
	// 			}
	// 		}else{
	// 			if(obj.shopcartnum > 0){
	// 				$("#J-shoping-num").css('display', '');
	// 				$("#J-shoping-num").html(obj.shopcartnum);
	// 			}
	// 			if(obj.exam_type == 5){
	// 				(function() {var _53code = document.createElement("script");_53code.src = "//tb.53kf.com/code/code/10155271/1";var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(_53code, s);})();
	// 			}
    //             $('.yikao_index_outlogin').css('display','none');//医考首页
    //             $('#yikao_index_login').css('display','block');//医考首页
    //         }
	// 	});
    //
    // });
    //右下工具浮窗（微信微博二维码）
    $(".offcn-jump li").each(function (x) {
        $(this).hover(function () {
            $(this).find(".offcn-ibox").show();
        },function(){
            $(this).find(".offcn-ibox").hide();
        });
    });
    /*top*/

    $(window).scroll(function(){
        if($(window).scrollTop() >400){
            $('.offcn-itop').fadeIn(200);
        }else{
            $('.offcn-itop').fadeOut(200);
        }
    });
    $('.offcn-itop').click(function(){
        jQuery('body,html').animate({scrollTop:0},300);
        return false;
    });
    /*input-focus*/
    $('.soInpt').each(function(){  

        var txt = $(this).val();  

        $(this).focus(function(){  

        if(txt === $(this).val()) $(this).val("");  

        }).blur(function(){  

        if($(this).val() == "") $(this).val(txt);  

        });  
    }) 
})
