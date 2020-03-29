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

function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#evaluationQuery').bootstrapTable({
            url: httpUrl + "/mark/selectNotMark",         //请求后台的URL（*）
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
                    field: 'studentGrade',
                    title: '学年',
                    align: 'center',
                    colspan: 1,
                }, {
                    field: 'major',
                    title: '专业',
                    align: 'center',
                    colspan: 1,
                }, {
                    field: 'studentNo',
                    title: '学号',
                    align: 'center',
                    colspan: 1,
                }, {
                    field: 'studentName',
                    title: '姓名',
                    align: 'center',
                    colspan: 1,

                }, {
                    field: 'evaluationName',
                    title: '评测名称',
                    align: 'center',
                    colspan: 1,
                }, {
                    field: 'modifyTime',
                    title: '提交时间',
                    align: 'center',
                    colspan: 1,
                }, {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    formatter: addFunctionAlty//表格中增加按钮
                }
            ],
            responseHandler: function (res) {  //后台返回的结果
                console.log(res);

                if (res.data != null) {
                    var markInfo = res.data.list;
                    var NewData = [];
                    if (markInfo.length) {
                        for (var i = 0; i < markInfo.length; i++) {
                            // var rname = "";
                            // if (userInfo[i].roleEntity != null) {
                            //     rname = userInfo[i].roleEntity["name"];
                            // } else {
                            //     rname = "-"
                            // }
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

                            // var getGrade=markInfo[i].studentEntity[0].studentGrade;
                            var getGrade = markInfo[i].gradeEvaluationEntity[0].questionBankEntity[0].questionBankStage;  //把学生学年变成了测评题学年
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
                                dataNewObj.evaluationName = markInfo[i].gradeEvaluationEntity[0].evaluationName;
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

    // 得到查询的参数（需要传给后台的参数）
    function queryParams(params) {
        var studentNo = $("#studentNo").val();
        var studentName = $("#studentName").val();
        var evaluationName = $("#evaluationName").val();

        var Grade = $("#studentGrade").val() == "0" ? "" : $("#studentGrade").val();
        var majorId = $("#majorId").val() == "0" ? "" : $("#majorId").val();
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,
            pageSize: params.pageSize,
            studentNo: studentNo,
            studentName: studentName,
            evaluationName: evaluationName,
            Grade: Grade,
            majorId: majorId
        };
        return JSON.stringify(temp);
    }

    return oTableInit;
}

function search() {
    $("#evaluationQuery").bootstrapTable('refresh');
}

function addFunctionAlty(value, row, index) {
    var btnText = '';

    btnText += "<input type=\"button\"  class=\"layui-btn layui-btn-xs btn-com\" onclick=\"evaluation(" + "'" + row.markId + "'" + "," + "'" + row.studentId + "'" + "," + "'" + row.gradeEvaluationId + "'" + ")\" value=\"点评\">";
    return btnText;
}

var getMarkId;

function evaluation(markId, studentId, gradeEvaluationId) {
    $("#evaluationQueryInfo").hide();
    $("#evaluationQueryStudent").show();
    getMarkId = markId;
    var data = {
        markId: markId,
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
            console.log(rs);
            var getStudentInfo = rs.data[0].studentEntity[0];
            $("#getNo").text(getStudentInfo.studentNo);
            $("#getName").text(getStudentInfo.studentName);
            //add bu fuqiang
            if (getStudentInfo.headImg != null && getStudentInfo.headImg != '') {
                $("#evaluationreviewhead").attr('src', getStudentInfo.headImgPath);
            }

            $("#getSex").text(getStudentInfo.studentSex);
            $("#getAge").text(getStudentInfo.studentAge);
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
            $("#getGrade").text(Grade);
            $("#getMajor").text(getStudentInfo.majorEntity[0].majorName);

            if (rs.data[0].gradeEvaluationEntity[0].evaluationType == 0) {
                $("#getType").text("阶段");
            } else {
                $("#getType").text("入学");
            }
            $("#getStage").text("第" + rs.data[0].gradeEvaluationEntity[0].grade + "学年");

            var myChart = echarts.init(document.getElementById('echarts'));
            var list1 = [];
            var list2 = [];
            var dimensionInfo = rs.data[0].dimensionRecordEntity;

            for (var i = 0; i < dimensionInfo.length; i++) {
                var a = {
                    text: '',
                    max: 100
                };
                a.text = dimensionInfo[i].dimensionEntity[0].dimensionName;
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

            //添加试题
            var testInfo = rs.data[1];
            var problemInfo = testInfo.problemEntity;
            $("#testTitle").text(testInfo.evaluationName);
            var text = "";
            var k = 0;
            var getKey = "";
            var allKey = "";
            for (var j = 0; j < problemInfo.length; j++) {
                getKey = problemInfo[j].answerEntity[0].solutionIds;
                allKey = problemInfo[j].solutionEntity;
                k = j + 1;
                text += "<div id=\"answer-list" + problemInfo[j].problemId + "\">";
                text += "                    <div class=\"layui-input-block\" id=\"question" + problemInfo[j].problemId + "\">" + k + "．" + problemInfo[j].problemTitle + "</div>";
                text += "                    <div class=\"layui-input-block\" id=\"choose" + problemInfo[j].problemId + "\">";
                if (allKey[0].solutionId == getKey) {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\" checked='checked'><div class=\"layui-unselect layui-form-radio layui-form-radioed\" style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                } else {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";
                }
                text += "A." + problemInfo[j].solutionEntity[0].solutionContent + "</div></div>";

                if (allKey[1].solutionId == getKey) {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\" checked='checked'><div class=\"layui-unselect layui-form-radio layui-form-radioed\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                } else {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                }
                text += "B." + problemInfo[j].solutionEntity[1].solutionContent + "</div></div>";

                if (allKey[2].solutionId == getKey) {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\" checked='checked'><div class=\"layui-unselect layui-form-radio layui-form-radioed\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                } else {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                }
                text += "C." + problemInfo[j].solutionEntity[2].solutionContent + "</div></div>";

                if (allKey[3].solutionId == getKey) {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\" checked='checked'><div class=\"layui-unselect layui-form-radio layui-form-radioed\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                } else {
                    text += "<input type=\"radio\" name=\"problem" + problemInfo[j].problemId + "\"><div class=\"layui-unselect layui-form-radio\"  style='width: 100%'><i class=\"layui-anim layui-icon\"></i><div>";

                }
                text += "D." + problemInfo[j].solutionEntity[3].solutionContent + "</div></div>";
                text += "                    </div>";
                text += "</div>";
            }
            $("#getTest").append(text);
        }
    });

    //维度字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl + "/system/SelectAllDimension",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            DimensionList = res.data;
            var txt = ' <option value="0">请选择</option>';
            for (var i = 0; i < res.data.length; i++) {
                username = res.data[i].dimensionName;
                var item = res.data[i].dimensionId;
                txt += '<option value ="' + item + '">' + username + '</option>'
            }
            $('#checkDimension').html(txt);
        }, error: function () {

            layer.msg('维度字典表查询失败', {icon: 2, time: 1000});
        }
    });

}

//返回
function comeBack() {
    $("#evaluationQueryInfo").show();
    $("#evaluationQueryStudent").hide();
    $("#getTest").empty();
    $("#teacherComment").val("");
    $("#evaluationhead").attr('src', '');
}

// 提交
function teacherPush() {
    var dimensionId = $("#checkDimension").val();
    var markContent = $("#teacherComment").val();
    var userId = sessionStorage.getItem("userId");
    var data = {
        markId: getMarkId,
        dimensionId: dimensionId,
        markContent: markContent,
        userId: userId
    };
    if (userId == undefined || userId == "") {
        layer.alert('请重新登录后再执行操作')
    } else if (dimensionId == "0") {
        layer.alert("请选择维度")
    } else if (markContent == "") {
        layer.alert("请填写点评项")
    } else {
        $.ajax({
            type: "post",//发送
            url: httpUrl + "/mark/markEntity",//地址
            data: JSON.stringify(data),//传送的数据
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (res) {

                layer.msg('点评成功', {icon: 1, time: 1000});
                comeBack();
                setTimeout("javascript:location.href='evaluationReviews.html'", 1000);
            },
            error: function () {

                layer.msg('任务流程编辑失败', {icon: 2, time: 1000});
            }
        })
    }

}