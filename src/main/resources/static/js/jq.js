$.ajaxSetup({
	cache : false,
	error : function(xhr, textStatus, errorThrown) {
		var msg = xhr.responseText;
		var response = JSON.parse(msg);
		var code = response.code;
		var message = response.message;
		if (code == 400) {
			layer.msg(message);
		} else if (code == 401) {
			layer.msg('未登录');
		} else if (code == 403) {
			console.log("未授权:" + message);
			layer.msg('未授权');
		} else if (code == 500) {
			layer.msg('系统错误：' + message);
		}
	}
});

function buttonDel(data, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<a class='layui-btn  layui-btn-xs btn-del' title='删除' onclick='del(\"" + data +"\")'>删除</a>");
	return btn.prop("outerHTML");
}
function buttonDel2(data, permission, pers){
	if(permission = ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<a class='layui-btn  layui-btn-xs btn-del' title='删除' onclick='del(\"" + data +"\")'>删除</a>");
	return btn.prop("outerHTML");
}
function buttonDel3(data1,data2, permission, pers){
	if(permission = ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<a class='layui-btn  layui-btn-xs btn-del' title='删除' onclick='del(\"" + data1 +"\",\""+data2+"\")'>删除</a>");
	return btn.prop("outerHTML");
}
function buttonEdit(href, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<a class='layui-btn layui-btn-xs btn-edit' title='编辑' onclick='window.location=\"" + href +"\"'>编辑</a>");
	return btn.prop("outerHTML");
}
function buttonSet(data1,data2, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<a class='layui-btn layui-btn-xs btn-edit' title='设置为院领导' onclick='set(\"" + data1+"\",\""+data2 +"\")'>设置为院领导</a>");
	return btn.prop("outerHTML");
}
function buttonNoSet(data1,data2, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<a class='layui-btn layui-btn-xs btn-warning' title='解除设置为院领导' onclick='noset(\"" + data1+"\",\""+data2 +"\")'>解除设置为院领导</a>");
	return btn.prop("outerHTML");
}
function deleteCurrentTab(){
	var lay_id = $(parent.document).find("ul.layui-tab-title").children("li.layui-this").attr("lay-id");
	parent.active.tabDelete(lay_id);
}
