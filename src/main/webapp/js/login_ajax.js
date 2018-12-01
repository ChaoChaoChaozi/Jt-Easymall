//@ sourceURL=login_ajax.js
$(function(){
	
	//30天自动登录
	var alogin_info=getCookie("autoLogin");
	//alert("alogin_info="+alogin_info);
	if(alogin_info!=""){
		var infos=alogin_info.split(":");
		//alert(infos[0]+"    "+infos[1]);
		$("form input[name=username]").val(infos[0]);
		$("form input[name=password]").val(infos[1]);
		return login();
	}
	//记住用户名
	$("form input[name=username]").val(getCookie("loginName"));
	
	//给form表单添加submit事件
	$("form").submit(function(){
		return login();
	});
	
});
function login(){
	//获取页面数据
	var userName=$("form input[name=username]").val();
	var userPassword=$("form input[name=password]").val();
	var remember=$("form input[name=remname]").get(0).checked;
	var autologin=$("form input[name=autologin]").get(0).checked;
	if(userName==""){
		$("form table tr:eq(0) td span").html("用户名不能为空");
		return false;
	}
	if(userPassword==""){
		$("form table tr:eq(1) td span").html("密码不能为空");
		return false;
	}
	//发送异步请求
	$.ajax({
		url:"/user_ajax/login",
		type:"get",
		data:{"userName":userName,"userPassword":userPassword},
		dataType:"json",
		success:function(result){
			//result是服务端返回的数据
			if(result.status==1){
				//window.location.href="index.html";
				window.location.href="/";
				
				if(autologin){
					if(getCookie("autoLogin")==""){
						var value=userName+":"+userPassword;
						setCookie("autoLogin",value);
					}					
				}else{
					delCookie("autoLogin");
				}
				if(remember){
					if(getCookie("loginName")==""){
						addCookie("loginName",userName,5);
					}
				}else{
					delCookie("loginName");
				}
			}else if(result.status==0){
				alert(result.msg);
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
	
	return false;
}
