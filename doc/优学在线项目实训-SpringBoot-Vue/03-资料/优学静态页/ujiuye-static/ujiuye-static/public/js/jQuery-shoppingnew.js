 /*
  * Jquery—shopping   0.1
  * 实现加入购物车效果
 */
;(function($){
    $.extend($.fn,{
        shoping:function(){
            var self=this,
                $shop=$('.eTopcen'), //整个购物车div
                $num=$('#J-shoping-num') //购物车当前加入的个数
                // $num_hidden = $('#J-shoping-num_hidden') //购物车当前加入的个数 页面隐藏 0的时候用
            var S={
                init:function(){
                    //给相关元素绑定点击事件
                    $(self).data('click',true).live('click',this.addShopingToCart); //添加商品到购物车
                },
                //添加商品到购物车
                addShopingToCart:function(e){
                    // 统计
                    if (window._tag) {
                        _tag.dcsMultiTrack('wt.event','addcart','wt.tx_e','1');
                    }
                    // 统计end
                    e.stopPropagation(); //阻止冒泡
                    //x,y 当前商品的位置 X,Y购物车所在的位置
                    var $target=$(e.target),
                        id=$target.attr('id'),
                        dis=$target.data('click'),
                        x = $target.offset().left + 30,
                        y = $target.offset().top + 10,
                        X = $shop.offset().left+$shop.width()/2-$target.width()/2+45,
                        Y = $shop.offset().top;
                    if(id == 0){
                        $.qglobal.tip({content:"课程id为0,不能加入购物车", icon:'error' ,time:1000});
                        return false;
                    }
                    if(dis){
                        //检查该课程是否可以购买 
                        $.ajax({
                            type : 'POST',
                            cache : false,
                            dataType : 'JSON',
                            url  : '/shopcart/addToCart/',
                            data : {
                                courseId : id  //课程id
                            }, 
                            success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                                if(result.status == 1){
                                    if ($('#floatOrder').length <= 0) {
                                        $('body').append('<div id="floatOrder"><img src="/public/images/zg_pic04.jpg" width="50" height="50" /></div');
                                    };
                                    var $obj=$('#floatOrder');
                                    if(!$obj.is(':animated')){
                                        $obj.css({'left': x,'top': y}).animate({'left': X,'top': Y-80},1000,function() {
                                            $obj.stop(false, false).animate({'top': Y-20,'opacity':0},1000,function(){
                                                $obj.fadeOut(300,function(){
                                                    $obj.remove();  
                                                    var num=Number($num.html());
                                                    if(num == 0){
                                                        $num.html((num+1)).css("display","inline-block");;
                                                    }else{
                                                        if(num<=10){
                                                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                                                        };
                                                        $num.html((num+1));
                                                    }
                                                });
                                            });
                                        }); 
                                    };
                                }else if(result.status == 2){
                                    $.dialog({
                                        title:"购物车操作",
                                        content:result.msg,
                                        padding:'0px 0px',
                                        lock:true,
                                        ok:function(){
                                            window.location.href = '/study-'+id+'/';
                                        },
                                        cancel:function(){}
                                    });
                                }else{
                                    $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                                }
                            }
                        });
                    };
                }
            };
            S.init(); 
        },
        //报名操作
        directshoping:function(){
            
            var self=this;
            var SOPS={
                init:function(){
                    //给相关元素绑定点击事件
                    $(self).data('click',true).live('click',this.checkCartShoping); //报名
                },
                //报名
                checkCartShoping: function(){
                    // 统计
                    if (window._tag) {
                        _tag.dcsMultiTrack('wt.event','purchase');
                    }
                    // 统计end
                    var courseId = $(this).attr("id");
                    if(courseId == 0){
                        $.qglobal.tip({content:"课程id为0,不能报名", icon:'error' ,time:3000});
                        return false;
                    }
					var mobile = $(this).attr("data-mobile");
					var name = $(this).attr("data-name");
					var remark = $(this).attr("data-remark");
					if(mobile){
						var data2 = {
							sid:'7fe5727f9e4da5d576e090ca5183a859',
							mobile:mobile, 
							name:name, 
							province: '', 
							city: '', 
							remark:remark
						};
						$.ajax({
							type : 'GET',
							dataType : 'JSON',
							url  : 'https://dc.offcn.com:8443/a.gif',
							data : data2, 
							async :false,
							success:function(result){
							}
						})
					}
                    $.ajax({
                        type : 'POST',
                        cache : false,
                        dataType : 'JSON',
                        url  : '/shopcart/checkcartshoping/',
                        data : {
                            courseId : courseId
                        }, //form表单序列化
                        success:function(result){ //用ajax方法发送信息到当前Action中的main方法
							var area = '';
                            if ((typeof area_code != 'undefined') && (area_code != '')) {
                                area = 'area/' + area_code + '/';
                            }
                            if(result.status == 1){
                                var isIe=(document.all)?true:false;
                                if(isIe) {
                                    var linka = document.createElement('a');
                                    linka.href = '/shopcart/checkout/ids/'+courseId +'/'+ area;
                                    document.body.appendChild(linka);
                                    linka.click();
                                }else{
                                    window.location.href = '/shopcart/checkout/ids/'+courseId +'/'+ area;
                                }
                            }else if(result.status == 2){
                                $.dialog({
                                    title:"报名操作",
                                    content:result.msg,
                                    padding:'0px 0px',
                                    lock:true,
                                    ok:function(){
										//跳转到学习页面
                                        window.location.href = '/study-'+courseId+'/';
                                    },
                                    cancel:function(){}
                                });
                            }else if(result.status == 3){
                                $.qglobal.tip({content:result.msg, icon:'error' ,time:2000,url:'/foreuser/login/'});
                            }else{
                                $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                            }
                        }
                    });
                },
                //post传值到相应的地址
                post:function (URL, PARAMS) {        
                    var temp = document.createElement("form");        
                    temp.action = URL;        
                    temp.method = "post";        
                    temp.style.display = "none";        
                    for (var x in PARAMS) {        
                        var opt = document.createElement("input");        
                        opt.name = x;        
                        opt.value = PARAMS[x];    
                        temp.appendChild(opt);        
                    }
                    document.body.appendChild(temp);        
                    temp.submit();        
                    return temp;        
                }
            };
            SOPS.init(); 
        },
        //购物车操作
        shopingoperate:function(){
            var self=this;
            var SOP={
                init:function(){
                    //给相关元素绑定点击事件
                    $(self).data('click',true).live('click',this.delOneCartShop); //购物车单个课程删除 
                    $("#J_del_selected_cart_shop").data('click',true).live('click',this.delSelectCartShop); //购物车多个课程删除 
                    $("#J_confrim_selected_cart_shop").data('click',true).live('click',this.confrimSelectedCartShop); //结算
                },
                //单个删除购物车
                delOneCartShop: function(){
                    var recId = $(this).attr("data-id");
                    if(recId == 0){
                        $.qglobal.tip({content:"id为0", icon:'error' ,time:3000});
                        return false;
                    }
                    $.dialog({
                        title:"购物车删除操作",
                        content:"确定要从购物车删除该课程吗？",
                        padding:'0px 0px',
                        lock:true,
                        ok:function(){
                            $.ajax({
                                type : 'POST',
                                cache : false,
                                dataType : 'JSON',
                                url  : '/shopcart/delonecartshop/',
                                data : {
                                    recId : recId
                                }, //form表单序列化
                                success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                                    if(result.status == 1){
                                        if(result.data >= 0){
                                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                                            if(Number(result.dialog) == 0){
                                                window.location.reload();
                                            }else{
                                                //删除多个id相同额
                                                $("tr[id='cart_shop_"+recId+"']").remove();
                                                $("#J-shoping-num").html(result.dialog);
                                                $("#cart_total").html(result.dialog);
                                                var old_cart_total_price = $("#old_cart_total_price").val();
                                                var cha = Number(Number(old_cart_total_price) - Number(result.data));
												$("#old_cart_total_price").val(cha.toFixed(2));
                                                $("#cart_total_price").html('<font class="FontFA">¥</font>'+cha.toFixed(2));
                                                if(undefined == $('.J_checkall').attr('checked')){
                                                    $('.J_checkall').attr('checked', true);
                                                    $('.J_checkitem').attr('checked', true);
                                                }
                                            }
                                        }else{
                                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                                        }
                                    }else{
                                        $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                                    }
                                }
                            });
                        },
                        cancel:function(){}
                    });
                },
                //购物车删除多个课程
                delSelectCartShop: function(){
                    if($('.J_checkitem:checked').length == 0){
                        $.qglobal.tip({content:'请先选择后再操作', icon:'alert'});
                        return false;
                    }
                    var ids = '';
                    $('.J_checkitem:checked').each(function(){
                            ids += $(this).val() + ',';
                    });
                    ids = ids.substr(0, (ids.length - 1));
                    if(ids == ''){
                        $.qglobal.tip({content:'选择后数据不存在', icon:'alert'});
                        return false;
                    }
                    $.dialog({
                        title:"购物车批量删除操作",
                        content:"确定要从购物车批量删除这些课程吗？",
                        padding:'0px 0px',
                        lock:true,
                        ok:function(){
                            $.ajax({
                                type : 'GET',
                                cache : false,
                                dataType : 'JSON',
                                url  : '/shopcart/delselectcartshop/recId/'+ids+'/',
                                success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                                    if(result.status == 1){
                                        $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                                        window.location.reload();
                                    }else{
                                        $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                                    }
                                }
                            });
                        },
                        cancel:function(){}
                    });
                },
                //结算
                confrimSelectedCartShop: function(){
                    var btn = this;
                    if($('.J_checkitem:checked').length == 0){
                        $.qglobal.tip({content:'请先选择后再操作', icon:'alert'});
                        return false;
                    }
                    var ids = '';
                    $('.J_checkitem:checked').each(function(){
                            ids += $(this).val() + ',';
                    });
                    ids = ids.substr(0, (ids.length - 1));
                    if(ids == ''){
                        $.qglobal.tip({content:'选择后数据不存在', icon:'alert'});
                        return false;
                    }
					
                    var param = {ids :ids};
					if ((typeof area_code != 'undefined') && (area_code != '')) {
                        param.area = area_code;
                    }
					
					SOP.post('/shopcart/checkout/',param);
                },
                //post传值到相应的地址
                post:function (URL, PARAMS) {        
                    var temp = document.createElement("form");        
                    temp.action = URL;        
                    temp.method = "post";        
                    temp.style.display = "none";        
                    for (var x in PARAMS) {        
                        var opt = document.createElement("input");        
                        opt.name = x;        
                        opt.value = PARAMS[x];    
                        temp.appendChild(opt);        
                    }
                    document.body.appendChild(temp);        
                    temp.submit();        
                    return temp;        
                }
            };
            SOP.init(); 
        },
        //结算操作
        checkoutoperate:function(){
            var self=this;
            var CKOP={
                init:function(){
                    //给相关元素绑定点击事件
                    $(self).data('click',true).live('click',this.confrimUserInfo); //结算中确认用户信息
                    $("#J_submit_orders").data('click',true).live('click',this.confrimSubmitOrders); //结算
                    $("#J_submit_orders_zero").data('click',true).live('click',this.confrimSubmitOrdersZero); //结算是0元的
                },
                //结算中确认用户信息
                confrimUserInfo: function(){
                    var realname = $("#realname").val();
                    var phone = $("#phone").val();
                    if(realname == ""){
                        $.qglobal.tip({content:"姓名为空", icon:'error' ,time:3000});
                        return false;
                    }
                    if(phone == ""){
                        $.qglobal.tip({content:"手机号为空", icon:'error' ,time:3000});
                        return false;
                    }
                },
                //结算
                confrimSubmitOrders: function(){
                    var pay_type = $("#pay_type").val();
                    var direct_trade_create_req = $("#direct_trade_create_req").val();
                    var courseId = 0;
                    var youhui = $("#youhui").val();
                    var coupon_code = $('#coupon_code').val();
                    var nouser_phone = $('input[name=nouser_phone]').val();
					var area_code = $('#area_code').val();
                    if(direct_trade_create_req == 0){
                        $.qglobal.tip({content:"来源参数错误", icon:'error' ,time:1000});
                        return false;
                    }
                    
                    if(pay_type == 0){
                        $.qglobal.tip({content:"请先选择支付方式", icon:'error' ,time:1000});
                        return false;
                    }
                    courseId = $("#courseId").val();
                    if(direct_trade_create_req == 1){ //购买
                        if(courseId == 0){
                            $.qglobal.tip({content:"课程id为0", icon:'error' ,time:1000});
                            return false;
                        }
                    }else if(direct_trade_create_req == 2){
                        if(courseId == ''){
                            $.qglobal.tip({content:"课程id为0", icon:'error' ,time:1000});
                            return false;
                        }
                    }
                    if(nouser_phone.length==0 || nouser_phone.length!=11){
                        $.qglobal.tip({content:"手机号格式错误", icon:'error' ,time:1000});
                        return false;
                    }
                    var myreg = /^(((1[0-9]{1}))+\d{9})$/; 
                    if(!myreg.test(nouser_phone)){ 
                        $.qglobal.tip({content:"请输入正确的手机号码", icon:'error' ,time:1000});
                        return false;
                    }
                    $.dialog({
                        title:"订单提交操作",
                        content:"确定要提交订单吗？",
                        padding:'0px 0px',
                        lock:true,
                        ok:function(){
							var data = {
                                pay_type : pay_type,
                                direct_trade_create_req : direct_trade_create_req,
                                courseId : courseId,
                                phone : nouser_phone,
                                youhui : youhui,
                                coupon_code : coupon_code
                            };

                            if ((typeof area_code != 'undefined') && (area_code != '')) {
                                data.area = area_code;
                            }
                            $.ajax({
                                type : 'POST',
                                cache : false,
                                dataType : 'JSON',
                                url  : '/shopcart/submitorders/',
                                data : data, //form表单序列化
                                success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                                    if(result.status == 1){
                                        $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                                        var param = {order_id :result.data,direct_trade_create_req:direct_trade_create_req};
                                        CKOP.post('/shopcart/done/',param);   
                                    }else if(result.status == 2){
                                        $.qglobal.tip({content:result.msg, icon:'error' ,time:2000,url:'/foreuser_web/login/'});
                                    }else if(result.status == 3){
                                        $.qglobal.tip({content:result.msg, icon:'error' ,time:2000,url:'/shopcart/cart_to_shop/'});
                                    }else{
                                        if(result.status == 0){
                                            $.qglobal.tip({content:result.msg, icon:'error' ,time:3000,url:'/list/'});
                                        }else{
                                            $.qglobal.tip({content:result.msg, icon:'error' ,time:3000});
                                        }
                                    }
                                }
                            });
                        },
                        cancel:function(){}
                    });  
                },
                //结算
                confrimSubmitOrdersZero: function(){
                    var courseId = 0;
                    var lessonId = 0;
                    var ctype = 0;
                    courseId = $("#J_submit_orders_zero").attr("data-cid");
                    lessonId = $(this).attr("data-lid");
                    ctype = $(this).attr("data-ctp");
                    if(courseId == 0){
                        $.qglobal.tip({content:"课程id为0", icon:'error' ,time:1000});
                        return false;
                    }
					var data = {
                        courseId : courseId
                    };
                    if ((typeof area_code != 'undefined') && (area_code != '')) {

                        data.area = area_code;
                    }
					var mobile = $(this).attr("data-mobile");
					var name = $(this).attr("data-name");
					var remark = $(this).attr("data-remark");
					if(mobile){
						var data2 = {
							sid:'7fe5727f9e4da5d576e090ca5183a859',
							mobile:mobile,
							name:name, 
							province: '', 
							city: '', 
							remark:remark
						};
						
						$.ajax({
							type : 'GET',
							dataType : 'JSON',
							url  : 'https://dc.offcn.com:8443/a.gif',
							data : data2, 
							async :false,
							success:function(result){
							}
						})
					}
                    $.ajax({
                        type : 'POST',
                        cache : false,
                        dataType : 'JSON',
                        url  : '/shopcart/submitorderszero/',
                        data : data, //form表单序列化
                        success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                            if(result.status == 1){
                                if(result.dialog == 1){ //套餐跳转到个人中心我的课程
                                    $.qglobal.tip({content:result.msg, icon:'success' ,time:3000,url:'/mycourse/index/'});
                                }else{ //普通课程跳转到我的学习页面
									if(ctype == 2){
										$.qglobal.tip({content:"报名成功，正在跳转...", icon:'success' ,time:1000,url:'/video-'+lessonId+"/?cid="+result.data+"&ctype="+ctype});
									}else{
                                        var exam_type = $("#J_submit_orders_zero").attr("data-exam_type");
                                        if(exam_type == 3){
                                            var rdata = result.data;
                                                    var str = '';
                                            var pic = '';
                                            if(rdata.pay_pic){
                                                pic += '<div><img src="';
                                                pic += rdata.pay_pic;
                                                pic += '" alt="';
                                                pic += rdata.pay_pic_comment;
                                                pic += '"/></div><p>';
                                                pic += rdata.pay_pic_comment;
                                                pic += '</p>';
                                                $(".zg_czfcgdivfr").html(pic);
                                            }
                                                    if(rdata){
                                                        str = '<dl class="clearfix"><dt>订 单 号：</dt><dd>';
                                                        str += rdata.order_code;
                                                        str += '</dd></dl><dl class="clearfix"><dt>付款时间：</dt><dd>';
                                                        str += rdata.pay_time;
                                                        str += '</dd></dl><dl class="clearfix"><dt>订单金额：</dt><dd>';
                                                        str += rdata.pay_fee;
                                                        str += '元</dd></dl><dl class="clearfix">';
                                                        str += '<dt>课程清单：</dt><dd><p>';
                                                        str += rdata.course_name;
                                                        str += '</p></dd></dl>';
                                                        $(".zg_czfcgdivfl").html(str);
                                                        $(".zg_czfcg2").show();
                                                    }
                                            return false;
                                        }
										$.qglobal.tip({content:result.msg, icon:'success' ,time:3000,url:'/study-'+result.data+"/"});
									}
                                }
                            }else if(result.status == 2){
								if($('.login_button').html()){
									$('.login_button').trigger('click');
                                }else{ 
									$.qglobal.tip({content:result.msg, icon:'error' ,time:2000,url:'/foreuser/login/isfree/1/'});
								}
                            }else if(result.status == 3){
                                $.dialog({
                                    title:"加入学习操作",
                                    content:result.msg,
                                    padding:'0px 0px',
                                    lock:true,
                                    ok:function(){
                                        window.location.href = '/study-'+courseId+'/';
                                    },
                                    cancel:function(){}
                                });
                            }else{
                                $.qglobal.tip({content:result.msg, icon:'error' ,time:3000});
                            }
                        }
                    });
                },
                //post传值到相应的地址
                post:function (URL, PARAMS) {        
                    var temp = document.createElement("form");        
                    temp.action = URL;        
                    temp.method = "post";        
                    temp.style.display = "none";        
                    for (var x in PARAMS) {        
                        var opt = document.createElement("input");        
                        opt.name = x;        
                        opt.value = PARAMS[x];    
                        temp.appendChild(opt);        
                    }
                    document.body.appendChild(temp);        
                    temp.submit();        
                    return temp;        
                }
            };
            CKOP.init(); 
        }
    }); 
})(jQuery);
