/**
 * Created by chen on 2018/12/7.
 */
var times;
var date ;
var myDate ;
var  endtime ;
$(function () {
    //发布人字典表
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"grade/selectSysUser",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            for (var i = 0; i < res.data.length; i++) {
                var username=res.data[i].username;
                // var item = res.data[i].id;
                $('#username').append($('<option value ="' + username + '">' + username + '</option>'));
            }

        },
        error: function () {
            layer.msg('发布人查询失败', {icon: 2, time: 1000});
        }
    });
    getQuestion(1);
    $('#grades').text("大学一年级");
    $('#evaluationTypes').text("入学测评");
    var oTable = new TableInit();
    oTable.Init();
})
//获取对应学年题库问题
function getQuestion(e) {
    var js={
        questionBankStage:e
    }
    //试题名称字典表
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"grade/selectQuestion",//地址
        data: JSON.stringify(js),//传送的数据
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {

            for (var i = 0; i < res.data.length; i++) {
                var questionBankName=res.data[i].questionBankName;
                var item = res.data[i].questionBankId;
                $('#questionBankNames').append($('<option value ="' + item + '">' + questionBankName + '</option>'));
            }
        },
        error: function () {

            layer.msg('试题查询失败', {icon: 2, time: 1000});
        }
    });
}


var  grade=1;
function gradeEvaluation(e) {

    var obj1 = document.getElementById("grade1");
    var obj2 = document.getElementById("grade2");
    var obj3 = document.getElementById("grade3");
    grade = e.replace(/[^0-9]/ig,"");
    document.getElementById("questionBankNames").options.length = 1;
    // $('#navigation').show();
    if(grade==1){
        $('#searchBt1').show();
        $('#searchBt2').hide();
        $('#searchBt3').hide();
        obj1.style.backgroundColor= "#CEEBE9";
        obj2.style.backgroundColor= "#F7F7F7";
        obj3.style.backgroundColor= "#F7F7F7";
        $('#evaluationTypes').text("入学测评");
        $('#grades').text("大学一年级");
        getQuestion(1);
    } if(grade==2){
        $('#searchBt2').show();
        $('#searchBt1').hide();
        $('#searchBt3').hide();
        obj2.style.backgroundColor= "#CEEBE9";
        obj1.style.backgroundColor= "#F7F7F7";
        obj3.style.backgroundColor= "#F7F7F7";
        $('#evaluationTypes').text("阶段测评");
        $('#grades').text("大学二年级");
        getQuestion(2);
    } if(grade==3){
        $('#searchBt3').show();
        $('#searchBt2').hide();
        $('#searchBt1').hide();
        obj3.style.backgroundColor= "#CEEBE9";
        obj2.style.backgroundColor= "#F7F7F7";
        obj1.style.backgroundColor= "#F7F7F7";
        $('#evaluationTypes').text("阶段测评");
        $('#grades').text("大学三年级");
        getQuestion(3);
    }
    refreshTable();
}
//加载表格
function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#grade').bootstrapTable({
            url: httpUrl+"grade/selectGradeEvaluation",         //请求后台的URL（*）
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
            pageList: [10, 20, 30, 40],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            columns: [
                {
                    field: 'gradeEvaluationId',
                    title: '序号',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                {
                    field: 'evaluationName',
                    title: '测评名称',
                    align: 'center'
                },
                {
                    field: 'evaluationType',
                    title: '测评类型',
                    align: 'center'
                },
                {
                    field: 'questionBankName',
                    title: '试题名称',
                    align: 'center'
                },
                {
                    field: 'questionBankFilePath',
                    title: '文件',
                    align: 'center'
                },
                {
                    field: 'username',
                    title: '发布人',
                    align: 'center'
                },
                {
                    field: 'modifyTime',
                    title: '发布日期',
                    align: 'center'
                },
                {
                    field: 'evaluationDate',
                    title: '截止时间',
                    align: 'center'
                },
                {
                    field: 'state',
                    title: '状态',
                    align: 'center'
                },
            ],
            responseHandler: function (res) {  //后台返回的结果

                if(res.code == "10000"){
                    var userInfo = res.data.list;
                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'gradeEvaluationId': '',
                                "evaluationName": '',
                                "evaluationType": '',
                                "questionBankName": '',
                                "questionBankFilePath": '',
                                "username": '',
                                "modifyTime": '',
                                "evaluationDate": '',
                                "state": ''
                            };
                            dataNewObj.gradeEvaluationId = userInfo[i].gradeEvaluationId;
                            dataNewObj.evaluationName = userInfo[i].evaluationName;
                            dataNewObj.evaluationType = getType(userInfo[i].evaluationType);
                            dataNewObj.questionBankName = userInfo[i].questionBankEntity[0].questionBankName;
                            dataNewObj.questionBankFilePath = downloadtext(userInfo[i].questionBankEntity[0].questionBankFilePath,userInfo[i].questionBankEntity[0].questionBankName);
                            dataNewObj.username = userInfo[i].sysUserEntity[0].username;
                            dataNewObj.modifyTime = userInfo[i].modifyTime;
                            dataNewObj.evaluationDate = userInfo[i].evaluationDate;
                            dataNewObj.state = getState(userInfo[i].evaluationDate);
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
        var evaluationName= $('#evaluationName').val();
        var evaluationType= $('#evaluationType').val();
        var questionBankName= $('#questionBankName').val();
        var username= $('#username').val();
        var state= $('#state').val();
        var temp = {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,                     //初始化加载第一页，默认第一页
            pageSize: params.pageSize,
            evaluationName:evaluationName,
            evaluationType:evaluationType,
            questionBankName:questionBankName,
            username:username,
            state:state,
            grade:grade
        };
        return JSON.stringify(temp);
    }
    return oTableInit;
}


function addModal() {
    $("#addModal").modal('show');
    refreshTable1();
    var oTable = new TableInit1();
    oTable.Init();
}

function TableInit1() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#unpublished').bootstrapTable({
            url: httpUrl+"grade/selectAllGradeEvaluationDraft",         //请求后台的URL（*）
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
            pageList: [10, 20, 30, 40],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            columns: [
                {
                    field: 'evaluationName',
                    title: '测评名称',
                    align: 'center'
                },
                {
                    field: 'evaluationType',
                    title: '测评类型',
                    align: 'center'
                },
                {
                    field: 'questionBankName',
                    title: '试题名称',
                    align: 'center'
                },
                {
                    field: 'questionBankFilePath',
                    title: '文件',
                    align: 'center'
                },
                {
                    field: 'evaluationDate',
                    title: '截止时间',
                    align: 'center'
                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    formatter: addFunctionAlty//表格中增加按钮
                }

            ],
            responseHandler: function (res) {  //后台返回的结果
                console.log(res);
                if(res.code == "10000"){
                    var userInfo = res.data.list;
                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'gradeEvaluationId': '',
                                "evaluationName": '',
                                "evaluationType": '',
                                "questionBankName": '',
                                "questionBankFilePath": '',
                                "evaluationDate": ''
                            };
                            dataNewObj.gradeEvaluationId = userInfo[i].gradeEvaluationId;
                            dataNewObj.evaluationName = userInfo[i].evaluationName;
                            dataNewObj.evaluationType = getType(userInfo[i].evaluationType);
                            dataNewObj.questionBankName = userInfo[i].questionBankEntity[0].questionBankName;
                            dataNewObj.questionBankFilePath = downloadtext(userInfo[i].questionBankEntity[0].questionBankFilePath,userInfo[i].questionBankEntity[0].questionBankName);
                            dataNewObj.evaluationDate = userInfo[i].evaluationDate;
                            NewData.push(dataNewObj);
                            // console.log(NewData[i].evaluationDate);
                            endtime = NewData[i].evaluationDate;
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
        var temp = {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,                     //初始化加载第一页，默认第一页
            pageSize: params.pageSize,
            grade:grade
        };
        return JSON.stringify(temp);
    }
    return oTableInit;
}
//表格内信息处理方法
function addFunctionAlty(value, row, index) {
    console.log(row);
    var btnText = '';
    btnText += "<input type=\"button\"  class=\"layui-btn layui-btn-sm btn-rel\" onclick=\"updateDraft(" +"'"+ row.gradeEvaluationId +"'" +","+"'"+row.evaluationDate+"'"+")\" value=\"发布\">";
    btnText += "<input type=\"button\"  class=\"layui-btn layui-btn-sm btn-del\" onclick=\"deleteDraft(" +"'"+ row.gradeEvaluationId +"'" +")\" value=\"删除\" >";
    return btnText;
}
//表格内信息处理方法
function downloadtext(path,name) {
    name=name+".xlsx";
    return [
        '<a href="'+pathUrl+path+'" download="'+name+'" style="color: #3276b1;">下载</a>'
    ].join('');
}
//表格内信息处理方法
function getState(time) {
    times = time;
     date = new Date();
     myDate = date.toISOString();

    if(times>myDate){
        return "进行中"
    }else{
        return "已结束"
    }
}
//表格内信息处理方法
function getType(type) {
    if(type==0){
        return "入学测评"
    }
    if(type==1){
        return "阶段测评"
    }
}

//刷新表格
function refreshTable() {
    $("#grade").bootstrapTable('refresh');
}
//刷新表格
function refreshTable1() {
    $("#unpublished").bootstrapTable('refresh');
}
var evaluationType;
//添加一个评测到草稿箱
function addGradeEvaluationDraft() {
    var evaluationName= $('#evaluationNames').val();
    var evaluationTypes= $('#evaluationTypes').text();
    var questionId= $('#questionBankNames').val();
    var evaluationDate=$('#evaluationDate').val();
    var grades = $('#grades').text();
    if(evaluationTypes=="入学测评"){
        evaluationType=0;
    }if(evaluationTypes=="阶段测评"){
        evaluationType=1;
    }if(grades=="大学一年级"){
        grade=1;
    }if(grades=="大学二年级"){
        grade=2;
    }if(grades=="大学三年级"){
        grade=3;
    }
    if(evaluationName==""){
        layer.alert("请填写测评名称")
    }
    else if(questionId=="0"){
        layer.alert("请选择试题")
    }
    else if(evaluationDate==""){
        layer.alert("请选择截止时间")
    }
    else{
        var userId = sessionStorage.getItem("userId");
        var js={
            evaluationName:evaluationName,
            evaluationType:evaluationType,
            questionId:questionId,
            evaluationDate:evaluationDate,
            userId:userId,
            grade:grade
        };
        console.log(js);
        $.ajax({
            type: "post",//发送
            url:  httpUrl+"grade/addGradeEvaluationDraft",//地址
            data: JSON.stringify(js),//传送的数据
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (res) {
                console.log(res);

                // if(res.code==10000){

                layer.msg('添加成功', {icon: 1, time: 1000});
                    empty();
                    refreshTable1();

                // }else{
                //     layer.alert('该试题已经添加过，且没有完成，请重新添加！');
                // }
                // setTimeout("javascript:location.href='gradeEvaluationManagement.html'", 1000);
            },
            error: function () {

                layer.msg('添加失败', {icon: 2, time: 1000});
            }
        })
    }
}

//发布评测草稿
function updateDraft(e,h) {
    // console.log(times);
    // console.log(myDate);
    // console.log(times>myDate);

    // console.log(e);
    // console.log(h);
    // if (h>myDate ){
    //     layer.alert('当前存在正在进行的测评');
    //     return false;
    // } else {
    var js={
        gradeEvaluationId:e
    };
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"grade/updateStatus",//地址
        data: JSON.stringify(js),//传送的数据
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
                console.log(res);
                if(res.data == 12345){
                    layer.alert('当前存在正在进行的测评');
                }
                else if (res.code == 10000) {
                    layer.msg('发布成功', {icon: 1, time: 1000});
                    refreshTable1();
                    $("#addModal").modal('hide');
                    refreshTable();
                    empty();
                } else {
                    layer.alert('修改状态失败！');
                }


        },
        error: function () {
            layer.msg('发布失败', {icon: 2, time: 1000});
        }
    })
    // }
}
//通过评测id删除评测草稿
function deleteDraft(e) {
    layer.open({
        content: "确认要删除吗?",
        btn:['确认','取消'],
        yes: function () {
            var js={
                gradeEvaluationId:e
            };
            $.ajax({
                type: "post",//发送
                url:  httpUrl+"grade/deleteGradeEvaluationDraft",//地址
                data: JSON.stringify(js),//传送的数据
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (res) {
                    if(res.code==10000){
                        layer.msg('删除成功!', {icon: 1, time: 1000});
                        refreshTable1();
                        // setTimeout("javascript:location.href='gradeEvaluationManagement.html'", 1000);
                    }
                },
                error: function () {

                    layer.msg('删除失败', {icon: 2, time: 1000});
                }
            })
        }
    })

}

function empty() {
    $('#evaluationNames').val("");
    $('#evaluationTypes').val("");
    $('#questionBankNames').val("0").trigger("change");
    $('#evaluationDate').val("");
    $('#grades').val("0");
}