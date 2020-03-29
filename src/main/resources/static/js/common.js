// var httpUrl = "http://192.168.2.146:8080/";
// var pathUrl = "http://192.168.2.146:8080/imgFiles/";
var httpUrl = getRootPath_web()+"/";
var pathUrl = getRootPath_web() + "/imgFiles/";
function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
     console.log(localhostPaht);
    // return (localhostPaht + projectName);
    return localhostPaht;
}
//form序列化为json
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1){
		return "";
	}
	var params = url[1].split("&");
	
	for(var i=0; i<params.length; i++){
		var param = params[i].split("=");
		if(key == param[0]){
			return param[1];
		}
	}
}

// 检查登录状态
function loginInfo(){
	var user = "";
    $.ajax({
        type : 'get',
        url : '/sys/login',
        async: false,
        success : function(data){
            if(data != null && data != ""){
                user = data;
            }
        },
        error: function(xhr,textStatus,errorThrown){
            var msg = xhr.responseText;
            var response = JSON.parse(msg);
            $("#info").html(response.message);
        }
    });
    
    return user;
}



/**
 * 获取数据（ajax）
 */
var getData = function (param) {
    var self = this;
    var http = self[self.env+'HTTP'];
    if(!param.url){
        console.log('参数异常')
        return;
    }
    param.type = param.type||'POST';
    param.async = param.async||true;
    param.header = param.header||{"Authorization":'1',
            "Access-Control-Allow-Credentials":true
        };
    if(typeof param.data === "object"){
        param.data = param.data === undefined?{}:JSON.stringify(param.data);
    }
    param.success = param.success || function(ret){
            console.log("提交成功");
            layer.msg("提交成功")
    };
    param.error = param.error || function(err){
            console.log(err);
            console.log("提交失败");
            layer.msg("失败")
    };
    $.ajax({
        url:http+param.url,
        type:param.type,
        contentType:"application/json",
        header:param.header,
        data:param.data,
        xhrFields: {
            withCredentials: true
        },
        success:function(result){
            switch (result.code){
                case '201'://出现错误
                    console.log('出现错误');
                    layer.msg("失败");
                    break;
                case '202'://未登录
                    break;
                case '203'://参数异常
                    console.log('出现异常');
                    layer.msg("失败");
                    break;
                default:
                    param.success(result);
                    break;
            }
            result = null;
        },
        error:function (err) {
            param.error(err);
        }
    })
};

// function test() {
//     var param = {
//         type:"post",
//         url:"/user/select",
//         data:{},
//         success:function (ret) {
//             //成功
//         },
//         error: function (err) {
//             //失败
//         }
//     };
//     Common.prototype.getData(param);
// }