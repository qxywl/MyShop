$(function(){

	$("#spec-list").on('mouseover','img',function(){
		var src=$(this).attr("src");
		$("#spec-n1 img").eq(0).attr({
			src:src.replace("\/n5\/","\/n1\/"),
			jqimg:src.replace("\/n5\/","\/n0\/")
		});
	});				
	

	
	//库存
	$("#kc").each(function(){
		var _this = $(this);
		var _input = _this.find("input");
		var _kcNum =_this.find("em").text();
		_this.find("a").eq(0).click(function(){
			if(_input.val()<=1){
				alert("至少购买一件");
				return false;
			}else{
				_input.val(parseInt(_input.val())-1);
			}
		});
		_this.find("a").eq(1).click(function(){
			// console.log(typeof( parseInt(_kcNum)));
			if(parseInt(_kcNum)<=parseInt(_input.val())){
				alert("不要大于库存");
				// console.log(parseInt(_kcNum));
				_input.val(parseInt(_kcNum));
				return false;
			}else{
				_input.val(parseInt(_input.val())+1);
			}
		});
	});

	$(".shul_txt").find("input").change(function() {
		if(!/^\d{1,3}$/.test($(this).val())) {
			alert("只能输入数字");
			return false;
		}
	});
});


