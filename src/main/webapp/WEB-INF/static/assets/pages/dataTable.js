function init(url) {
	
	var json = {};

	$('#searchButton').click(function (){
		$("#searchForm").find('input').each(function(){
	    	json[$(this).attr("name")]=$(this).val();
	    });
		$('#dataTables').DataTable().draw();//重新填充数据
	})
	
	$('#dataTables').DataTable({
		"language": {
			"zeroRecords": "没有找到记录",
            "info": "从 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            "infoEmpty": "没有记录",
            "processing" : "加载中...",
            "paginate": {
            	"first":"首页",
                "last":"末页",
                "next":"下页",
                "previous":"上页"
            }
		},
        responsive : true,//响应
        processing : true,//加载中
        bSort : false,// 排序
        bFilter : false,// 搜索
		searching : false,// 搜索
		bLengthChange : false,// 页面大小
		serverSide : true,// 开启异步数据加载
		pagingType : 'full_numbers',// 显示首页和尾页
		sAjaxSource : url,
		fnServerData : function(sSource, aDataSet, fnCallback) {
			json.start=aDataSet[3].value;
			json.limit=aDataSet[4].value;
			json.sEcho=aDataSet[0].value;
			alert(JSON.stringify(json));
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"data" : json,
				"url" : sSource,
				"success" : fnCallback
			});
		}
	});
}