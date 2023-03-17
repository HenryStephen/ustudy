/**
 * @name 前台UI&TOOLS
 * @author wjh
 */
;(function($){
    $.qglobal.init = function(){
        $.qglobal.ui.init();
    }
    $.qglobal.ui = {
        init: function() {
            $.qglobal.ui.return_top(); //返回顶部
            $.qglobal.ui.meterialsdownload();//资料下载
            $.qglobal.ui.faq_question();//答疑提问
            $.qglobal.ui.faq_answer();//答疑回复
            $.qglobal.ui.appraisaldopost();//发表评论
            $.qglobal.ui.getMoreAppraisal();//加载更多评论
            //$.qglobal.ui.getAppraisalForCourse();//课程详情页面评论
            $.qglobal.ui.getMoreFaq();//加载更多答疑
            $.qglobal.ui.getMoreMaterials();//加载更多资料
        },
        //返回顶部
        return_top: function() {
            $('#J_returntop')[0] && $('#J_returntop').returntop();
        },
        //资料下载        
        meterialsdownload: function(){
            $('#J_meterials_download').live('click',function(){
                var id = $(this).attr('data-mid');  
                if(id == 0){
                    $.qglobal.tip({content:"资料id不存在", icon:'error' ,time:3000});
                    return false;
                }
                window.location.href = SLPGER.root + 'materials/downloads/id/'+id;
            });
        },
        //答疑回复
        faq_answer: function(){
            $('#J_faq_answers').live('click',function(){
                var id = $(this).attr('data-id'); 
                var questions = $("#J_faq_answers_"+id).val();  
                var courseId = $(this).attr('data-courseId'); 
                var lessonId = $(this).attr('data-lessonId'); 
                var isfrom = $(this).attr('data-from');
                if(id == 0){
                    $.qglobal.tip({content:"提问id不存在", icon:'error' ,time:3000});
                    return false;
                }
                if(courseId == 0){
                    $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(lessonId == 0){
                    $.qglobal.tip({content:"课件id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(questions == ""){
                    $.qglobal.tip({content:"请填写回复内容", icon:'error' ,time:3000});
                    $("#J_faq_answers_"+id).val("");
                    $("#J_faq_answers_"+id).focus();
                    return false;
                }
                $.ajax({
                    type : 'POST',
                    cache : false,
                    dataType : 'JSON',
                    url  : SLPGER.root + 'faq/doAddAnswers',
                    data : {
                        id : id,
                        questions : questions,
                        courseId : courseId,
                        lessonId : lessonId
                    }, //form表单序列化
                    success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                        if(result.status == 1){
                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                            var anshtml = "";
                            if(isfrom == 1){
                                anshtml ="<dl class='offcn1212-answers clearfix'><dt><em>&nbsp;</em><span><img src='"+result.data.avatar+"'></span></dt><dd  class='offcn1212-answer-dd'><font>"+ result.data.realname + "</font>&nbsp;<em>"+ result.data.add_time +"</em><p>"+ result.data.questions +"</p></dd></dl>";
                            }else{
                                anshtml ="<dl class='clearfix'><dt><em>&nbsp;</em><span><img src='"+result.data.avatar+"'></span></dt><dd><font>"+ result.data.realname + "</font>&nbsp;<em>"+ result.data.add_time +"</em><p>"+ result.data.questions +"</p></dd></dl>";
                            }
                            $("#answers_"+id).prepend(anshtml);
                        }else if(result.status == 2){
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:3000,url:SLPGER.root + 'foreuser/login'});
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                        $("#J_faq_answers_"+id).val("");
                        $("#clickanswerinput").show();
                        if(isfrom == 1){
                            $(".wordCount").hide();
                        }else{
                           $("#wordCount").hide(); 
                        }
                    }
                });
            });
        },
        //课程播放页面答疑提问      
        faq_question: function(){
            $('#J_faq_quesitons').live('click',function(){
                var courseId = $(this).attr('data-courseId'); 
                var lessonId = $(this).attr('data-lessonId'); 
                var questions = $("#J_faq_quesitons_"+courseId+"_"+lessonId).val();  
                if(courseId == 0){
                    $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(lessonId == 0){
                    $.qglobal.tip({content:"课件id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(questions == ""){
                    $.qglobal.tip({content:"请填写提问内容", icon:'error' ,time:3000});
                    $("#J_faq_quesitons_"+courseId+"_"+lessonId).val("");
                    $("#J_faq_quesitons_"+courseId+"_"+lessonId).focus();
                    return false;
                }
                $.ajax({
                    type : 'POST',
                    cache : false,
                    dataType : 'JSON',
                    url  : SLPGER.root + 'faq/doAddQuestions',
                    data : {
                        courseId : courseId,
                        lessonId : lessonId,
                        questions : questions
                    }, //form表单序列化
                    success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                        if(result.status == 1){
                            if(result.data){
                                var faqhtml = "";
                                $(result.data).each(function(){
                                    faqhtml += "<ul><li>" + this.questions + "<span> " + this.username + "（" + this.add_time + "）</span></li></ul>";
                                })
                                $("#dayi_2").prepend(faqhtml);
                            }
                        }else if(result.status == 2){
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:2000,url:SLPGER.root + 'foreuser/login'});
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                    }
                });
            });
        },
        //发表评论
        appraisaldopost: function(){
            $('#J_appraisal').live('click',function(){
                var content = $("#J_appraisal_content").val();  
                var courseId = $("#J_get_more_appraisal").attr("data-cid");
                if(courseId == 0){
                    $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(content == ""){
                    $.qglobal.tip({content:"请填写评价内容", icon:'error' ,time:3000});
                    $("#J_appraisal_content").val("");
                    $("#J_appraisal_content").focus();
                    return false;
                }
                $.ajax({
                    type : 'POST',
                    cache : false,
                    dataType : 'JSON',
                    url  : SLPGER.root + 'appraisal/doPost',
                    data : {
                        courseId : courseId ,
                        content : content
                    }, //form表单序列化
                    success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                        if(result.status == 1){
                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                            var anshtml = "";
                            anshtml += "<dl><dt><img src='" + result.data.largeavatar + "'><em></em></dt><dd><span>" + result.data.realname + "</span>" + result.data.add_time + "</dd><dd>" + result.data.content + "</dd></dl>";
                            var atotal = $("#atotal").html();
                            $("#atotal").html(Number(atotal)+1);
                            $("#atotal2").html(Number(atotal)+1);
                            $("#apparaisal_area").prepend(anshtml);
                            $("#J_appraisal_content").val("");
                            var wordCount = $("#wordCount"),
                            word = wordCount.find(".word");
                            word.text(200);
                        }else if(result.status == 2){
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:2000,url:SLPGER.root + 'foreuser/login'});
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                    }
                });
            });
        },
        //加载更多评论
        getMoreAppraisal: function(){
            $('#J_get_more_appraisal').live('click',function(){
                var last = $("#last").val();  
                var amount = 5;  //每次加载5条
                var courseId = $(this).attr("data-cid");
                if(courseId == 0){
                    $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                    return false;
                }
                $.ajax({
                    type : 'POST',
                    cache : false,
                    dataType : 'JSON',
                    url  : SLPGER.root + 'appraisal/index',
                    data : {
                        courseId : courseId ,
                        last   : last,
                        amount : amount
                    }, //form表单序列化
                    success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                        if(result.status == 1){
                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                            $("#last").val((last++)+1); //赋值分页数据
                            if(result.data){
                                var anshtml = "";
                                $(result.data).each(function(){
                                    anshtml += "<dl><dt><img src='" + this.largeavatar + "'><em></em></dt><dd><span>" + this.realname + "</span>" + this.add_time + "</dd><dd>" + this.content + "</dd></dl>";
                                })
                                $("#apparaisal_area").append(anshtml);
                            }else{
                                $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                            }
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                    }
                });
            });
        },
        //课程播放页面加载更多答疑
        getMoreFaq: function(){
            $('#J_get_more_faq').live('click',function(){
                var last = $("#last_faq").val();  
                var amount = 3;  //每次加载5条
                var courseId = $("#courseId").val();
                var lessonId = $("#lessonId").val();
                if(courseId == 0){
                    $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(lessonId == 0){
                    $.qglobal.tip({content:"课件id为0", icon:'error' ,time:3000});
                    return false;
                }
                $.ajax({
                    type : 'POST',
                    cache : false,
                    dataType : 'JSON',
                    url  : SLPGER.root + 'faq/getCourseMyFaqList',
                    data : {
                        courseId : courseId ,
                        lessonId : lessonId ,
                        last   : last,
                        amount : amount
                    }, //form表单序列化
                    success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                        if(result.status == 1){
                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                            $("#last_faq").val((last++)+1); //赋值分页数据
                            if(result.data){
                                var faqhtml = "";
                                $(result.data).each(function(){
                                    faqhtml += "<ul><li>" + this.questions + "<span> " + this.username + "（" + this.add_time + "）</span></li>";
                                    if(this.answers){
                                        $(this.answers).each(function(){
                                            faqhtml += "<li>" + this.questions + "<span> " + this.username + "（" + this.add_time + "）</span></li>";
                                        })
                                    }
                                    faqhtml += "</ul>";
                                })
                                $("#dayi_2").append(faqhtml);
                            }else{
                                $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                            }
                            //添加滚动条
                            var $mulvheight=$('.scrollBox').height();
                            if($mulvheight < $(window).height()- $('.offcnkcbfmain_right_1').height()){
                                $('.scrollBox').css('overflow-y','scroll');
                                $('.scrollBox').css('overflow-x','hidden');
                                $('.scrollBox').css('height',$(window).height()- $('.offcnkcbfmain_right_1').height() - 50);
                            }else{
                                $('.scrollBox').css('overflow-y','hidden')
                            }
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                    }
                });
            });
        },
        //课程播放页面加载更多资料
        getMoreMaterials: function(){
            $('#J_get_more_materials').live('click',function(){
                var last = $("#last_materials").val();  
                var amount = 4;  //每次加载5条
                var courseId = $("#courseId").val();
                var lessonId = $("#lessonId").val();
                if(courseId == 0){
                    $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                    return false;
                }
                if(lessonId == 0){
                    $.qglobal.tip({content:"课件id为0", icon:'error' ,time:3000});
                    return false;
                }
                $.ajax({
                    type : 'POST',
                    cache : false,
                    dataType : 'JSON',
                    url  : SLPGER.root + 'materials/getCourseMyMaterialsList',
                    data : {
                        courseId : courseId ,
                        last   : last,
                        amount : amount
                    }, //form表单序列化
                    success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                        if(result.status == 1){
                            $.qglobal.tip({content:result.msg, icon:'success' ,time:1000});
                            $("#last_materials").val((last++)+1); //赋值分页数据
                            if(result.data){
                                var downhtml = "";
                                $(result.data).each(function(){
                                    downhtml += "<dl onmouseover='download_hover(" + this.id + ")' onmouseout='download_out(" + this.id + ")' id='dl_" + this.id + "'><dt><em><a href='javascript:void(0);'>" + this.filename + "【课件" + lessonId +"】</a></em><span><a href='javascript:void(0);' id='J_meterials_download' data-mid=" + this.id + ">下载</a></span></dt>";
                                    downhtml += "<dd>" + this.filebreif + "</dd></dl>";
                                })
                                $("#materials_download_"+courseId+'_'+lessonId).append(downhtml);
                            }else{
                                $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                            }
                            //添加滚动条
                            var $mulvheight=$('.scrollBox').height();
                            if($mulvheight < $(window).height()- $('.offcnkcbfmain_right_1').height()){
                                $('.scrollBox').css('overflow-y','scroll');
                                $('.scrollBox').css('overflow-x','hidden');
                                $('.scrollBox').css('height',$(window).height()- $('.offcnkcbfmain_right_1').height() - 50);
                            }else{
                                $('.scrollBox').css('overflow-y','hidden')
                            }
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                    }
                });
            });
        },
        //课程详情页面评论调取 J_appraisal_for_course
        getAppraisalForCourse: function(){
            var amount = 5;  //每次加载5条
            var courseId = $("#J_appraisal_for_course").attr("data-cid");
            if(courseId == 0){
                $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                return false;
            }
            $.ajax({
                type : 'POST',
                cache : false,
                dataType : 'JSON',
                url  : SLPGER.root + 'appraisal/index',
                data : {
                    courseId : courseId ,
                    last   : 1,
                    amount : amount
                }, //form表单序列化
                success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                    if(result.status == 1){
                        if(result.data){
                            var anshtml = "";
                            $(result.data).each(function(){
                                anshtml += "<dl><dt><img src='" + this.largeavatar + "'><em></em></dt><dd><span>" + this.realname + "</span>" + this.add_time + "</dd><dd>" + this.content + "</dd></dl>";
                            })
                            var total = Number(result.dialog);
                            $("#atotal").html(total);
                            $("#atotal2").html(total);
                            $("#apparaisal_area").html(anshtml);
                        }else{
                            $.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                        }
                    }else{
                        var total = Number(result.dialog);
                        $("#atotal").html(total);
                        $("#atotal2").html(total);
                        //$.qglobal.tip({content:result.msg, icon:'error' ,time:1000});
                    }
                }
            });
        },
        //课程详情页面资料调取 J_appraisal_for_course
        getMaterialsForCourse: function(){
            $("#J_materials_for_course").parent().attr("class","kcjssel");
            $("#J_catalog_for_course").parent().attr("class","");
            $("#J_faq_for_course").parent().attr("class","");
            $("#catalog_area").hide();
            $("#materials_area").show();
            $("#faq_area").hide();
            var courseId = $("#J_appraisal_for_course").attr("data-cid");
            if(courseId == 0){
                $.qglobal.tip({content:"课程id为0", icon:'error' ,time:3000});
                return false;
            }
            $.ajax({
                type : 'POST',
                cache : false,
                dataType : 'JSON',
                url  : SLPGER.root + 'materials/index',
                data : {
                    courseId : courseId ,
                    last   : 1
                }, //form表单序列化
                success:function(result){ //用ajax方法发送信息到当前Action中的main方法
                    if(result.status == 1){
                        if(result.data){
                            var materhtml = "";
                            var filetypes = "";
                            $(result.data).each(function(){
                                 if(this.filetype == "xls"  || this.filetype == "xlsx"){
                                     filetypes = "zlxz_icon49";
                                 }else if(this.filetype == "ppt" || this.filetype == "pptx"){
                                     filetypes = "zlxz_icon47";
                                 }else if(this.filetype == "tar" || this.filetype == "zip" || this.filetype == "txt"){
                                     filetypes = "zlxz_icon46";
                                 }else if(this.filetype == "doc" || this.filetype == "docx"){
                                     filetypes = "zlxz_icon48";
                                 }else if(this.filetype == "pdf"){
                                     filetypes = "zlxz_icon5";
                                 }
                                 materhtml += "<dl><dt><strong class='" + filetypes + "'></strong><a href='javascript:void(0);'>" + this.filename + "</a>课件" + this.lessonId + "<em>";
                                 materhtml += "<a href='javascript:void(0);' id='J_meterials_download' data-mid='" + this.id + "'>下载</a></em></dt><dd>" + this.filebreif + "</dd></dl>";
                            })
                            $("#materials_area_zone").html(materhtml);
                        }else{
                  
                            $("#materials_area_zone").html('<div class="offcnNonecon"><span>'+result.msg+'</span></div>');
                        }
                    }else{
                        $("#materials_area_zone").html('<div class="offcnNonecon"><span> '+result.msg+'</span></div>');
                    }
                }
            });
        },
        //课程详情页面答疑调取 J_appraisal_for_course
        getFaqForCourse: function(){
            $("#J_materials_for_course").parent().attr("class","");
            $("#J_catalog_for_course").parent().attr("class","");
            $("#J_faq_for_course").parent().attr("class","kcjssel");
            $("#catalog_area").hide();
            $("#materials_area").hide();
            $("#faq_area").show();
        }
    }
    $.qglobal.init();
})(jQuery);