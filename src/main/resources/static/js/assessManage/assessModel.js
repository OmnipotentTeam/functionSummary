var username;
var state = '';
var stageEntityList;
var SysUserList = [];
var DimensionList = [];
var stageEntityList = [];
var unreleasedList = [];
var endEntityList = [];
var taskId;
var num;
var nums;
var long;
var l;
var i = 1;
var stoptime;
var renwu;
var b_stoptime;
var b_renwu;
var kk;  //这是编辑任务的阶段数
var optionDisplar = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q'];
var initSel = {};
var categoryinit;//类别
var modelLevelinit;//级别
var applicablegradeinit;//适用年级
var modelMustbeinit;//是否必选
var modelStateinit;//状态
$(function () {
    //发布人字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl + "grade/selectSysUser",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            console.log(res)
            SysUserList = res.data;
            for (var i = 0; i < res.data.length; i++) {
                username = res.data[i].username;
                var item = res.data[i].id;
                $('#seluserId').append($('<option value ="' + item + '">' + username + '</option>'));
            }
        },
    });
    //初始化类别
    $.ajax({
        type: "post",//发送
        url: httpUrl + "assess/detailmsgselect",//地址
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({dicttype: "modelcategory"}),
        dataType: "json",
        success: function (res) {
            categoryinit = res.data.listdict;
            //console.log(res);
            initSel.categoryinit = res.data.listdict[0].k;
            for (var i = 0; i < res.data.listdict.length; i++) {
                $('#selcategory').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#category').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#categoryupdate').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#categorydetail').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
            }
        },
    });

    //初始化级别
    $.ajax({
        type: "post",//发送
        url: httpUrl + "assess/detailmsgselect",//地址
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({dicttype: "modelLevel"}),
        dataType: "json",
        success: function (res) {
            //console.log(res);
            modelLevelinit = res.data.listdict
            initSel.modelLevelinit = res.data.listdict[0].k;
            for (var i = 0; i < res.data.listdict.length; i++) {
                $('#sellevel').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelLevel').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelLevelupdate').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelLeveldetail').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
            }
        },
    });
    //初始化学年
    $.ajax({
        type: "post",//发送
        url: httpUrl + "assess/detailmsgselect",//地址
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({dicttype: "applygrade"}),
        dataType: "json",
        success: function (res) {
            //console.log(res);
            console.log(">>>>>>>>>>>>>>>")
            console.log(res.data.listdict)
            console.log(">>>>>>>>>>>>>>>")
            initSel.applicablegradeinit = res.data.listdict[0].k;
            applicablegradeinit = res.data.listdict;
            for (var i = 0; i < res.data.listdict.length; i++) {
                $('#selgrade').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#applicablegrade').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#applicablegradeupdate').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#applicablegradedetail').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
            }
        },
    });

    //初始化必须
    $.ajax({
        type: "post",//发送
        url: httpUrl + "assess/detailmsgselect",//地址
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({dicttype: "modelMustbe"}),
        dataType: "json",
        success: function (res) {
            // console.log(res);
            modelMustbeinit = res.data.listdict;
            initSel.modelMustbeinit = res.data.listdict[0].k;
            for (var i = 0; i < res.data.listdict.length; i++) {
                $('#selmustbe').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelMustbe').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelMustbeupdate').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelMustbedetail').append($('<option id="aaa" value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
            }
        },
    });

    //初始化状态
    $.ajax({
        type: "post",//发送
        url: httpUrl + "assess/detailmsgselect",//地址
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify({dicttype: "modelState"}),
        dataType: "json",
        success: function (res) {
            // console.log(res);
            modelStateinit = res.data.listdict;
            initSel.modelStateinit = res.data.listdict[0].k;
            for (var i = 0; i < res.data.listdict.length; i++) {


                $('#selstate').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelState').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelStateupdate').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
                $('#modelStatedetail').append($('<option value ="' + res.data.listdict[i].k + '">' + res.data.listdict[i].val + '</option>'));
            }
        },
    });

    $('#date').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });

    $('#update-date').datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    var oTable = new TableInit();
    oTable.Init();

})

//add by fuqiang 2019-7-18 初始化表格
function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#task').bootstrapTable({
            url: httpUrl + "assess/query",         //请求后台的URL（*）
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
            // pageSize: 10,//单页记录数
            // pageList: [10, 20, 30, 40],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            columns: [
                {
                    field: 'taskId',
                    title: '序号',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                {
                    field: 'modelName',
                    title: '模型名称',
                    align: 'center'
                },
                {
                    field: 'applicablegrade',
                    title: '适用年级',
                    align: 'center'
                },
                {
                    field: 'category',
                    title: '类别',
                    align: 'center'
                },
                {
                    field: 'modelLevel',
                    title: '级别',
                    align: 'center'
                },
                {
                    field: 'modelMustbe',
                    title: '是否必须',
                    align: 'center'
                },
                {
                    field: 'modelState',
                    title: '状态',
                    align: 'center'
                },
                {
                    field: 'userId',
                    title: '发布教师',
                    align: 'center'
                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (state == '' || state == '0') {
                            return addFunctionAlty(value, row, index);
                        } else {
                            return addFunctionAltys(value, row, index);
                        }
                    }
                }
            ],
            responseHandler: function (res) {  //后台返回的结果

                console.log(res);
                if (res.code == "10000") {
                    var userInfo = res.data.list;
                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {

                            var dataNewObj = {
                                'modelId': '',
                                "modelName": '',
                                "applicablegrade": '',
                                "category": '',
                                "modelLevel": '',
                                "modelMustbe": '',
                                "modelState": '',
                                "userId": ''
                            };
                            dataNewObj.modelId = userInfo[i].id;
                            dataNewObj.modelName = userInfo[i].title;
                            dataNewObj.applicablegrade = userInfo[i].applicablegradeName;
                            dataNewObj.category = userInfo[i].categoryName;
                            dataNewObj.modelLevel = userInfo[i].modellevelName;
                            dataNewObj.modelMustbe = userInfo[i].requiredName;
                            dataNewObj.modelState = userInfo[i].openCloseStateName;
                            dataNewObj.userId = getUserName(userInfo[i].userId);
                            NewData.push(dataNewObj);
                        }
                    }
                    var data = {
                        total: res.data.total,
                        rows: NewData
                    };
                    return data;
                } else {
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
        var taskName = $('#taskName').val();
        var selgrade = $('#selgrade').val();
        var selcategory = $('#selcategory').val();
        var sellevel = $('#sellevel').val();
        var selmustbe = $('#selmustbe').val();
        var selstate = $('#selstate').val();
        var userId = $('#userId').val();
        var temp = {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,                     //初始化加载第一页，默认第一页
            pageSize: params.pageSize,
            title: taskName,
            applicablegrade: selgrade,
            category: selcategory,
            modelLevel: sellevel,
            required: selmustbe,
            openCloseState: selstate
        };
        //console.log(temp);
        unreleasedList = [];
        return JSON.stringify(temp);
    }

    return oTableInit;
}

//刷新表格
function refreshTable() {
    $("#task").bootstrapTable('refresh');
}

//add by fuqiang 2019-7-18
function addFunctionAlty(value, row, index) {
    var btnText = '';
    btnText += "<input type=\"button\" data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dtl\" onclick=\"details(" + "'" + row.modelId + "'" + ")\" value=\"详情\">";
    btnText += "<input type=\"button\" data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-edit\"onclick=\"updateTask(" + "'" + row.modelId + "'" + ")\" value=\"编辑\">";
    btnText += "<input type=\"button\" data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-del\"   onclick=\"deleteTask(" + "'" + row.modelId + "'" + ")\" value=\"删除\" >";
    return btnText;
}

//显示添加模态框
function addModal() {
    $("#addModal").modal('show');
}

//add bu fuqiang 2019-7-19添加选项
function addDiv() {
    var text = "";
    text += "<div class=\"layui-fluid stageDiv\" id=\"OptionDiv" + i + "\">";
    text += "  <div class=\"layui-card\">";
    text += "    <div class=\"layui-form\" wid110=\"\">";
    text += "       <div class=\"layui-form-item\">";
    text += "         <label class=\"layui-form-label optionnum\">" + optionDisplar[i - 1] + "</label>";
    text += "         <textarea style='width: 350px;lenght:90px;'  maxlength=\"200\"  placeholder=\"请填写选项内容（200字以内）\" class=\"stageContent layui-textarea optioncomment\"></textarea>";
    text += "         <button type=\"button\" class=\"close\" id=\"deleteStage" + i + "\" onclick=\"deleteStage()\" style='display: none'>×</button>";
    text += "       </div>";
    text += "    </div>";
    text += "  </div>";
    text += " </div>";

    $("#stage").append(text);

    $('#deleteStage' + i).show();
    var k = i - 1;
    if (k > 0) {
        $('#deleteStage' + k).hide();
    }
    ++i;
}

//add bu fuqiang 2019-7-19添加框提交信息
function submit() {
    var modelName = $('#modelName').val();
    var modelDesc = $('#modelDesc').val();
    var applicablegrade = $('#applicablegrade').val();
    var category = $('#category').val();
    var modelLevel = $('#modelLevel').val();
    var modelMustbe = $('#modelMustbe').val();
    var modelState = $('#modelState').val();

    var userId = sessionStorage.getItem("userId");
    stageEntityList = [];


    if (modelName == "") {
        layer.alert('请输入模型名称');
        return false;
    } else if (userId == undefined || userId == "") {
        layer.alert('请登录后再发布项目')
        return false;
    } else if ($('.stageDiv').length == 0) {
        layer.alert('请填加选项');
        return false;
    } else {
        for (var j = 0; j < $('.optionnum').length; j++) {
            var optionEntity = {};
            optionEntity.optionOrder = $('.optionnum')[j].innerHTML;
            if ($('.optioncomment')[j].value == '') {
                layer.alert('请填写' + optionEntity.optionOrder + '选项内容');
                return false;
            } else {
                optionEntity.optionComment = $('.optioncomment')[j].value;
                stageEntityList.push(optionEntity);
            }
        }
    }

    var js = {
        title: modelName,
        description: modelDesc,
        applicablegrade: applicablegrade,
        category: category,
        modelLevel: modelLevel,
        required: modelMustbe,
        openCloseState: modelState,
        userId: userId,
        optionList: stageEntityList
    };
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: httpUrl + "assess/add",
        data: JSON.stringify(js),
        dataType: 'json',
        success: function (rs) {
            if (rs.code == 10000) {
                layer.alert("添加成功");
                refreshTable();
                empty();
                $('#addModal').modal('hide');
            }
        },
        error: function () {
        }
    });
}

//删除阶段
function deleteStage() {
    i--;
    var k = i - 1;
    // console.log(i);
    // console.log(k);
    $('#deleteStage' + k).show();
    $("#OptionDiv" + i).remove();
}

//编辑删除的阶段
function deleteStages() {
    i--;
    kk--;
    // console.log(i);
    // console.log(kk);
    $('#deleteStages' + kk).show();
    $("#stageDiv" + i).remove();
}

//编辑框添加阶段
function addDivs() {
    l = long + 1;
    var text = "";
    text += "<div class=\"layui-fluid stageDivs\" id=\"stageDivs" + l + "\">";
    text += "   <div class=\"layui-card\">";
    text += "        <div class=\"layui-form\" wid110=\"\">";
    text += "             <div class=\"layui-form-item\">";
    text += "                   <label class=\"layui-form-label optionnum optionid\">" + optionDisplar[l - 1] + "</label>";
    text += "                   <textarea style='width: 350px' maxlength=\"200\"  placeholder=\"请填写任务内容(200字以内)\" class=\"stageContent layui-textarea optioncomment\"></textarea>";
    text += "                   <button type=\"button\" class=\"close\" id=\"update-deleteStage" + l + "\" onclick=\"deleteStages()\" style='display: none'>×</button>";
    text += "               </div>";
    text += "            </div>";
    text += "    </div>";
    text += "</div>";

    $("#stageupdate").append(text);
    $('#update-deleteStage' + l).show();
    var k = l - 1;
    if (k > 0) {
        $('#update-deleteStage' + k).hide();
    }
    ++long;
}

//删除编辑中的阶段
function deleteStages() {
    var k = long - 1;
    $('#update-deleteStage' + k).show();
    $("#stageDivs" + long).remove();
    long--;
}

//add by fuqiang 2019-7-19 模型详细信息
function details(modelId) {
    //console.log(modelId);
    js = {
        "id": modelId
    }
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: httpUrl + "assess/detailmsg",
        data: JSON.stringify(js),
        dataType: 'json',
        success: function (re) {
            if (re.code == 10000) {
                myclear();
                mydisplay(re);

                //标题
                $('#modelNamedetail').val(re.data.model.title);
                //描述
                $('#modelDescdetail').val(re.data.model.description);


                var text = "选项";
                for (var i = 0; i < re.data.optionList.length; i++) {
                    text += "  <div class=\"layui-card\">";
                    text += "    <div class=\"layui-form\" wid110=\"\">";
                    text += "       <div class=\"layui-form-item\">";
                    text += "         <label class=\"layui-form-label optionnum\">" + re.data.optionList[i].optionOrder + "</label>";
                    text += "         <textarea style='width: 350px;lenght:90px;' disabled='disabled' class=\"stageContent layui-textarea optioncomment\">" + re.data.optionList[i].optionComment + "</textarea>";
                    text += "       </div>";
                    text += "    </div>";
                    text += " </div>";
                }
                $("#stagedetail").append(text);

                $("#detailedModal").modal('show');


            }
        },
        error: function () {
            layer.alert("服务异常");
        }
    });

}

//add by fuqiang 2019-7-19 编辑模块信息
function updateTask(modelId) {
    js = {
        "id": modelId
    }
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: httpUrl + "assess/detailmsg", // assess/query
        data: JSON.stringify(js),
        dataType: 'json',
        success: function (re) {
            //console.log(re);
            if (re.code == 10000) {
                $('#modelid').val(re.data.model.id);
                $('#modelNameupdate').val(re.data.model.title);
                $('#modelDescupdate').val(re.data.model.description);

                $('#applicablegradeupdate').val(re.data.model.applicablegrade).trigger("change");
                $('#categoryupdate').val(re.data.model.category).trigger("change");
                $('#modelLevelupdate').val(re.data.model.modellevel).trigger("change");
                $('#modelMustbeupdate').val(re.data.model.required).trigger("change");
                $('#modelStateupdate').val(re.data.model.openCloseState).trigger("change");

                changegradeup();
                var text = "选项";
                for (var i = 0; i < re.data.optionList.length; i++) {
                    text += "<div class=\"layui-fluid stageDivs\" id=\"stageDivs" + i + "\">";
                    text += "  <div class=\"layui-card\">";
                    text += "    <div class=\"layui-form\" wid110=\"\">";
                    text += "       <div class=\"layui-form-item\">";
                    text += "         <label class=\" optionid\" style=\"display:none\" >" + re.data.optionList[i].id + "</label>";
                    text += "         <label class=\"layui-form-label optionnum\">" + re.data.optionList[i].optionOrder + "</label>";
                    text += "         <textarea style='width: 350px;lenght:90px;' disable=''disable'\" class=\"stageContent layui-textarea optioncomment\">" + re.data.optionList[i].optionComment + "</textarea>";
                    text += "         <button type=\"button\" class=\"close\" id=\"deleteStage" + i + "\" onclick=\"deleteStage()\" style='display: none'>×</button>";
                    text += "       </div>";
                    text += "    </div>";
                    text += "   </div>";
                    text += " </div>";
                }
                $("#stageupdate").append(text);
                long = re.data.optionList.length;
                $("#updateModal").modal('show');


            }
        },
        error: function () {
            layer.alert("服务异常");
        }
    });

}

//add by fuqiang 2019-7-22
function updateSubmit() {
    var id = $('#modelid').val();
    var modelName = $('#modelNameupdate').val();
    var modelDesc = $('#modelDescupdate').val();
    var applicablegrade = $('#applicablegradeupdate').val();
    var category = $('#categoryupdate').val();
    var modelLevel = $('#modelLevelupdate').val();
    var modelMustbe = $('#modelMustbeupdate').val();
    var modelState = $('#modelStateupdate').val();

    var userId = sessionStorage.getItem("userId");
    var stageEntityList = [];

    if (modelName == "") {
        layer.alert('请输入模型名称');
        return false;
    } else if (userId == undefined || userId == "") {
        layer.alert('请登录后再发布项目')
        return false;
    } else if ($('.stageDivs').length == 0) {
        layer.alert('请填加选项');
        return false;
    } else {
        console.log($('.optioncomment'));
        for (var j = 0; j < $('.optionnum').length; j++) {
            var optionEntity = {};
            optionEntity.optionOrder = $('.optionnum')[j].innerHTML;
            if ($('.optioncomment')[j].value == '') {
                layer.alert('请填写' + optionEntity.optionOrder + '选项内容');
                return false;
            } else {
                optionEntity.optionComment = $('.optioncomment')[j].value;
                optionEntity.id = $('.optionid')[j].innerHTML;
                stageEntityList.push(optionEntity);
            }
        }
    }

    var js = {
        id: id,
        title: modelName,
        description: modelDesc,
        applicablegrade: applicablegrade,
        category: category,
        modelLevel: modelLevel,
        required: modelMustbe,
        openCloseState: modelState,
        userId: userId,
        optionList: stageEntityList
    };
    // console.log(js);
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=utf-8",
        url: httpUrl + "assess/update",
        data: JSON.stringify(js),
        dataType: 'json',
        success: function (rs) {
            if (rs.code == 10000) {
                layer.alert("修改成功");
                refreshTable();
                empty();
                $('#updateModal').modal('hide');
            }
        },
        error: function () {
        }
    });

}

//add by fuqiang2019-7-18 删除模块
function deleteTask(e) {
    layer.open({
        content: "确认要删除吗?",
        btn: ["确认", "取消"],
        yes: function () {
            var js = {
                id: e
            };
            $.ajax({
                type: "post",//发送
                url: httpUrl + "assess/delete",//地址
                data: JSON.stringify(js),//传送的数据
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (res) {
                    if (res.code == 10000) {
                        layer.msg('删除成功');
                        refreshTable();
                    }
                },
                error: function () {
                    layer.msg('删除失败');
                }
            })
        }
    })
}

//add by fuqiang 219-7-22
function gradeEvaluation(e) {
    refreshTable();
}

//add by fuqiang 2019-7-19 清空文本框
function empty() {
    i = 1;
    $('#modelName').val("");
    $('#modelDesc').val("");
    $('#applicablegrade').val(initSel.applicablegradeinit).trigger("change");
    $('#category').val(initSel.categoryinit).trigger("change");
    $('#modelLevel').val(initSel.modelLevelinit).trigger("change");
    $('#modelMustbe').val(initSel.modelMustbeinit).trigger("change");
    $('#modelState').val(initSel.modelStateinit).trigger("change");


    $('#stage').empty();
    $('#stagedetail').empty();
    $('#stageupdate').empty();
}

//表格内信息处理方法
function getUserName(e) {
    for (var i = 0; i < SysUserList.length; i++) {
        if (e == SysUserList[i].id) {
            return SysUserList[i].username;
        }
    }
}

function changegrade() {
    var gradesel = $('#applicablegrade option:selected').val();
    if (gradesel == "51" || gradesel == "50") {
        $("#category").val(initSel.categoryinit).trigger("change");
        $("#category").attr("disabled", "true");
    } else {
        $("#category").removeAttr("disabled");
    }
}

function changegradeup() {
    var gradesel = $('#applicablegradeupdate option:selected').val();
    if (gradesel == "51" || gradesel == "50") {
        $("#categoryupdate").val(initSel.categoryinit).trigger("change");
        $("#categoryupdate").attr("disabled", "true");
    } else {
        $("#categoryupdate").removeAttr("disabled");
    }
}
//清空详情
function myclear() {
    $('#categorydetail').empty();
    $('#applicablegradedetail').empty();
    $('#modelLeveldetail').empty();
    $('#modelMustbedetail').empty();
    $('#modelStatedetail').empty();
}
// 赋值
function mydisplay(re) {
    //适用年级
    for (var i = 0; i < applicablegradeinit.length; i++) {
        if (applicablegradeinit[i].k == re.data.model.applicablegrade) {
            $('#applicablegradedetail').append($('<option value ="' + applicablegradeinit[i].k + '">' + applicablegradeinit[i].val + '</option>'));
        }
    }
    //类型
    for (var i = 0; i < categoryinit.length; i++) {
        if (categoryinit[i].k == re.data.model.category) {
            $('#categorydetail').append($('<option value ="' + categoryinit[i].k + '">' + categoryinit[i].val + '</option>'));
        }
    }
    //级别
    for (var i = 0; i < modelLevelinit.length; i++) {
        if (modelLevelinit[i].k == re.data.model.modellevel) {
            $('#modelLeveldetail').append($('<option value ="' + modelLevelinit[i].k + '">' + modelLevelinit[i].val + '</option>'));
        }
    }
    //是否必选
    for (var i = 0; i < modelMustbeinit.length; i++) {
        if (modelMustbeinit[i].k == re.data.model.required) {
            $('#modelMustbedetail').append($('<option value ="' + modelMustbeinit[i].k + '">' + modelMustbeinit[i].val + '</option>'));
        }
    }
    //状态
    for (var i = 0; i < modelStateinit.length; i++) {
        if (modelStateinit[i].k == re.data.model.openCloseState) {
            $('#modelStatedetail').append($('<option value ="' + modelStateinit[i].k + '">' + modelStateinit[i].val + '</option>'));
        }
    }
}
//清空编辑
function myclear2() {
    $('#categoryupdate').empty();
    $('#applicablegradeupdate').empty();
    $('#modelLevelupdate').empty();
    $('#modelMustbeupdate').empty();
    $('#modelStateupdate').empty();
}
function mydisplay2(re) {
    //适用年级
    for (var i = 0; i < applicablegradeinit.length; i++) {
        if (applicablegradeinit[i].k == re.data.model.applicablegrade) {
            $('#applicablegradeupdate').append($('<option value ="' + applicablegradeinit[i].k + '">' + applicablegradeinit[i].val + '</option>'));
        }
    }
    //类型
    for (var i = 0; i < categoryinit.length; i++) {
        if (categoryinit[i].k == re.data.model.category) {
            $('#categoryupdate').append($('<option value ="' + categoryinit[i].k + '">' + categoryinit[i].val + '</option>'));
            console.log(categoryinit[i].val)
        }
    }
    //级别
    for (var i = 0; i < modelLevelinit.length; i++) {
        if (modelLevelinit[i].k == re.data.model.modellevel) {
            $('#modelLevelupdate').append($('<option value ="' + modelLevelinit[i].k + '">' + modelLevelinit[i].val + '</option>'));
        }
    }
    //是否必选
    for (var i = 0; i < modelMustbeinit.length; i++) {
        if (modelMustbeinit[i].k == re.data.model.required) {
            $('#modelMustbeupdate').append($('<option value ="' + modelMustbeinit[i].k + '">' + modelMustbeinit[i].val + '</option>'));
        }
    }
    //状态
    for (var i = 0; i < modelStateinit.length; i++) {
        if (modelStateinit[i].k == re.data.model.openCloseState) {
            $('#modelStateupdate').append($('<option value ="' + modelStateinit[i].k + '">' + modelStateinit[i].val + '</option>'));
        }
    }
}
