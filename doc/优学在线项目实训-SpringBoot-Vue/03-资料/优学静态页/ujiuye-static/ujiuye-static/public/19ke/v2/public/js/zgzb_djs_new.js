// JavaScript Document
var activestoptime = '';
$(function(){
	var nowtime = $('#activedata').attr('data_time'); //当前时间
	
	djs(nowtime); //页面加载时立即执行一次,不然要等1秒,setInterval自身的缺陷
	setInterval(function(){
		nowtime = parseInt(nowtime)+1;
		//活动是有序排好的
		var data_type = $('#activedata').attr('data_type'); //活动类型
		var data_stat = $('#activedata').attr('data_stat'); //活动开始时间
		var data_end = $('#activedata').attr('data_end');   //活动结束时间
		var data_price = $('#activedata').attr('data_active_price');   //活动价
		var data_count = $('#activedata').attr('data_count');   //活动库存
		if(nowtime && data_type && data_stat && data_end && data_price){
			var data_types = data_stats = data_ends =  data_prices = new Array(); //定义一数组
			//切割数组
			data_types = data_type.split(",");  //类型
			data_stats = data_stat.split(",");  //开始时间
			data_ends = data_end.split(",");    //结束时间
			data_prices = data_price.split(",");  //活动价
			data_count = data_count.split(",");  //活动库存

			//验证长度
			if(data_types.length == data_stats.length && data_ends.length == data_prices.length && data_types.length==data_ends.length){
				//循环判断
				for(var i=0;i<data_types.length;i++){
					//活动中
					if(data_ends[i] >= nowtime && data_stats[i] <= nowtime){
						//页面变化
						lxfEndtime(nowtime,data_stats[i],data_ends[i],data_types[i],data_prices[i],data_count[i]);
						break;
					}
					//活动（可能）即将开始
					if(data_stats[i] >= nowtime){
						
						//页面变化
						lxfEndtime(nowtime,data_stats[i],data_ends[i],data_types[i],data_prices[i],data_count[i]);
						break;
					}
				}
				
			}
			
		}
	},1000);
})

//页面加载时立即执行一次,不然要等1秒,setInterval自身的缺陷
function djs(nowtime){
	//活动是有序排好的
	var data_type = $('#activedata').attr('data_type'); //活动类型
	var data_stat = $('#activedata').attr('data_stat'); //活动开始时间
	var data_end = $('#activedata').attr('data_end');   //活动结束时间
	var data_price = $('#activedata').attr('data_active_price');   //活动价
	var data_count = $('#activedata').attr('data_count');   //活动库存
	if(nowtime && data_type && data_stat && data_end && data_price){
		var data_types = data_stats = data_ends =  data_prices = new Array(); //定义一数组
		//切割数组
		data_types = data_type.split(",");  //类型
		data_stats = data_stat.split(",");  //开始时间
		data_ends = data_end.split(",");    //结束时间
		data_prices = data_price.split(",");  //活动价
		data_count = data_count.split(",");  //活动库存

		//验证长度
		if(data_types.length == data_stats.length && data_ends.length == data_prices.length && data_types.length==data_ends.length){
			//循环判断
			for(var i=0;i<data_types.length;i++){
				//活动中
				if(data_ends[i] >= nowtime && data_stats[i] <= nowtime){
					//页面变化
					lxfEndtime(nowtime,data_stats[i],data_ends[i],data_types[i],data_prices[i],data_count[i]);
					break;
				}
				//活动（可能）即将开始
				if(data_stats[i] >= nowtime){
					//页面变化
					lxfEndtime(nowtime,data_stats[i],data_ends[i],data_types[i],data_prices[i],data_count[i]);
					break;
				}
				
			}
		}
		
	}
}

function getZero(num){
	if(num.toString().length>1){ 
		num = num ; 
	}else{
		num =  '0' + num ;
	}
	return num;
}

/*
 * 页面变化
 */
function lxfEndtime(nowtime,starttime,endtime,type,price,count){
	var seconds = minutes = hours = day = 0;
	var seconds_start = (starttime-nowtime);//还有多久开始
	var seconds_left = (endtime-nowtime);//还有多久结束

	if(type == 1){
		$('.zgzb_price_show').html('秒杀价');
	}else{
		$('.zgzb_price_show').html('拼团价');
	}
	
	
	if( seconds_start>24*60*60 ||  seconds_left<=0 ){//活动开始前24h之前和结束后 正常价
		//console.log(0);
		$('.zgzb_mszt').hide(); //pc+wap
		$('.zgzb_price_show').html('价  格'); //pc
		$('.zb_jishao_price span i').hide(); //wap 秒杀价字样
		$('.zb_jishao_price span em').hide(); //wap 秒杀名额
		
		//价格
		var more = $('.zgmoreone').attr('more');
		var wapmore = $('.wapzgmoreone').attr('more');
		$('.offcn19_xfprice').html("<span>￥</span>"+more);//pc顶部价格
		$('.zgzb_price_show').hide(); //pc价格
		$('.zgmoreone').html("<i>￥</i>"+more); //pc价格
		$('.b1').show().siblings('.btn_assemble').hide();
		
		$('.offcn19_bmnum').show();
		$('.offcn19_pintuanf').hide();
		$('#renxing').hide();
		$('#woyaobaoming').show();

		
	}else if(seconds_start<=24*60*60 && seconds_start>=0 ){
		//console.log(1);
		//活动开始前24h内  正常价
		$('.zgzb_mszt').show().addClass('zgzb_mszt1 clearfix').removeClass('zgzb_mszt2 zgzb_mszt3'); //pc+wap
		
		seconds = getZero( parseInt(seconds_start%60)); 
		minutes = getZero( parseInt(seconds_start/60%60)); 
		hours = getZero( parseInt(seconds_start/60/60%24));
		day = getZero( parseInt(seconds_start/60/60/24));
		
		$('.zgzb_surplus').html('距离开始' + '<span class="zgzb_hours">'+hours+'</span>:<span class="zgzb_minutes">'+ minutes +'</span>:<span class="zgzb_seconds">'+ seconds +'</span>');
		$('.zgzb_price .zgzb_price_show').html('价  格');

		if(type == 1){
			$('.zgzb_mssd').html('限时秒杀');
		}
		if(type == 2){
			$('.zgzb_mssd').html('限时拼团');
		}
		//价格
		var more = $('.zgmoreone').attr('more');
		$('.offcn19_xfprice').html("<span>￥</span>"+more);//pc顶部价格
		$('.zgzb_price_show').hide(); //pc价格
		$('.zgmoreone').html("<i>￥</i>"+more); //pc价格
		$('.b1').show().siblings('.btn_assemble').hide();


		if(type == 1){
			$('#offcn19_bmnum').html('剩余<b>'+count+'</b>名额');//pc剩余名额
			$('.offcn19_bmnum').show();
			$('#offcn19_bmnum').show(); //pc剩余名额
			$('.offcn19_pintuanf').hide();//我要拼团 按钮 隐藏
			$('#renxing').hide();//任性原价购 按钮 隐藏
			$('#woyaobaoming').show();//我要报名 按钮 显示
		}
		if(type == 2){
			$('.offcn19_bmnum').hide(); //pc剩余名额
			$('.offcn19_pintuanf').show();//我要拼团 按钮 隐藏
			$('#renxing').show();//任性原价购 按钮 隐藏
			$('#woyaobaoming').hide();//我要报名 按钮 显示
		}
	
	}else if( seconds_start<0 && seconds_left>=6*60*60){
		//console.log(2);
		//活动开始至即将结束时段6h为界  活动价
		$('.zgzb_mszt').show().addClass('zgzb_mszt2 clearfix').removeClass('zgzb_mszt3 zgzb_mszt1'); //pc+wap
		
		seconds = getZero( parseInt(seconds_left%60)); 
		minutes = getZero( parseInt(seconds_left/60%60)); 
		hours = getZero( parseInt(seconds_left/60/60%24));
		day = parseInt(seconds_left/60/60/24);
		$('.zgzb_surplus').html('距离结束'  + '<em><span class="zgzb_day">'+day+'</span>天</em><span class="zgzb_hours">'+hours+'</span>:<span class="zgzb_minutes">'+ minutes +'</span>:<span class="zgzb_seconds">'+ seconds +'</span>');
		if( day==0 ){
			$('.zgzb_surplus em').hide();
		}
		$('.btn_assemble').show().siblings('.b1').hide();
		
		if(type == 1){
			$('.zgzb_mssd').html('限时秒杀');
		}
		if(type == 2){
			$('.zgzb_mssd').html('限时拼团');
		}

		//价格 
		var more = price;
		$('.offcn19_xfprice').html("<span>￥</span>"+more);//pc顶部价格
		$('.zgzb_price_show').show(); //pc价格
		$('.zgmoreone').html("<i>￥</i>"+more); //pc价格
		$('.b1').show().siblings('.btn_assemble').hide();
		$('.zb_jishao_price span').html("<i>秒杀价</i>￥"+more); //wap价格
		if(type == 1){
			$('.zb_footer_price').html("<i style='font-style:normal;font-size:.24rem;'>秒杀价</i>￥"+more); //wap价格
		}else{
			$('.zb_footer_price').html("￥"+more); //wap价格
		}

		if(type == 1){
			$('#offcn19_bmnum').html('剩余<b>'+count+'</b>名额');//pc剩余名额
			$('.offcn19_bmnum').show();
			$('#offcn19_bmnum').show(); //pc剩余名额
			$('.offcn19_pintuanf').hide();//我要拼团 按钮 隐藏
			$('#renxing').hide();//任性原价购 按钮 隐藏
			$('#woyaobaoming').show();//我要报名 按钮 显示
		}
		if(type == 2){
			$('#woyaobaoming').hide();//我要报名 按钮 
			$('.offcn19_bmnum').hide(); //pc剩余名额
			$('.offcn19_pintuanf').show();//我要拼团 按钮 显示
			$('#renxing').show();//任性原价购 按钮 显示
		}

	}else if(  seconds_left>0 && seconds_left < 6*60*60 ){
		//console.log(3);
		//活动结束前6小时开始结束倒计时  活动价
		
		seconds = getZero( parseInt(seconds_left%60)); 
		minutes = getZero( parseInt(seconds_left/60%60)); 
		hours = getZero( parseInt(seconds_left/60/60%24));
		$('.zgzb_mszt').show().addClass('zgzb_mszt3 clearfix').removeClass('zgzb_mszt2 zgzb_mszt1');
		$('.zgzb_surplus').html('即将结束' + '<span class="zgzb_hours">'+hours+'</span>: <span class="zgzb_minutes">'+ minutes +'</span>:<span class="zgzb_seconds">'+ seconds +'</span>');
		$('.btn_assemble').show().siblings('.b1').hide();
		
		if(type == 1){
			$('.zgzb_mssd').html('限时秒杀');
		}
		if(type == 2){
			$('.zgzb_mssd').html('限时拼团');
		}
		
		//价格 
		var more = price;
		$('.offcn19_xfprice').html("<span>￥</span>"+more);//pc顶部价格
		$('.zgzb_price_show').show(); //pc价格
		$('.zgmoreone').html("<i>￥</i>"+more); //pc价格
		$('.b1').show().siblings('.btn_assemble').hide();
		$('.zb_jishao_price span').html("<i>秒杀价</i>￥"+more); //wap价格
		if(type == 1){
			$('.zb_footer_price').html("<i style='font-style:normal;font-size:.24rem;'>秒杀价</i>￥"+more); //wap价格
		}else{
			$('.zb_footer_price').html("￥"+more); //wap价格
		}
		
		if(type == 1){
			$('#offcn19_bmnum').html('剩余<b>'+count+'</b>名额');//pc剩余名额
			$('.offcn19_bmnum').show();
			$('#offcn19_bmnum').show(); //pc剩余名额
			$('.offcn19_pintuanf').hide();//我要拼团 按钮 隐藏
			$('#renxing').hide();//任性原价购 按钮 隐藏
			$('#woyaobaoming').show();//我要报名 按钮 显示
		}
		if(type == 2){
			$('.offcn19_bmnum').hide(); //pc剩余名额
			$('.offcn19_pintuanf').show();//我要拼团 按钮 隐藏
			$('#renxing').show();//任性原价购 按钮 隐藏
			$('#woyaobaoming').hide();//我要报名 按钮 显示
		}

	}
}