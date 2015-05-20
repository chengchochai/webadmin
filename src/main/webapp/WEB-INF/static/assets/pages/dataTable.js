function init(url, data, tableName) {
	$('#' + tableName).DataTable(
			{
				language : {
					"lengthMenu" : "每页 _MENU_ 条记录",
					"zeroRecords" : "没有找到记录",
					"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
					"infoEmpty" : "没有记录",
					"infoFiltered" : "(从 _MAX_ 条记录中过滤)",
					"search" : "搜索:",
					"loadingRecords" : "加载中...",
					"paginate" : {
						"first" : "首页",
						"last" : "末页",
						"next" : "下页",
						"previous" : "下页"
					}
				},
				bSort : false,// 排序
				bFilter : false,// 搜索
				searching : false,// 搜索
				bLengthChange : false,// 页面大小
				responsive : true,//
				serverSide : true,// 开启异步数据加载
				pagingType : "full_numbers",// 显示首页和尾页
				sAjaxSource : "url",
				fnServerData : function(sSource, aDataSet, fnCallback) {
					sSource = url + "/service/list/" + aDataSet[3].value
							+ "/game/" + aDataSet[4].value;
					$.ajax({
						"dataType" : 'json',
						"type" : "POST",
						"data" : {
						// gamename : $("#gamename").val(),
						// classId : $("#classId").val(),
						// state : $("#state").val()
						},
						"url" : sSource,
						"success" : fnCallback
					});
				}
			});
}