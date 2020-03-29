
//关闭模态框时清空数据
$(function (){
    $("#downloadTemplate").attr("href",pathUrl+"试卷模板.xlsx");
    $("#downloadTemplate").attr("download","试卷模板.xlsx");
    $('#myModal').on('hidden.bs.modal', function (){
        $(':input','#myModal').not(':button,:submit,:reset').val('').removeAttr('checked').removeAttr('checked');
        $("#questionBankStage").val(0).trigger("change");
    });
});

var  FileName = "";
//上传文件格式验证
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function uploadFile(target,id) {
    var filetypes =[".xlsx",".xls"];
    var filepath = target.value;
    if(filepath) {
        var isnext = false;
        var fileend = filepath.substring(filepath.indexOf("."));
        if (filetypes && filetypes.length > 0) {
            for (var i = 0; i < filetypes.length; i++) {
                if (filetypes[i] == fileend) {
                    isnext = true;
                    break;
                }
            }
        }
        if (! isnext) {
            layer.alert("请上传xlsx类型文件");
            target.value = "";
            return false;
        } else {
            var file= document.getElementById("questionBankFilePath").files[0];
            var formdata = new FormData();
            formdata.append("file", file);
            $.ajax({
                type: "POST",//发送
                url: httpUrl+"/util/saveFile",//地址
            data: formdata,//传送的数据
                cache: false,
                processData: false,  // 告诉jQuery不要去处理发送的数据
                contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                success: function (rs) {
                    if(rs.code==10000){
                        FileName = rs.message;
                        layer.msg('上传成功', {icon: 1, time: 1000});
                    }else{

                        layer.msg('上传失败', {icon: 2, time: 1000});
                    }
                },
                error: function () {

                    layer.msg('上传失败', {icon: 2, time: 1000});
                }
            })
        }
    }
}
function addQuestionBankManagement() {
      var questionBankName = $('#questionBankName').val();
      var questionBankStage = $('#questionBankStage').val();
      var questionBankIntroduce = $('#questionBankIntroduce').val();
      var questionBankFilePath = $('#questionBankFilePath').val();
      if(questionBankName == ""){
          layer.alert("请填写试题名称")
          $('#questionBankName').css({
              "border-color": "red"//输入为空时显示变红
          });
      }
      else if(questionBankStage == 0){
          layer.alert("请选择阶段")

          $('#select2-questionBankStage-container').css({
              "border-color": "red"//输入为空时显示变红
          });
      }
      else if(questionBankIntroduce ==""){
          layer.alert("请输入简介")
      }else if (questionBankFilePath == ""){
          layer.alert("请上传文件")
      }
      else if (questionBankIntroduce.length > 40){
          layer.alert("文字超过40字")
      }
      else{
          var userId = sessionStorage.getItem("userId");
          var js={
              questionBankName:questionBankName,
              questionBankStage:questionBankStage,
              questionBankIntroduce:questionBankIntroduce,
              userId:userId,
              questionBankFilePath:FileName
          };
          $.ajax({
              type: "post",//发送
              url:  httpUrl+"/question/addQuestionBank",//地址
              data: JSON.stringify(js),//传送的数据
              contentType: "application/json;charset=utf-8",
              dataType: "json",
              success: function (res) {
                  if(res.code==10000) {
                      layer.msg('添加成功', {icon: 1, time: 1000});
                      $('#myModal').modal('hide');
                      refreshTable();
                  }
              },
              error: function () {
                  layer.msg('添加失败', {icon: 2, time: 1000});
              }
          })
      }
  }
//发布人字典表
$(function () {
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"/grade/selectSysUser",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            if(res.code==10000) {
                for (var i = 0; i < res.data.length; i++) {
                    var username=res.data[i].username;
                    $('#studentName').append($('<option value ="' + username + '">' + username + '</option>'));
                }
            }
        }, error: function () {

            layer.msg('发布人字典表查询失败', {icon: 2, time: 1000});
        }
    });
    //初始化表格
    var oTable = new TableInit();
    oTable.Init();
});

function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#questionBankManagement').bootstrapTable({
            url: httpUrl+'/question/selectQuestionBank',         //请求后台的URL（*）
            method: 'POST',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortOrder: "asc",                   //排序方式
            queryParamsType: '',
            dataType: 'json',
            paginationShowPageGo: true,
            showJumpto: true,
            pageNumber: 1, //初始化加载第一页，默认第一页
            queryParams: queryParams,//请求服务器时所传的参数
            sidePagination: 'server',//指定服务器端分页
            pageSize: 10,//单页记录数
            pageList: [5, 10, 15, 20],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列

            columns: [
               {
                    field: 'questionBankId',
                    title: '测评编号',
                    align: 'center',
                   formatter: function (value, row, index) {
                       return index + 1;
                   }
                },
                {
                    field: 'questionBankName',
                    title: '试题名称',
                    align: 'center',
                    'background-color':'#0aa66e'
                },
                {
                    field: 'questionBankIntroduce',
                    title: '简介',
                    align: 'center'
                },
                {
                    field: 'questionBankStage',
                    title: '阶段名称',
                    align: 'center'
                },
                {
                    field: 'questionBankFilePath',
                    title: '文件',
                    align: 'center'
                },
                {
                    field: 'userName',
                    title: '发布人',
                    align: 'center'
                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    events:operateEvents,//给按钮注册事件
                    formatter: addFunctionAlty//表格中增加按钮
                }],
            responseHandler: function (res) {  //后台返回的结果
                if(res.code == "10000"){

                    var userInfo = res.data.list;
                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'questionBankId': '',
                                "questionBankName": '',
                                "questionBankIntroduce": '',
                                "questionBankStage": '',
                                "questionBankFilePath": '',
                                "userName": '',
                                "operation": ''
                            };
                            dataNewObj.questionBankId = userInfo[i].questionBankId;
                            dataNewObj.questionBankName = userInfo[i].questionBankName;
                            dataNewObj.questionBankIntroduce = userInfo[i].questionBankIntroduce;
                            dataNewObj.questionBankStage = getstage(userInfo[i].questionBankStage);
                            dataNewObj.questionBankFilePath = downloadtext(userInfo[i].questionBankName,userInfo[i].questionBankFilePath);
                            dataNewObj.userName = userInfo[i].userName;
                            NewData.push(dataNewObj);
                        }
                    }
                    var data = {
                        total: res.data.total,
                        rows: NewData
                    };
                    return data;
                }else {
                    var data = {
                        total: 0,
                        rows: NewData
                    };
                    return data;
                }
            }

        });
    };
    // 得到查询的参数
    function queryParams(params) {
        var questionBankName = $('#evaluationName').val();
        var userName = $('#studentName').val();
        var questionBankStage  = $('#stage').val();
        var temp = {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,                     //初始化加载第一页，默认第一页
            pageSize: params.pageSize,
            questionBankName:questionBankName,
            userName:userName,
            questionBankStage:questionBankStage
        };
        return JSON.stringify(temp);
    }
    return oTableInit;
}
  //刷新表格
  function refreshTable() {
      $("#questionBankManagement").bootstrapTable('refresh');
  }
//表格内信息处理方法
function downloadtext(name,path) {
    name=name+".xlsx";
    return [
        '<a href="'+pathUrl+path+'" download="'+name+'" style="color: #3276b1;">点击下载</a>'
    ].join('');
}
function getstage(e) {
    if (e == 1){
        return '第一学年'
    }  if (e == 2){
        return '第二学年'
    }  if (e == 3){
        return '第三学年'
    }
}
function addFunctionAlty(value, row, index) {//把需要创建的按钮封装在函数中
    return[
        '<a class="layui-btn layui-btn-xs btn-see" id="see">查看</a>',
        '<a class="layui-btn  layui-btn-xs btn-del" id="delete">删除</a>'
    ].join("")
}
window.operateEvents = {//添加一个按钮组在对应的按钮组中写下需要使用的事件
    "click #see": function (e, vlaue, row, index) {//预览按钮事件
            $("#evaluationQueryStudent").hide();
            $("#evaluationQueryInfo").show();
            var data = {
                questionBankId: row.questionBankId
            };
            $.ajax({
                type: "POST",
                url: httpUrl+"/question/problemAndSolution",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json;charest=utf-8",
                success: function (rs) {
                        var  html=rs.data[0].questionBankName;
                        $("#tbo").html(html);
                    var text = "";
                            for (var i = 0;i<rs.data.length;i++){
                                text += "<div class=\"layui-form-item\" style='padding-left: 15% '>";
                                text += "                    <div class=\"layui-input-block\">"+parseInt(i+1)+"．"+rs.data[i].problemTitle+"</div>";
                                text += "                    <div class=\"layui-input-block\">";
                                text += "                        <input type=\"radio\" name=\"problem"+parseInt(i+1)+"\" value=\"\"><div class=\"layui-unselect layui-form-radio\" style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>A."+rs.data[i].solutionEntities[0].solutionContent+"</div></div>";
                                text += "                        <input type=\"radio\" name=\"problem"+parseInt(i+1)+"\" value=\"\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>B."+rs.data[i].solutionEntities[1].solutionContent+"</div></div>";
                                text += "                        <input type=\"radio\" name=\"problem"+parseInt(i+1)+"\" value=\"\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>C."+rs.data[i].solutionEntities[2].solutionContent+"</div></div>";
                                text += "                        <input type=\"radio\" name=\"problem"+parseInt(i+1)+"\" value=\"\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%; margin-bottom: 15px'><i class=\"layui-anim layui-icon\"></i><div>D."+rs.data[i].solutionEntities[3].solutionContent+"</div></div>";
                                text += "                    </div>";
                                text += "                </div>";
                            }
                    $("#tbo2").html(text);
                },error: function () {

                    layer.msg('网络异常请重试', {icon: 2, time: 1000});
                }
            })

    },
    "click #delete": function (e, vlaue, row, index) {//删除按钮事件
        layer.open({
            content: "确认要删除吗?",
            btn:["确认","取消"],
            yes: function () {
                var data = {
                    questionBankId: row.questionBankId
                };
                $.ajax({
                    type: "POST",
                    url: httpUrl+"/question/deleteQuestionBank",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: "application/json;charest=utf-8",
                    success: function (rs) {
                        layer.msg('删除成功!', {icon: 1, time: 1000});
                        setTimeout("javascript:location.href='questionBankManagement.html'", 1000);
                    },error: function () {

                        layer.msg('删除失败', {icon: 2, time: 1000});
                    }
                })
            }
        })

    }
};
function go_back() {
    $("#evaluationQueryStudent").show();
    $("#evaluationQueryInfo").hide();
}

