$(function(){
	$(".del").click(function(){
		if(confirm("您确定删除吗？")){
			var id = $(this).parent().parent().find("input[class=buyNumInp1]").attr("id");
			window.location.href="${app}/cart/deleteCart/"+id;
		}
	});
	$(".delNum").click(function(){
		//1获取商品的id
		var id = $(this).next().attr("id");
		//2获取输入框中数量
		var num = $(this).next().val();
		//3、计算减1后的值
		num = num-1;
		if(num>0){
			//4、跳转
			window.location.href="${app}/cart/editCart/"+id+"/"+num;
		}else{
			if(confirm("您确认删除吗？")){
				window.location.href="${app}/cart/deleteCart/"+id;
			}
		}
	});
	$(".addNum").click(function(){
		//alert("addmum");
		//1.获取商品id
		var id = $(this).prev().attr("id");
		//2获取输入框中数量
		var num = $(this).prev().val();
		//3计算数量
		num = parseInt(num)+1;
		//alert(id+"   "+num);
		//4跳转
		window.location.href="${app}/cart/editCart/"+id+"/"+num;;
	});
	$(".buyNumInp1").blur(function(){
		//获取输入框中的数量
		var num = $(this).val();
		//获取商品id
		var id = $(this).attr("id");
		//获取隐藏域中的值
		var oldNum = $("#hid_"+id).val();
		//如果num!=oldNum才执行修改操作
		if(num!=oldNum){
			//判断是否为0，
			if(num==0){//执行删除
				window.location.href="${app}/cart/deleteCart/"+id;
			}else if(/^[1-9][0-9]*$/.test(num)){
				//判断输入的数据是否合法
				window.location.href="${app}/cart/editCart/"+id+"/"+num;;
			}else{
				alert("请输入大于等于0的整数！");
				//回显  text() html() attr("..")
				$(this).val(oldNum);
			}
		}
	});
});