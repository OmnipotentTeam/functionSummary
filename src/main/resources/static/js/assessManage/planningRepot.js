/**
 * Created by Administrator on 2018/12/7.
 */
$(function () {
//专业字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl + "/system/selectMajor",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            // console.log(res);
            for (var i = 0; i < res.data.length; i++) {
                var majorName = res.data[i].majorName;
                var majorId = res.data[i].majorId;
                $('#majorId').append($('<option value ="' + majorId + '">' + majorName + '</option>'));
            }
        },
        error: function () {
            layer.msg('专业查询失败', {icon: 2, time: 1000});
        }
    });

    //初始化表格
    var oTable = new TableInit();
    oTable.Init();
});
var stu;//创建全局，接收学生信息
// 生成首页表格
function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#evaluationQuery').bootstrapTable({
            url: httpUrl + "/mark/selectMarkGrade",         //请求后台的URL（*）
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
                    field: 'studentId',
                    title: '序号',
                    formatter: function (value, row, index) {
                        return index + 1;
                    },
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'startSchool',
                    title: '学级',
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'studentName',
                    title: '姓名',
                    align: 'center',
                    colspan: 1,

                },
                {
                    field: 'major',
                    title: '专业',
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'studentNo',
                    title: '学号',
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'studentPhoneNumber',
                    title: '手机号',
                    align: 'center',
                    colspan: 1,

                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    formatter: addFunctionAlty//表格中增加按钮
                }
            ],
            responseHandler: function (res) {  //后台返回的结果
                console.log(res)
                stu = res;
                if (res.data != null) {
                    var markInfo = res.data.list;
                    var NewData = [];
                    if (markInfo.length) {
                        for (var i = 0; i < markInfo.length; i++) {
                            var dataNewObj = {
                                "gradeEvaluationId": '',
                                "studentId": '',
                                "markId": '',
                                "studentGrade": '',
                                'major': '',
                                "studentNo": '',
                                "studentName": '',
                                'evaluationName': '',
                                'modifyTime': ''
                            };
                            dataNewObj.gradeEvaluationId = markInfo[i].gradeEvaluationId;
                            dataNewObj.studentId = markInfo[i].studentId;
                            dataNewObj.markId = markInfo[i].markId;
                            dataNewObj.studentPhoneNumber = markInfo[i].studentEntity[0].studentPhoneNumber;
                            dataNewObj.startSchool = markInfo[i].studentEntity[0].startSchool;

                            var getGrade=markInfo[i].studentEntity[0].studentGrade;
                            // var getGrade = markInfo[i].gradeEvaluationEntity[0].questionBankEntity[0].questionBankStage;  //把学生学年变成了测评题学年
                            if (getGrade == 1) {
                                dataNewObj.studentGrade = "大学一年级";
                            } else if (getGrade == 2) {
                                dataNewObj.studentGrade = "大学二年级";
                            } else if (getGrade == 3) {
                                dataNewObj.studentGrade = "大学三年级";
                            }
                            if (markInfo[i].studentEntity[0].majorEntity.length != 0) {
                                dataNewObj.major = markInfo[i].studentEntity[0].majorEntity[0].majorName;
                                dataNewObj.studentNo = markInfo[i].studentEntity[0].studentNo;
                                dataNewObj.studentName = markInfo[i].studentEntity[0].studentName;
                                // dataNewObj.evaluationName = markInfo[i].gradeEvaluationEntity[0].evaluationName;
                                dataNewObj.modifyTime = markInfo[i].modifyTime;
                                NewData.push(dataNewObj);
                            }
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
    // 生成首页表格时得到查询的参数（需要传给后台的参数）
    function queryParams(params) {
        var studentNo = $("#studentNo").val();
        var studentName = $("#studentName").val();
        var studentPhoneNumber = $("#search-studentPhoneNumber").val();
        var startSchool = $("#startSchool").val();
        var majorId = $("#majorId").val() == "0" ? "" : $("#majorId").val();
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,
            pageSize: params.pageSize,
            studentNo: studentNo,
            studentName: studentName,
            studentPhoneNumber: studentPhoneNumber,//手机号
            startSchool: startSchool,//学级
            majorId: majorId
        };
        return JSON.stringify(temp);
    }

    return oTableInit;
}



function search() {
    $("#evaluationQuery").bootstrapTable('refresh');
}
// 表格添加操作按钮
function addFunctionAlty(value, row, index) {
    var markInfo = stu.data.list;
    // console.log(markInfo);
    var btnText = '';
    if (row.studentGrade == "大学一年级") {
        btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
        btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn jinyong layui-btn-xs btn-jinyong2\"onclick=\"evaluation()\" value=\"大二年级\" disabled=\"disabled\">";
        btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong\"   onclick=\"evaluation()\" value=\"大三年级\" disabled=\"disabled\">";
        btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  jinyong layui-btn-xs btn-jinyong3\"   onclick=\"evaluation()\" value=\"综合成绩\" disabled=\"disabled\">";
    } else if (row.studentGrade == "大学二年级") {
        if(markInfo[index].marklist.length<2){
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation3()\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong btn-dasan\"   onclick=\"evaluation()\" value=\"大三年级\" disabled=\"disabled\">";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong3\"   onclick=\"evaluation()\" value=\"综合成绩\" disabled=\"disabled\">";
        }else {
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + ","+"'大学二年级'" +")\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong btn-dasan\"   onclick=\"evaluation()\" value=\"大三年级\" disabled=\"disabled\">";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong3\"   onclick=\"evaluation()\" value=\"综合成绩\" disabled=\"disabled\">";
        }
    } else if (row.studentGrade == "大学三年级") {
        if(markInfo[index].marklist.length < 2){
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation3()\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn   layui-btn-xs  btn-dasan\"   onclick=\"evaluation3()\" value=\"大三年级\" >";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn   layui-btn-xs btn-zong\"   onclick=\"evaluation3()\" value=\"综合成绩\" >";
        }else if (markInfo[index].marklist.length == 2){
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'大学一年级'" + ")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'大学二年级'" + ")\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dasan\"   onclick=\"evaluation3()\" value=\"大三年级\" >";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-zong\"   onclick=\"evaluation3()\" value=\"综合成绩\" >";
        }else {
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'大学一年级'" + ")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'大学二年级'" + ")\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dasan\"   onclick=\"evaluation(" + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'大学三年级'" + ")\" value=\"大三年级\" >";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-zong\"   onclick=\"evaluation2(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'综合成绩'" + ")\" value=\"综合成绩\" >";
        }
    }else {
        if(markInfo[index].marklist.length < 2){
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation3()\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn   layui-btn-xs  btn-dasan\"   onclick=\"evaluation3()\" value=\"大三年级\" >";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn   layui-btn-xs btn-zong\"   onclick=\"evaluation3()\" value=\"综合成绩\" >";
        }else if (markInfo[index].marklist.length == 2){
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'大学一年级'" + ")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'大学二年级'" + ")\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dasan\"   onclick=\"evaluation3()\" value=\"大三年级\" >";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-zong\"   onclick=\"evaluation3()\" value=\"综合成绩\" >";
        }else {
            btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'大学一年级'" + ")\" value=\"大一年级\">";
            btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'大学二年级'" + ")\" value=\"大二年级\">";
            btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dasan\"   onclick=\"evaluation(" + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'大学三年级'" + ")\" value=\"大三年级\" >";
            btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-zong\"   onclick=\"evaluation2(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'综合成绩'" + ")\" value=\"综合成绩\" >";
        }
    }


    // if (row.studentGrade == "大学一年级") {
    //     btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
    //     btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn jinyong layui-btn-xs btn-jinyong2\"onclick=\"evaluation(" + "'" + row.markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + row.gradeEvaluationId + "'" + ")\" value=\"大二年级\" disabled=\"disabled\">";
    //     btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong\"   onclick=\"evaluation(" + "'" + row.markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + row.gradeEvaluationId + "'" + ")\" value=\"大三年级\" disabled=\"disabled\">";
    //     btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  jinyong layui-btn-xs btn-jinyong3\"   onclick=\"evaluation(" + "'" + row.markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + row.gradeEvaluationId + "'" + ")\" value=\"综合成绩\" disabled=\"disabled\">";
    // } else if (row.studentGrade == "大学二年级") {
    //     btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" +markInfo[index].marklist[0].gradeEvaluationId + "'" + ","+"'大学一年级'" +")\" value=\"大一年级\">";
    //     btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + ","+"'大学二年级'" +")\" value=\"大二年级\">";
    //     btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong btn-dasan\"   onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + ","+"'大学三年级'" +")\" value=\"大三年级\" disabled=\"disabled\">";
    //     btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn jinyong  layui-btn-xs btn-jinyong3\"   onclick=\"evaluation(" + "'" + row.markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + row.gradeEvaluationId + "'" +  ","+"'"+ row.studentGrade + "'" +")\" value=\"综合成绩\" disabled=\"disabled\">";
    // } else if (row.studentGrade == "大学三年级") {
    //     btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'大学一年级'" + ")\" value=\"大一年级\">";
    //     btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'大学二年级'" + ")\" value=\"大二年级\">";
    //     btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dasan\"   onclick=\"evaluation(" + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'大学三年级'" + ")\" value=\"大三年级\" >";
    //     btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-zong\"   onclick=\"evaluation2(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'综合成绩'" + ")\" value=\"综合成绩\" >";
    // }else {
    //     btnText += "<input type=\"button\" id='dayi' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dayi\" onclick=\"evaluation(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'大学一年级'" + ")\" value=\"大一年级\">";
    //     btnText += "<input type=\"button\" id='daer' data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-daer\"onclick=\"evaluation(" + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'大学二年级'" + ")\" value=\"大二年级\">";
    //     btnText += "<input type=\"button\" id='dasan' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-dasan\"   onclick=\"evaluation(" + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'大学三年级'" + ")\" value=\"大三年级\" >";
    //     btnText += "<input type=\"button\" id='zonghe' data-toggle=\"modal\" class=\"layui-btn  layui-btn-xs btn-zong\"   onclick=\"evaluation2(" + "'" + markInfo[index].marklist[0].markId + "'" + "," + "'" + markInfo[index].marklist[1].markId + "'" + "," + "'" + markInfo[index].marklist[2].markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + markInfo[index].marklist[0].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[1].gradeEvaluationId + "'" + "," + "'" + markInfo[index].marklist[2].gradeEvaluationId + "'" + "," + "'综合成绩'" + ")\" value=\"综合成绩\" >";
    //
    // }
    return btnText;
}
var dataA;
var dataD;
var getMarkId;
function evaluation3() {
    layer.alert('该学生未参加测评，暂无当前成绩');
    return;
}
// 大一大二大三的按钮功能实现
function evaluation(markId, studentId, gradeEvaluationId, Grade) {
        img(studentId)
        $("#evaluationQueryInfo").hide();
        $("#evaluationQueryStudent").show();
        $("#getGrade").text(Grade);//学年
        getMarkId = markId;

        var data = {
            markId: markId ,
            studentId: studentId,
            gradeEvaluationId: gradeEvaluationId

        };
        $.ajax({
            type: "POST",
            url: httpUrl + "/mark/selectNotMarkComment",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charest=utf-8",
            success: function (rs) {
                var getStudentInfo = rs.data[0].studentEntity[0];
                $("#getNo").text(getStudentInfo.studentNo);//学号
                $("#getName").text(getStudentInfo.studentName);//姓名
                //add bu fuqiang
                if (getStudentInfo.headImg != null && getStudentInfo.headImg != '') {
                    $("#evaluationhead").attr('src', getStudentInfo.headImgPath);
                }
                $("#getMajor").text(getStudentInfo.majorEntity[0].majorName);//专业
                //维度图
                var myChart = echarts.init(document.getElementById('echarts'));
                var list1 = [];
                var list2 = [];
                var dimensionInfo = rs.data[0].dimensionRecordEntity;
                for (var i = 0; i < dimensionInfo.length; i++) {
                    var a = {
                        text: '',
                        max: 100
                    };
                    if (dimensionInfo[i].dimensionEntity.length > 0) {
                        a.text = dimensionInfo[i].dimensionEntity[0].dimensionName;
                    }
                    list1.push(a);
                    list2.push(dimensionInfo[i].dimensionRecordNum)
                }
                option = {
                    title: {},
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {

                        x: 'center',
                    },
                    radar: [
                        {
                            indicator: list1,
                            center: ['50%', '50%'],
                            radius: 80
                        }
                    ],
                    series: [
                        {
                            name: '维度图',
                            type: 'radar',
                            tooltip: {
                                trigger: 'item'
                            },
                            itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: [
                                {
                                    value: list2
                                }
                            ]
                        }
                    ]
                };
                myChart.setOption(option);

            }, error: function () {

                layer.msg('网络异常请重试', {icon: 2, time: 1000});
            }
        })
        //获取活动记录表格数据
        var mGrade;
        if (Grade == "大学一年级") {
            mGrade = 1;
        } else if (Grade == "大学二年级") {
            mGrade = 2;
        } else if (Grade == "大学三年级") {
            mGrade = 3;
        }
        dataD = {
            studentId: studentId,
            studenGrade: '5' + mGrade
        };
        //大一大二大三分别的综合数据表第一页
        $('#evaluationQuery2').bootstrapTable({
            url: httpUrl + "/growth/queryreport",         //请求后台的URL（*）
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
            queryParams: queryParams2,//请求服务器时所传的参数
            sidePagination: 'server',//指定服务器端分页
            pageSize: 10,//单页记录数
            pageList: [8, 16, 24, 32],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            columns: [
                {
                    field: 'studentId',
                    title: '序号',
                    formatter: function (value, row, index) {
                        return index + 1;
                    },
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'modelTitle',
                    title: '名称',
                    align: 'center',
                    colspan: 1,

                },
                {
                    field: 'optionOrder',
                    title: '级别',
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'optionComment',
                    title: '等级及备注',
                    align: 'center',
                    colspan: 1,
                },
            ],
            responseHandler: function (res) {  //后台返回的结果
                var NewData = [];
                if (res.data != null && res.data.length != 0) {
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                    var long = markInfo.length;
                    if (long <= 8) {
                        //获取div宽度
                        var divWidth = $('#growthreportcomment').width();
                        //设置div高度为a4等比高度
                        var divHeight = divWidth / 595.28 * 841.89;
                        $("#growthreportcomment").css("height", divHeight);
                        $("#growthreport2").hide();
                        for (var i = 0; i < long; i++) {
                            var dataNewObj = {
                                "modelTitle": '',
                                "optionOrder": '',
                                "optionComment": '',
                            };
                            dataNewObj.modelTitle = markInfo[i].modelTitle;
                            dataNewObj.optionOrder = markInfo[i].optionOrder;
                            dataNewObj.optionComment = markInfo[i].optionComment;
                            if (markInfo.length != 0) {
                                NewData.push(dataNewObj);
                            }

                        }
                    }
                    if (long > 8) {
                        //获取div宽度
                        var divWidth = $('#growthreportcomment').width();
                        //设置div高度为a4等比高度
                        var divHeight = divWidth / 595.28 * 841.89;
                        var topHeight = $('#con_top').height();
                        //求出差值
                        var height = divHeight - topHeight;
                        $("#growthreportcomment").css("height", 2 * divHeight);
                        $("#growthreport2").css("margin-top", height - 100);
                        $("#growthreport2").show();
                        for (var i = 0; i < 9; i++) {
                            var dataNewObj = {
                                "modelTitle": '',
                                "optionOrder": '',
                                "optionComment": '',
                            };
                            dataNewObj.modelTitle = markInfo[i].modelTitle;
                            dataNewObj.optionOrder = markInfo[i].optionOrder;
                            dataNewObj.optionComment = markInfo[i].optionComment;
                            if (markInfo.length != 0) {
                                NewData.push(dataNewObj);
                            }
                        }
                    }
                    var data = {
                        total: 0,
                        // total: res.data.total,
                        rows: NewData
                    };
                    return data;
                } else {
                    //获取div宽度
                    var divWidth = $('#growthreportcomment').width();
                    //设置div高度为a4等比高度
                    var divHeight = divWidth / 595.28 * 841.89;
                    $("#growthreportcomment").css("height", divHeight);
                    $("#growthreport2").hide();
                    var data = {
                        total: 0,
                        rows: NewData
                    };
                    return data;
                }
            }
        });
        //大一大二大三分别的综合数据表第二页
        $('#evaluationQuery3').bootstrapTable({
            url: httpUrl + "/growth/queryreport",         //请求后台的URL（*）
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
            queryParams: queryParams2,//请求服务器时所传的参数
            sidePagination: 'server',//指定服务器端分页
            pageSize: 30,//单页记录数
            pageList: [8, 16, 24, 32],//分页步进值
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            columns: [
                {
                    field: 'studentId',
                    title: '序号',
                    formatter: function (value, row, index) {
                        return index + 9;
                    },
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'modelTitle',
                    title: '名称',
                    align: 'center',
                    colspan: 1,

                },
                {
                    field: 'optionOrder',
                    title: '级别',
                    align: 'center',
                    colspan: 1,
                },
                {
                    field: 'optionComment',
                    title: '等级及备注',
                    align: 'center',
                    colspan: 1,
                },
            ],
            responseHandler: function (res) {  //后台返回的结果
                if (res.data != null && res.data.length != 0) {
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                    var NewData = [];
                    if (markInfo.length) {
                        for (var i = 8; i < markInfo.length; i++) {
                            var dataNewObj = {
                                "modelTitle": '',
                                "optionOrder": '',
                                "optionComment": '',
                            };
                            dataNewObj.modelTitle = markInfo[i].modelTitle;
                            dataNewObj.optionOrder = markInfo[i].optionOrder;
                            dataNewObj.optionComment = markInfo[i].optionComment;
                            if (markInfo.length != 0) {
                                NewData.push(dataNewObj);
                            }

                        }
                    }
                    var data = {
                        // total: res.data.total,
                        total: 0,
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

}

// 大一大二大三得到查询的参数（需要传给后台的参数）
function queryParams2() {
    var temp = dataD
    return JSON.stringify(temp);
}

// 综合查询的参数（需要传给后台的参数）
function queryParams3() {
    var temp = dataA
    return JSON.stringify(temp);
}

// 综合按钮的功能
function evaluation2(markId1, markId2, markId, studentId, gradeEvaluationId1, gradeEvaluationId2, gradeEvaluationId, Grade) {
    $("#evaluationQueryInfo").hide();
    $("#evaluationQueryStudent").show();
    $("#getGrade").text(Grade);
    getMarkId = markId;
    //定义数据数组
    var list1 = [];
    var list2 = [];
    var list3 = [];
    var list4 = [];
    var list5 = [];
    var list6 = [];
    // 分别获取大一大二大三的数据
    //大一数据
    var data1 = {
        markId: markId1,
        studentId: studentId,
        gradeEvaluationId: gradeEvaluationId1

    };
    $.ajax({
        type: "POST",
        url: httpUrl + "/mark/selectNotMarkComment",
        data: JSON.stringify(data1),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            var dimensionInfo = rs.data[0].dimensionRecordEntity;
            for (var i = 0; i < dimensionInfo.length; i++) {
                var a = {
                    text: '',
                    max: 100
                };
                if (dimensionInfo[i].dimensionEntity.length > 0) {
                    a.text = dimensionInfo[i].dimensionEntity[0].dimensionName;
                }
                list1.push(a);
                list2.push(dimensionInfo[i].dimensionRecordNum)
            }
        }, error: function () {

            layer.msg('网络异常请重试', {icon: 2, time: 1000});
        }
    });
    //大二数据
    var data2 = {
        markId: markId2,
        studentId: studentId,
        gradeEvaluationId: gradeEvaluationId2

    };
    $.ajax({
        type: "POST",
        url: httpUrl + "/mark/selectNotMarkComment",
        data: JSON.stringify(data2),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            var dimensionInfo = rs.data[0].dimensionRecordEntity;
            for (var i = 0; i < dimensionInfo.length; i++) {
                var a = {
                    text: '',
                    max: 100
                };
                if (dimensionInfo[i].dimensionEntity.length > 0) {
                    a.text = dimensionInfo[i].dimensionEntity[0].dimensionName;
                }
                list3.push(a);
                list4.push(dimensionInfo[i].dimensionRecordNum);
                //大三数据
                $.ajax({
                    type: "POST",
                    url: httpUrl + "/mark/selectNotMarkComment",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType: "application/json;charest=utf-8",
                    success: function (rs) {
                        var getStudentInfo = rs.data[0].studentEntity[0];
                        $("#getNo").text(getStudentInfo.studentNo);
                        $("#getName").text(getStudentInfo.studentName);

                        //add bu fuqiang
                        if (getStudentInfo.headImg != null && getStudentInfo.headImg != '') {
                            $("#evaluationhead").attr('src', getStudentInfo.headImgPath);
                        }

                        var Grade = "";
                        if (getStudentInfo.studentGrade == 1) {
                            Grade = "第一学年";
                        } else if (getStudentInfo.studentGrade == 2) {
                            Grade = "第二学年";
                        } else if (getStudentInfo.studentGrade == 3) {
                            Grade = "第三学年";
                        } else {
                            Grade = "已毕业"
                        }
                        $("#getMajor").text(getStudentInfo.majorEntity[0].majorName);
                        var myChart = echarts.init(document.getElementById('echarts'));
                        var dimensionInfo = rs.data[0].dimensionRecordEntity;
                        for (var i = 0; i < dimensionInfo.length; i++) {
                            var a = {
                                text: '',
                                max: 100
                            };
                            if (dimensionInfo[i].dimensionEntity.length > 0) {
                                a.text = dimensionInfo[i].dimensionEntity[0].dimensionName;
                            }
                            list5.push(a);
                            list6.push(dimensionInfo[i].dimensionRecordNum)
                        }
                        option = {
                            title: {},
                            tooltip: {
                                trigger: 'axis'
                            },
                            legend: {

                                x: 'center',
                            },
                            radar: [
                                {
                                    indicator: [
                                        {name: list1[0].text, max: 100},
                                        {name: list1[1].text, max: 100},
                                        {name: list1[2].text, max: 100},
                                        {name: list3[0].text, max: 100},
                                        {name: list3[1].text, max: 100},
                                        {name: list3[2].text, max: 100},
                                        {name: list5[0].text, max: 100},
                                        {name: list5[1].text, max: 100},
                                        {name: list5[2].text, max: 100},
                                    ],
                                    center: ['50%', '50%'],
                                    radius: 80
                                }
                            ],
                            series: [
                                {
                                    name: '综合能力维度图',
                                    type: 'radar',
                                    tooltip: {
                                        trigger: 'item'
                                    },
                                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                                    data: [
                                        {
                                            value: [
                                                list2[0],
                                                list2[1],
                                                list2[2],
                                                list4[0],
                                                list4[1],
                                                list4[2],
                                                list6[0],
                                                list6[1],
                                                list6[2],
                                            ],
                                        }
                                    ]
                                }
                            ]
                        };
                        myChart.setOption(option);
                    }, error: function () {
                        layer.msg('网络异常请重试', {icon: 2, time: 1000});
                    }
                })
            }
        }, error: function () {
            layer.msg('网络异常请重试', {icon: 2, time: 1000});
        }
    })
    //大三数据
    var data = {
        markId: markId,
        studentId: studentId,
        gradeEvaluationId: gradeEvaluationId
    };
    //综合数据表
    dataA = {
        studentId: studentId,
    };
    //综合数据表第一页
    $('#evaluationQuery2').bootstrapTable({
        url: httpUrl + "/growth/queryreport",         //请求后台的URL（*）
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
        queryParams: queryParams3,//请求服务器时所传的参数
        sidePagination: 'server',//指定服务器端分页
        pageSize: 10,//单页记录数
        pageList: [8, 16, 24, 32],//分页步进值
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        silent: true,
        showRefresh: false,                  //是否显示刷新按钮
        showToggle: false,
        minimumCountColumns: 2,             //最少允许的列数
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'studentId',
                title: '序号',
                formatter: function (value, row, index) {
                    return index + 1;
                },
                align: 'center',
                colspan: 1,
            },
            {
                field: 'modelTitle',
                title: '名称',
                align: 'center',
                colspan: 1,

            },
            {
                field: 'optionOrder',
                title: '级别',
                align: 'center',
                colspan: 1,
            },
            {
                field: 'optionComment',
                title: '等级及备注',
                align: 'center',
                colspan: 1,
            },
        ],
        responseHandler: function (res) {  //后台返回的结果
            console.log(res)
            var NewData = [];
            if (res.data != null && res.data.length != 0) {
                console.log(res.data)
                if(res.data.length == 1){
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                }else if(res.data.length == 2){
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                    var markInfo1 = res.data[1].growthPlanningCommentEntity;
                    for( var i in markInfo1)
                    {
                        markInfo.push(markInfo1[i]);
                    }
                }
            else if(res.data.length == 3){
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                    var markInfo1 = res.data[1].growthPlanningCommentEntity;
                    var markInfo2 = res.data[2].growthPlanningCommentEntity;
                    for( var i in markInfo1)
                    {
                        markInfo.push(markInfo1[i]);
                    }
                    for (var i in markInfo2) {
                        markInfo.push(markInfo2[i]);
                    }
                }


                var long = markInfo.length;
                if (long <= 8) {
                    //获取div宽度
                    var divWidth = $('#growthreportcomment').width();
                    //设置div高度为a4等比高度
                    var divHeight = divWidth / 595.28 * 841.89;
                    $("#growthreportcomment").css("height", divHeight);
                    $("#growthreport2").hide();
                    for (var i = 0; i < long; i++) {
                        var dataNewObj = {
                            "modelTitle": '',
                            "optionOrder": '',
                            "optionComment": '',
                        };
                        dataNewObj.modelTitle = markInfo[i].modelTitle;
                        dataNewObj.optionOrder = markInfo[i].optionOrder;
                        dataNewObj.optionComment = markInfo[i].optionComment;
                        if (markInfo.length != 0) {
                            NewData.push(dataNewObj);
                        }

                    }
                }
                if (long > 8) {
                    //获取div宽度
                    var divWidth = $('#growthreportcomment').width();
                    //设置div高度为a4等比高度
                    var divHeight = divWidth / 595.28 * 841.89;
                    var topHeight = $('#con_top').height();
                    //求出差值
                    var height = divHeight - topHeight;
                    $("#growthreportcomment").css("height", 2 * divHeight);
                    $("#growthreport2").css("margin-top", height - 100);
                    $("#growthreport2").show();
                    for (var i = 0; i < 8; i++) {
                        var dataNewObj = {
                            "modelTitle": '',
                            "optionOrder": '',
                            "optionComment": '',
                        };
                        dataNewObj.modelTitle = markInfo[i].modelTitle;
                        dataNewObj.optionOrder = markInfo[i].optionOrder;
                        dataNewObj.optionComment = markInfo[i].optionComment;
                        if (markInfo.length != 0) {
                            NewData.push(dataNewObj);
                        }
                    }
                }
                var data = {
                    total: 0,
                    // total: res.data.total,
                    rows: NewData
                };
                return data;
            } else {
                //获取div宽度
                var divWidth = $('#growthreportcomment').width();
                //设置div高度为a4等比高度
                var divHeight = divWidth / 595.28 * 841.89;
                $("#growthreportcomment").css("height", divHeight);
                $("#growthreport2").hide();
                var data = {
                    total: 0,
                    rows: NewData
                };
                return data;
            }
        }
    });
    //综合数据表第二页
    $('#evaluationQuery3').bootstrapTable({
        url: httpUrl + "/growth/queryreport",         //请求后台的URL（*）
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
        queryParams: queryParams3,//请求服务器时所传的参数
        sidePagination: 'server',//指定服务器端分页
        pageSize: 30,//单页记录数
        pageList: [8, 16, 24, 32],//分页步进值
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        silent: true,
        showRefresh: false,                  //是否显示刷新按钮
        showToggle: false,
        minimumCountColumns: 2,             //最少允许的列数
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [
            {
                field: 'studentId',
                title: '序号',
                formatter: function (value, row, index) {
                    return index + 9;
                },
                align: 'center',
                colspan: 1,
            },
            {
                field: 'modelTitle',
                title: '名称',
                align: 'center',
                colspan: 1,
            },
            {
                field: 'optionOrder',
                title: '级别',
                align: 'center',
                colspan: 1,
            },
            {
                field: 'optionComment',
                title: '等级及备注',
                align: 'center',
                colspan: 1,
            },
        ],
        responseHandler: function (res) {  //后台返回的结果
            stu = res;
            if (res.data != null && res.data.length != 0) {
                if(res.data.length == 1){
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                }else if(res.data.length == 2){
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                    var markInfo1 = res.data[1].growthPlanningCommentEntity;
                    for( var i in markInfo1)
                    {
                        markInfo.push(markInfo1[i]);
                    }
                } else if(res.data.length == 3){
                    var markInfo = res.data[0].growthPlanningCommentEntity;
                    var markInfo1 = res.data[1].growthPlanningCommentEntity;
                    var markInfo2 = res.data[2].growthPlanningCommentEntity;
                    for( var i in markInfo1)
                    {
                        markInfo.push(markInfo1[i]);
                    }
                    for (var i in markInfo2) {
                        markInfo.push(markInfo2[i]);
                    }
                }

                var NewData = [];
                if (markInfo.length) {
                    for (var i = 8; i < markInfo.length; i++) {
                        var dataNewObj = {
                            "modelTitle": '',
                            "optionOrder": '',
                            "optionComment": '',
                        };
                        dataNewObj.modelTitle = markInfo[i].modelTitle;
                        dataNewObj.optionOrder = markInfo[i].optionOrder;
                        dataNewObj.optionComment = markInfo[i].optionComment;
                        if (markInfo.length != 0) {
                            NewData.push(dataNewObj);
                        }

                    }
                }
                var data = {
                    // total: res.data.total,
                    total: 0,
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
}

//返回
function comeBack() {
    $("#evaluationQueryInfo").show();
    $("#evaluationQueryStudent").hide();
    $("#getTest").empty();
    $("#evaluationreviewhead").attr('src', '');
    location.reload();//刷新表格数据
}

// 当浏览器大小变化时
$(window).resize(function () {
    //获取div宽度
    var divWidth = $('#growthreportcomment').width();
    //设置div高度为a4等比高度
    var divHeight = divWidth / 595.28 * 841.89;
    //获取实际内容高度
    var conHeight = $('#con').height();
    $("#growthreportcomment").css("height", 2 * divHeight);
    if (conHeight - 12 <= divHeight) {
        $("#growthreportcomment").css("height", divHeight);
    } else if (conHeight - 12 > divHeight) {
        $("#growthreportcomment").css("height", 2 * divHeight);
    }
});

//下载pdf
function downLoad() {
    html2canvas(document.getElementById("growthreportcomment"), {

        onrendered:function(canvas) {

            var contentWidth = canvas.width;
            var contentHeight = canvas.height;

            //一页pdf显示html页面生成的canvas高度;
            var pageHeight = contentWidth / 595.28 * 841.89;
            //未生成pdf的html页面高度
            var leftHeight = contentHeight;
            //pdf页面偏移
            var position = 0;
            //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
            var imgWidth = 595.28;
            var imgHeight = 595.28 / contentWidth * contentHeight;
            var pageData = canvas.toDataURL('image/jpeg', 1.0);

            var pdf = new jsPDF('', 'pt', 'a4');
            //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
            //当内容未超过pdf一页显示的范围，无需分页
            if (leftHeight < pageHeight) {
                pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
            } else {
                while (leftHeight > 0) {
                    pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                    leftHeight -= pageHeight;
                    position -= 841.89;
                    //避免添加空白页
                    if(leftHeight > 0) {
                        pdf.addPage();
                    }
                }
            }

            pdf.save('个人成绩单.pdf');
        }
    })

}

// 获取图片
function img(StudentId) {
    var data = {
        studentId: StudentId
    };
    $.ajax({
        type: "POST",
        url: httpUrl + "/student/selectStudent",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            console.log(rs.data.headImgPath);
            if(rs.data.headImg!= null && rs.data.headImg!=''){
                // 图片转base64
                var imgSrc = rs.data.headImgPath;
                //width、height调用时传入具体像素值，控制大小 ,不传则默认图像大小
                function getBase64Image(img, width, height) {
                    var canvas = document.createElement("canvas");
                    canvas.width = width ? width : img.width;
                    canvas.height = height ? height : img.height;
                    var ctx = canvas.getContext("2d");
                    ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
                    var dataURL = canvas.toDataURL();
                    return dataURL;
                }
                function getCanvasBase64(img) {
                    var image = new Image();
                    image.crossOrigin = '';
                    image.src = img;
                    var deferred = $.Deferred();
                    if (img) {
                        image.onload = function () {
                            deferred.resolve(getBase64Image(image));//将base64传给done上传处理
                        }
                        return deferred.promise();//问题要让onload完成后再return sessionStorage['imgTest']
                    }
                }
                getCanvasBase64(imgSrc)
                    .then(function (base64) {
                        $("#develophead").attr('src',base64);
                        $("#zhaopian").hide();
                    }, function (err) {
                        console.log(err);
                    });
            }else{
                $("#develophead").hide();
                $("#kuang").css("padding-top","4%");
            }
        }
    });
}
// function submitreportcomment(grade) {
//     var reportcomment = $("#reportcomment").val();
//     var data = {
//         planningId: planningId,
//         commentComtent: reportcomment
//     };
//     //console.log(data);
//     $.ajax({
//         type: "POST",
//         url: httpUrl + "/growth/addcomment",
//         data: JSON.stringify(data),
//         dataType: "json",
//         contentType: "application/json;charest=utf-8",
//         success: function (rs) {
//             // console.log(rs);
//             if (rs.code == 10000) {
//                 $("#reportcomment").val("");
//                 gradeEvaluation("grade" + grade);
//             }
//         }
//     });
// }