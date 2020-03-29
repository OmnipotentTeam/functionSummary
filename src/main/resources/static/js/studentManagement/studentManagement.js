//学生管理修改
var update_id;
var studentList = [];
var MajorList = [];
var userId = sessionStorage.getItem("userId");

//专业id转换名称
function getMajor(e) {
    for (var i = 0; i < MajorList.length; i++) {
        if (e == MajorList[i].majorId) {
            return MajorList[i].majorName;
        }
    }
}

getdeat();

function getdeat() {  //学级铺值
    var myDate = new Date();
    // myDate.getYear();        //获取当前年份(2位)
    var date = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    var startSchoolTxt = '  <option value="0">请选择</option>';
    for (var i = 0; i < 10; i++) {
        startSchoolTxt += '<option value="' + (date - i) + '">' + (date - i) + '</option>';
    }
    $("#startSchool").html(startSchoolTxt);
}

function delete_modal(studentId) {
    layer.open({
        content: "确认要删除吗?",
        btn: ["确认", "取消"],
        yes: function () {
            $.ajax({
                url: httpUrl + "information/deleteStudent/" + studentId,
                type: "POST",
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (res) {
                    if (res.message == "删除学生成功" || res.code == "10000") {
                        layer.msg('删除成功！', {
                            icon: 1,
                            time: 2000
                        })
                        getUserList();
                    } else {
                        layer.msg('删除失败！', {
                            icon: 2,
                            time: 2000
                        });
                    }
                }, error: function () {
                    layer.msg('删除失败！', {
                        icon: 2,
                        time: 2000
                    });
                }
            })
        }
    })


}

function update_modal(ids, id) {
    $("#myModal-update").modal('show');
    update_id = id;
    $("#studentNumber").text(studentList[ids].studentNo);
    $("#studentName").text(studentList[ids].studentName);
    $("#studentSex").text(studentList[ids].studentSex);
    $("#studentAge").text(studentList[ids].studentAge);
    $("#studentStartSchool").text(studentList[ids].startSchool);
    var Select = studentList[ids].studentGrade;
    if (studentList[ids].studentGrade > 3) {
        Select = 0;
    }
    $("#studentGrade_update").val(Select).trigger("change");
    $("#studentMajor").val(studentList[ids].majorId).trigger("change");
    // $("#student-yn").val(student-yn);
    $("#studentPhoneNumber").text(studentList[ids].studentPhoneNumber);
    // $("#studentChangeDescribe").val(studentChangeDescribe);
}

function update() {
    var studentGrade = document.getElementById("studentGrade_update").value;
    var studentMajor = document.getElementById("studentMajor").value;
    // var studentChangeDescribe=document.getElementById("studentChangeDescribe").value;
    obj = {
        studentId: update_id,
        userId: userId,
        studentGrade: studentGrade,
        majorId: studentMajor,
        studentChangeDescribe: " "
    }
    if (studentGrade == 0) {
        layer.msg('请选择年级！', {
            icon: 2,
            time: 2000
        })
    } else if (studentMajor == 0) {
        layer.msg('请选择专业！', {
            icon: 2,
            time: 2000
        })
    }
    // else if(studentChangeDescribe==""){
    //     layer.msg('请填写备注！',{
    //         icon: 2,
    //         time: 2000
    //     })
    // }
    else {
        $.ajax({
            url: httpUrl + "information/updateStudent",
            type: "POST",
            data: JSON.stringify(obj),
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (res) {
                if (res.code == 10000 && res.data == 77777) {
                    layer.msg('学生的评测或任务,未完成不可修改', {
                        icon: 1,
                        time: 3000
                    })
                    $("#myModal-update").modal('hide');
                    getUserList();
                } else if (res.code == 10000) {
                    layer.msg('修改成功！', {
                        icon: 1,
                        time: 2000
                    })
                    $("#myModal-update").modal('hide');
                    getUserList();
                }
            }
        })
    }
}

function bindStatus(index, status, id) {
    //console.log(studentList);
    //console.log(id);
    if (status == 0) {
        layer.msg('请先绑定信息后查看！', {
            icon: 2,
            time: 1000
        })
    } else {
        $("#myModal-parentInfo").modal('show');
        $.ajax({
            url: httpUrl + 'wechatparent/selectParent/' + id,
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            type: "post",
            success: function (res) {
                console.log(res.data);
for(var i = 0; i<res.data.length; i++){
    $('#layuiadmin-app-form-list1').append(
        $('<div style="border-bottom: #11111145 1px solid;margin-bottom: 10%">' +
            '<div class="layui-form-item">' +
            '<label class="layui-form-label">学生学号&nbsp;:</label>' +
            '<div class="layui-input-inline">' +
            '<label class="layui-form-label text-left">'+res.data[0].studentNo+'</label>' +
            '</div>' +
            '</div>' +
            '<div class="layui-form-item">' +
            ' <label class="layui-form-label">家长姓名&nbsp;:</label>' +
            '<div class="layui-input-inline">' +
            '<label class="layui-form-label text-left">'+res.data[i].parentName+'</label>' +
            '</div>' +
            '</div>' +
            '<div class="layui-form-item">' +
            ' <label class="layui-form-label">亲属关系&nbsp;:</label>' +
            '<div class="layui-input-inline">' +
            '<label class="layui-form-label text-left">'+res.data[i].relationship+'</label>' +
            '</div>' +
            '</div>' +
            '<div class="layui-form-item">' +
            '<label class="layui-form-label">联系电话&nbsp;:</label>' +
            '<div class="layui-input-inline">' +
            '<label class="layui-form-label text-left">'+res.data[i].parentPhone+'</label>' +
            '</div>' +
            '</div>' +
            '</div>'));
}
                // $('#bindStudentNumber').text(res.data[i].studentNo);
                // $('#bindParentName').text(res.data[0].parentName);
                // $('#bindRelationShip').text(res.data[0].relationship);
                // $('#bindParentPhone').text(res.data[0].parentPhone);
            }
        })
    }
}

function bindInfoClose() {
    $("#myModal-parentInfo").modal('hide');
    getUserList();
}

//学生信息查询
$(function () {
    //初始化表格

    //专业 字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl + "system/selectMajor",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            MajorList = res.data;
            for (var i = 0; i < res.data.length; i++) {
                majorName = res.data[i].majorName;
                var item = res.data[i].majorId;
                $('#studentMajor').append($('<option value ="' + item + '">' + majorName + '</option>'));
                $('#majorId').append($('<option value ="' + item + '">' + majorName + '</option>'));
            }
        },
    });
    var oTable = new TableInit();
    oTable.Init();
});

function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#myTable').bootstrapTable({
            url: httpUrl + 'information/selectStudentAll',         //请求后台的URL（*）
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
                    }
                }, {
                    field: 'studentName',
                    title: '姓名'
                }, {
                    field: 'startSchool',
                    title: '学级'
                }, {
                    field: 'studentGrade',
                    title: '学年'
                }, {
                    field: 'majorId',
                    title: '专业'
                }, {
                    field: 'studentNo',
                    title: '学号'
                }, {
                    field: 'studentPhoneNumber',
                    title: '手机号'
                }, {
                    field: 'operation',
                    title: '操作',
                    formatter: addFunctionAlty//表格中增加按钮
                }],
            responseHandler: function (res) {  //后台返回的结果 res返回json对象
                console.log(res);
                studentList = res.data.list;
                var NewData = [];
                if (res.code == "10000") {
                    var userInfo = res.data.list;

                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'studentId': '',
                                'studentPhoneNumber': '',
                                "studentName": '',
                                "studentNo": '',
                                "majorId": '',
                                "studentGrade": '',
                                "startSchool": '',
                                "studentBinding": ''
                            };
                            dataNewObj.studentId = userInfo[i].studentId;
                            dataNewObj.studentPhoneNumber = userInfo[i].studentPhoneNumber;
                            dataNewObj.studentName = userInfo[i].studentName;
                            dataNewObj.startSchool = userInfo[i].startSchool;
                            dataNewObj.studentNo = userInfo[i].studentNo;
                            dataNewObj.studentBinding = userInfo[i].studentBinding;
                            dataNewObj.majorId = getMajor(userInfo[i].majorId);
                            var getGrade = userInfo[i].studentGrade;
                            if (getGrade == 1) {
                                dataNewObj.studentGrade = "大学一年级";
                            } else if (getGrade == 2) {
                                dataNewObj.studentGrade = "大学二年级";
                            } else if (getGrade == 3) {
                                dataNewObj.studentGrade = "大学三年级";
                            } else {
                                dataNewObj.studentGrade = "已毕业";
                            }
                            NewData.push(dataNewObj);
                        }
                        // console.log(NewData)
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
        var studentGrade = $("#studentGrade").val();
        var majorId = $("#majorId").val();
        var studentNo = $("#studentNo").val();
        var studentNamee = $("#studentNamee").val();
        var studentPhoneNumber = $("#search-studentPhoneNumber").val();
        var startSchool = $("#startSchool").val();
        if (majorId == "0") {
            majorId = "";
        }
        if (studentGrade == "0") {
            studentGrade = "";
        }
        if (startSchool == "0") {
            startSchool = "";
        }
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,
            pageSize: params.pageSize,
            studentGrade: studentGrade,
            majorId: majorId,
            studentNo: studentNo,
            studentName: studentNamee,
            studentPhoneNumber: studentPhoneNumber,
            startSchool: startSchool


        };
        return JSON.stringify(temp);
    }

    return oTableInit;
}


// 表格中按钮
function addFunctionAlty(value, row, index) {
    var btnAction = '';
    var studentBinding = row.studentBinding;
    var btnBindtext, btnBindStatus;
    if (studentBinding == 0) {  //未绑定
        btnBindtext = '未绑定';
        btnBindStatus = 'btn-del'
    } else {
        btnBindtext = '已绑定';
        btnBindStatus = 'btn-edit'
    }
    btnAction += '<button type=\"button\" id=\"btn_look\" onclick=\"update_modal(\'' + index + '\', \'' + row.studentId + '\')\" class=\"layui-btn  layui-btn-xs btn-edit\" toggle=\"modal\" target=\"#myModal-update\">编辑</button>';
//编辑按钮图标：：：：：：<i class="layui-icon"></i>
    btnAction += '<button type=\"button\" id=\"btn_stop\" onclick=\"getGrow( \'' + row.studentId + '\')\"  class=\"layui-btn layui-btn-xs btn-grow\">成长轨迹</button>';
    btnAction += '<button type=\"button\" id=\"btn_stop\" onclick=\"bindStatus(\'' + index + '\',\'' + row.studentBinding + '\',\'' + row.studentNo + '\')\"  class=\"layui-btn layui-btn-xs ' + btnBindStatus + '\">' + btnBindtext + '</button>';
    //添加删除按钮
    btnAction += '<button type=\"button\" id=\"btn_look\" onclick=\"delete_modal(\'' + row.studentId + '\')\" class=\"layui-btn  layui-btn-xs btn-del\" toggle=\"modal\" target=\"#myModal-update\">删除</button>';
    return btnAction;
}


//刷新表格
function getUserList() {
    $("#myTable").bootstrapTable('refresh');
}

// 成长轨迹相关js！！！！！

//通过维度字典表获取维度名称
var DimensionList = [];
$(function () {
    //维度字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl + "/system/SelectAllDimension",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            DimensionList = res.data;

        },
    });
})

function getDimension(e) {
    for (var i = 0; i < DimensionList.length; i++) {
        if (e == DimensionList[i].dimensionId) {
            return DimensionList[i].dimensionName;
        }
    }
}

//成长轨迹信息查询
function getGrow(getStudentId) {
    img(getStudentId)
    $("#getInfo").hide();
    $("#evaluationQueryStudent").show();
    var data = {
        studentId: getStudentId
    };
    $.ajax({
        type: "POST",
        url: httpUrl + "/student/selectStudent",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            // console.log(rs);
            $("#getNo").text(rs.data.studentNo);
            $("#getName").text(rs.data.studentName);

            $("#getSex").text(rs.data.studentSex);
            $("#getAge").text(rs.data.studentAge);
            var Grade = "";
            if (rs.data.studentGrade == 1) {
                Grade = "第一学年";
            } else if (rs.data.studentGrade == 2) {
                Grade = "第二学年";
            } else if (rs.data.studentGrade == 3) {
                Grade = "第三学年";
            } else {
                Grade = "已毕业"
            }
            $("#getGrade").text(Grade);
            $("#getMajor").text(rs.data.majorName);
        }
    });

    $.ajax({
        type: "POST",
        url: httpUrl + "/information/selectStudentGrow",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (res) {
            console.log(res);
            if (res.data == null) {
                $("#scroll").html("该学生暂无成长轨迹");
                $("#scroll").css("text-align", "center");
            } else {
                $("#scroll").css("text-align", "");
                for (var I = 0; I < res.data.length; I++) {
                    var i = I + 1;
                    var tittles;
                    var taskTittle;
                    //判断是否有任务
                    if (res.data[I].TaskRecordEntity.length != 0) {
                        //测评标题
                        if (res.data[I].MarkEntity.markContent == "" || res.data[I].MarkEntity.markContent == null) {
                            tittles = "评测：" + res.data[I].GradeEvaluationEntity.evaluationName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + res.data[I].MarkEntity.modifyTime;
                        } else {
                            tittles = "评测：" + res.data[I].GradeEvaluationEntity.evaluationName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + res.data[I].MarkEntity.modifyTime;
                        }
                        //任务标题
                        if (res.data[I].TaskRecordEntity[0].taskRecordStatus == 0) {
                            taskTittle = "任务：" + res.data[I].TaskEntity.taskName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：未点评";
                        } else if (res.data[I].TaskRecordEntity[0].taskRecordStatus == 1) {
                            taskTittle = "任务：" + res.data[I].TaskEntity.taskName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已点评";
                        } else if (res.data[I].TaskRecordEntity[0].taskRecordStatus == -1) {
                            taskTittle = "任务：" + res.data[I].TaskEntity.taskName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已超时";
                        } else if (res.data[I].TaskRecordEntity[0].taskRecordStatus == 2) {
                            taskTittle = "任务：" + res.data[I].TaskEntity.taskName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已完成";
                        } else {
                            taskTittle = "任务：" + res.data[I].TaskEntity.taskName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已接受";
                        }
                        //添加测评与任务信息
                        $("#scroll").append(
                            '<div>\n' +
                            '<section>\n' +
                            '<ul id="test' + i + '" class="container anim-label-4 anim-art " style="height: auto;width:100%"> \n' +
                            '<li>\n' +
                            '<input type="radio" id="aa' + i + '" name="ac-3D" checked="checked"/>\n' +
                            '<label style="background:#ddd;" for="aa' + i + '" onclick="" title=' + tittles + '>' + tittles + '</label>\n' +
                            ' <article>\n' +
                            ' <div id="echarts' + i + '" style="width: 40%;float: left;height:300px"></div>\n' +
                            '<div id="content' + i + '" style="width: 55%;margin-left: 1%;float: left">\n' +
                            '<p  id="name' + i + '" >测试名称：</p>\n' +
                            '<p  id="type' + i + '" >测评类型：</p>\n' +
                            '<p  id="stage' + i + '" >阶段名称：</p>\n' +
                            '<p  id="date' + i + '" >发布日期：</p>\n' +
                            '<p  id="dimension' + i + '" >指派维度：</p>\n' +
                            '<p  id="teacher' + i + '" >教师点评：</p>\n' +
                            '</div>\n' +

                            '</article>\n' +
                            '</li>\n' +

                            '<li>\n' +
                            '<input type="radio" id="ab' + i + '" name="ac-3D" />\n' +
                            ' <label  style="background:#999;" for="ab' + i + '" onclick="" title=' + taskTittle + '>' + taskTittle + '</label>\n' +
                            '<article>\n' +
                            '<div id="allTask' + i + '" style="height:600px;overflow-y: scroll">\n' +
                            '</div>\n' +
                            '</article>\n' +
                            '</li>\n' +
                            '</ul>\n' +
                            '</section>\n'
                        );
                        var infoFirst = res.data[I];
                        //测评铺值
                        $("#name" + i).html("测试名称：" + infoFirst.GradeEvaluationEntity.evaluationName);  //评测名称
                        var getType = infoFirst.GradeEvaluationEntity.evaluationType;//评测类型
                        if (getType == 0) {
                            $("#type" + i).html("测试类型：入学")
                        } else {
                            $("#type" + i).html("测评类型：阶段")
                        }
                        $("#stage" + i).html("阶段名称：第" + infoFirst.GradeEvaluationEntity.grade + "学年");//阶段名称
                        $("#date" + i).html("发布日期：" + infoFirst.MarkEntity.createTime);//创造日期
                        $("#dimension" + i).html("指派维度：" + getDimension(infoFirst.MarkEntity.dimensionId));//指派维度
                        $("#teacher" + i).html("教师点评：" + infoFirst.MarkEntity.markContent);//教师点评

                        var myChart = echarts.init(document.getElementById('echarts' + i));//echarts表
                        var list1 = [];
                        var list2 = [];
                        var dimensionInfo = infoFirst.MarkEntity.dimensionRecordEntity;
                        for (var m = 0; m < dimensionInfo.length; m++) {
                            var a = {
                                text: '',
                                max: 200
                            };
                            a.text = dimensionInfo[m].dimensionName;
                            list1.push(a);
                            list2.push(dimensionInfo[m].dimensionRecordNum)
                        }
                        // console.log(list1,list2)
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
                        //任务铺值框
                        $("#allTask" + i).append(
                            '<div style="margin-left: 10%">\n' +
                            '<p id="task' + i + '" style="width:auto;margin-left:30%">任务名称：</p>\n' +
                            '<p id="deminsions' + i + '" style="width:auto;margin-left: 30%">维度：</p>\n' +
                            '<p id="sTime' + i + '" style="width:auto;margin-left: 30%">任务截止时间：</p>\n' +
                            '</div>\n' +
                            '<div id="allTaskContent' + i + '" style="width: 96%;height: auto;margin:2% 10px;">\n' +
                            '</div>\n');
                        $("#task" + i).html("任务名称：" + infoFirst.TaskEntity.taskName);//任务名称
                        $("#deminsions" + i).html("维度：" + infoFirst.TaskEntity.dimensionName);//维度
                        $("#sTime" + i).html("任务截止时间：" + infoFirst.TaskEntity.taskStopTime);//任务截止时间
                        // 阶段铺值
                        for (var J = 0; J < res.data[I].StageEntity.length; J++) {
                            var j = J + 1;
                            var taskStage = res.data[I].StageEntity[J].stageNum + "/" + (res.data[I].TaskEntity.stageAll - 1);//任务阶段
                            if (res.data[I].StageEntity[J].stageNum == 100) {
                                taskStage = "总结";                              //增加总结显示判断
                            }
                            var StageStopTime = res.data[I].StageEntity[J].stageStopTime;//截止时间
                            var StageContent = res.data[I].StageEntity[J].stageContent;//阶段内容


                            // 阶段记录铺值
                            for (var K = 0; K < res.data[I].StageEntity[J].stageRecord.length; K++) {
                                var StagePushTime = res.data[I].StageEntity[J].stageRecord[K].modifyTime;//上传时间
                                k = K + 1;

                                var guss = res.data[I].StageEntity[J].stageRecord[K].stageRecordStatus;//是否通过
                                var PassOrNO;
                                if (guss == 5) {
                                    PassOrNO = "优秀"
                                } else if (guss == 4) {
                                    PassOrNO = "良好"
                                } else if (guss == (-1)) {
                                    PassOrNO = "不及格"
                                } else if (guss == 1) {
                                    PassOrNO = "及格"
                                } else if (guss == 3) {
                                    PassOrNO = "中等"
                                } else {
                                    PassOrNO = "待点评"
                                }

                                var StageRecordEvaluate = res.data[I].StageEntity[J].stageRecord[K].stageRecordEvaluate;//评语
                                var piturePath = res.data[I].StageEntity[J].stageRecord[K].stageRecordImgPath//成果物图片路径
                                var pathArray = piturePath.split("/");//路径截取
                                var Achievements = "";          //总结与阶段展示成果物
                                console.log(pathArray[pathArray.length - 1]);
                                if ((pathArray[pathArray.length - 1]) == "" || (pathArray[pathArray.length - 1]) == "null") {
                                    Achievements = '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>    <p id="res' + i + j + k + '" style="width:auto;margin-left: 15%">无</p>\n';
                                } else {
                                    Achievements = '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>    <p id="res' + i + j + k + '" style="width:auto;margin-left: 15%"><img style="width: 75%" src=' + piturePath + '></p>\n';
                                }
                                var stageRecordContent = res.data[I].StageEntity[J].stageRecord[K].stageRecordContent //成果物描述或总结
                                $("#allTaskContent" + i).append(
                                    '<div id="kkkk' + i + j + k + '" style="width: 96%;height: auto;margin:2% 10px;border: 1px solid #eee;border-radius: 10px;-webkit-box-shadow: 0 15px 30px rgba(0,0,0,0.1);box-shadow: 0 15px 30px rgba(0,0,0,0.1);">\n' +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">任务阶段：</p>  <p id="tStage' + i + j + k + '" style="width:auto;margin-left: 15%;"></p>    <br>\n' +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">截止时间：</p>  <p id="taskTime' + i + j + k + '" style="width:auto;margin-left: 15%"></p><br>\n' +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">阶段内容：</p>  <p id="tContent' + i + j + k + '" style="width:auto;margin-left: 15%;padding-left: 163px;margin-right: 15%;"></p><br>\n' +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">上传时间：</p>  <p id="tPushTime' + i + j + k + '" style="width:auto;margin-left: 15%"></p><br>\n' +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">评测等级：</p>  <p id="pass' + i + j + k + '" style="width:auto;margin-left: 15%"></p><br>\n' +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">评语：</p>      <p id="eva' + i + j + k + '" style="width:auto;margin-left: 15%;padding-left: 163px;margin-right: 15%;">无评语</p><br>\n' +
                                    // '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>    <p id="res'+i+j+k+ '" style="width:auto;margin-left: 15%"><img src="../../../img/logo/logo.png"></p>\n'+
                                    // '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>    <p id="res'+i+j+k+ '" style="width:auto;margin-left: 15%"><img style="width: 75%" src='+piturePath+'></p>\n'+
                                    Achievements +
                                    '<p  style="width: 150px;float: left;margin-left: 15%">总结：</p><div><p id="sContent' + i + j + k + '" style="width: auto;margin-left: 15%;padding-left: 163px;margin-right: 15%;">' + stageRecordContent + '</p></div>\n' +
                                    '</div>\n'
                                )
                                console.log("#tStage" + j + k);
                                $("#tStage" + i + j + k).html(taskStage);//任务阶段
                                $("#taskTime" + i + j + k).html(StageStopTime);//截止时间
                                $("#tContent" + i + j + k).html(StageContent);//任务内容
                                $("#tPushTime" + i + j + k).html(StagePushTime);//上传时间
                                $("#pass" + i + j + k).html(PassOrNO);//是否通过
                                if (StageRecordEvaluate == null) {//教师评价
                                    $("#eva" + i + j + k).html("暂无评价");
                                } else {
                                    $("#eva" + i + j + k).html(StageRecordEvaluate);
                                }
                            }
                        }
                    } else {
                        if (res.data[I].MarkEntity.markContent == "" || res.data[I].MarkEntity.markContent == null) {
                            tittles = "评测：" + res.data[I].GradeEvaluationEntity.evaluationName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + res.data[I].MarkEntity.modifyTime;
                        } else {
                            tittles = "评测：" + res.data[I].GradeEvaluationEntity.evaluationName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + res.data[I].MarkEntity.modifyTime;
                        }

                        $("#scroll").append(
                            '<section>\n' +
                            '<ul id="test' + i + '" class="container anim-label-4 anim-art " style="height: auto;width:100%"> \n' +
                            '<li>\n' +
                            '<input type="radio" id="aa' + i + '" name="ac-3D" checked="checked"/>\n' +
                            '<label for="aa' + i + '" onclick="" title=' + tittles + '>' + tittles + '</label>\n' +
                            ' <article>\n' +
                            ' <div id="echarts' + i + '" style="width: 40%;float: left;height:300px"></div>\n' +
                            '<div id="content' + i + '" style="width: 55%;margin-left: 1%;float: left">\n' +
                            '<p  id="name' + i + '" >测试名称：</p>\n' +
                            '<p  id="type' + i + '" >测评类型：</p>\n' +
                            '<p  id="stage' + i + '" >阶段名称：</p>\n' +
                            '<p  id="date' + i + '" >发布日期：</p>\n' +
                            '<p  id="dimension' + i + '" >指派维度：</p>\n' +
                            '<p  id="teacher' + i + '" >教师点评：</p>\n' +
                            '</div>\n' +

                            '</article>\n' +
                            '</li>\n' +
                            '</ul>\n' +
                            '</section>\n'
                        );
                        var infoFirst = res.data[I];
                        var markContent;
                        var dimensionName;
                        if (infoFirst.MarkEntity.markContent == null) {     //判断是否点评
                            markContent = "";
                        } else {
                            markContent = infoFirst.MarkEntity.markContent;
                        }
                        if (getDimension(infoFirst.MarkEntity.dimensionId) == undefined) {  //判断是否推荐维度了
                            dimensionName = "";
                        } else {
                            dimensionName = getDimension(infoFirst.MarkEntity.dimensionId);
                        }
                        //测评铺值
                        $("#name" + i).html("测试名称：" + infoFirst.GradeEvaluationEntity.evaluationName);  //评测名称
                        var getType = infoFirst.GradeEvaluationEntity.evaluationType;//评测类型
                        if (getType == 0) {
                            $("#type" + i).html("测试类型：入学")
                        } else {
                            $("#type" + i).html("测评类型：阶段")
                        }
                        $("#stage" + i).html("阶段名称：第" + infoFirst.GradeEvaluationEntity.grade + "学年");//阶段名称
                        $("#date" + i).html("发布日期：" + infoFirst.MarkEntity.createTime);//创造日期
                        $("#dimension" + i).html("指派维度：" + dimensionName);//指派维度
                        $("#teacher" + i).html("教师点评：" + markContent);//教师点评

                        var myChart = echarts.init(document.getElementById('echarts' + i));//echarts表
                        var list1 = [];
                        var list2 = [];
                        var dimensionInfo = infoFirst.MarkEntity.dimensionRecordEntity;
                        for (var m = 0; m < dimensionInfo.length; m++) {
                            var a = {
                                text: '',
                                max: 200
                            };
                            a.text = dimensionInfo[m].dimensionName;
                            list1.push(a);
                            list2.push(dimensionInfo[m].dimensionRecordNum)
                        }
                        // console.log(list1,list2)
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

                    }
                }
            }

        }
    })
}

function comeBack() {
    $("#getInfo").show();
    $("#evaluationQueryStudent").hide();
    getUserList();
    $("#scroll").empty();
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
            console.log(rs);
            if(rs.data.headImg!= null && rs.data.headImg!=''){
                $("#develophead").attr('src',rs.data.headImgPath);
            }else{
                $("#develophead").hide();
            }
        }
    });
}
