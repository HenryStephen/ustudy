// JavaScript Document
$(function(){
    //浮窗
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
    
    jQuery(".teacherList").slide({ mainCell:".dlList", effect:"leftLoop",vis:5, autoPlay:true});
    jQuery(".booklist").slide({ mainCell:".dlList", effect:"leftLoop",vis:5, autoPlay:true});
    
    $(".teacherList dl").hover(function(){
        $(this).find("dd").show();
        $(this).animate({           
             top: "0px"
        },300)  
    },function(){
        $(this).find("dd").hide();
        $(this).animate({           
             top: "20px"
        },300)  
    });
    
    
})
$(document).ready(function() {
    jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
        $(tab_conbox).find(".tabc").hide();
        $(tabtit).find("li:first").addClass("on").show(); 
        $(tab_conbox).find(".tabc:first").show();
    
        $(tabtit).find("li").bind(shijian,function(){
          $(this).addClass("on").siblings("li").removeClass("on"); 
            var activeindex = $(tabtit).find("li").index(this);
            $(tab_conbox).children(".tabc").eq(activeindex).show().siblings(".tabc").hide();
            return false;
        });
    
    };
    /*调用方法如下：*/
    $.jqtab("#tabs01","#tab_conbox01","mouseenter");
    $.jqtab("#tabs02","#tab_conbox02","mouseenter");
    $.jqtab("#tabs03","#tab_conbox03","mouseenter");
    
    $.jqtab("#tabs04","#tab_conbox04","mouseenter");
    $.jqtab("#tabs05","#tab_conbox05","mouseenter");
    $.jqtab("#tabs06","#tab_conbox06","mouseenter");
    $.jqtab("#tabs07","#tab_conbox07","mouseenter");
    $.jqtab("#tabs08","#tab_conbox08","mouseenter");
    $.jqtab("#tabs09","#tab_conbox09","mouseenter");
});
$(document).ready(function() {
    jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
        $(tab_conbox).find(".tabbd").hide();
        $(tabtit).find("li:first").addClass("on").show(); 
        $(tab_conbox).find(".tabbd:first").show();
    
        $(tabtit).find("li").bind(shijian,function(){
          $(this).addClass("on").siblings("li").removeClass("on"); 
            var activeindex = $(tabtit).find("li").index(this);
            $(tab_conbox).children(".tabbd").eq(activeindex).show().siblings(".tabbd").hide();
            return false;
        });
    
    };
    /*调用方法如下：*/
    $.jqtab("#tabs1","#tab_conbox1","mouseenter");
    $.jqtab("#tabs2","#tab_conbox2","mouseenter");
    $.jqtab("#tabs3","#tab_conbox3","mouseenter");
    $.jqtab("#tabs4","#tab_conbox4","mouseenter");
});


$(function(){
	$(".e_nav .e_nd").each(function(d) {
        $(this).hover(function(){
            $(this).append("<em></em>");
        },function(){
            $(this).find("em").remove();
        });
    });
	$('#it_index_jpkc').hover(function(){
		$(this).find('.zg_jpkcx').slideDown();
	},function(){
		$(this).find('.zg_jpkcx').slideUp();
	});
});