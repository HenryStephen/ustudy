$(function(){
		
		function getCookie(name) {    
			var nameEQ = name + "=";    
			var ca = document.cookie.split(';');    //把cookie分割成组    
			for(var i=0;i < ca.length;i++) {    
				var c = ca[i];                      //取得字符串    
				while (c.charAt(0)==' ') {          //判断一下字符串有没有前导空格    
					c = c.substring(1,c.length);      //有的话，从第二位开始取    
				}    
				if (c.indexOf(nameEQ) == 0) {       //如果含有我们要的name    
					return unescape(c.substring(nameEQ.length,c.length));    //解码并截取我们要值    
				}    
			}
			return false;    
		}
		
		
        if(uidtest){
        }else{
			var sid = getCookie('lds');
            var url = document.domain; 
			if(sid){
				var login_url = "/foreusertest/islogin/url_home/"+url+"/php_new_123sid/"+sid+"/php_new_vs/2/?callback=?";
			}else{
				var login_url = "/foreusertest/islogin/url_home/"+url+"/?callback=?";
			}
            // $.getJSON("http://login.offcn.com/foreusertest/islogin/url_home/"+url+"/?callback=?", function(data){
            $.getJSON(login_url, function(data){
                    if(data.status == 'y'){
                        $.ajax({
                            type : 'POST',
                            dataType : 'json',
                            url  : '/foreuser/getuinfotest/',
                            data : {data:data.loginkey,url_home:url},
                            success:function(result){
                                if(result.status == 'y'){
                                    uidtest = result.uid;
                                    location.reload();
                                }else if(result.status == 's'){
                                    uidtest = result.uid;
                                }
                            }
                        });
                    }
            });               
        }
});