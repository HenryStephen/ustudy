$(function(){
		
		function getCookie(name) {    
			var nameEQ = name + "=";    
			var ca = document.cookie.split(';');    //��cookie�ָ����    
			for(var i=0;i < ca.length;i++) {    
				var c = ca[i];                      //ȡ���ַ���    
				while (c.charAt(0)==' ') {          //�ж�һ���ַ�����û��ǰ���ո�    
					c = c.substring(1,c.length);      //�еĻ����ӵڶ�λ��ʼȡ    
				}    
				if (c.indexOf(nameEQ) == 0) {       //�����������Ҫ��name    
					return unescape(c.substring(nameEQ.length,c.length));    //���벢��ȡ����Ҫֵ    
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