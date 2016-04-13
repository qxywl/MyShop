function queryMaterails(reqUrl){
	$.ajax({
		url:reqUrl,
		dataType:'json',
		contentType:'application/json;charset=UTF-8',
		type:'POST',
		data:'materialsId=1',
		success:function(result) {
			if(result!=null) {
				$('#goodsOwnerNo').val(result.goodsOwnerNo);
				$('#zhName').val(result.zhName);
				$('#enName').val(result.enName);
			} else {
				$('#goodsOwnerNo').val('');
				$('#zhName').val('');
				$('#enName').val('');
				alert('无可用数据');
			}
		}
	});
}