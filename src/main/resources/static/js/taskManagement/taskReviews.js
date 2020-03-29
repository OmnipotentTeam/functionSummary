var p = "";         //stageRecordStatus
var pp = "";
var po = "";        //stageRecordStatus     通过状态
var o = "";         //studentGrade          学年
var pd = "";        //stageRecordStatus
var g;              //StageEntity的循环长度
var sre;            //stageRecordEvaluate   文本框内容
var tri;            //taskRecordId
var sri;            //stageRecordId
var nn;             //stageNum
var mm;             //stageAll
var zj;             //总结 阶段状态
var isPass = 5;         //评测等级5代表优秀 -1代表不及格

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
                $('#majorId').append($('<option value ="' + majorName + '">' + majorName + '</option>'));
            }
        },
        error: function () {
            layer.alert('专业查询失败');
        }
    });
    //维度字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl + "system/SelectAllDimension",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            DimensionList = res.data;
            for (var i = 0; i < res.data.length; i++) {
                username = res.data[i].dimensionName;
                var item = res.data[i].dimensionId;
                $('#dimensionName').append($('<option value ="' + username + '">' + username + '</option>'));
            }
        },
    });

    //初始化表格
    var oTable = new TableInit();
    oTable.Init();
});

function TableInit() {

    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tbo').bootstrapTable({
            url: httpUrl + '/task/selectTaskAll',         //请求后台的URL（*）
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
                    field: 'taskRecordId',
                    title: '序号',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                {
                    field: 'studentGrade',
                    title: '学年',
                    align: 'center'
                },
                {
                    field: 'majorName',
                    title: '专业',
                    align: 'center'
                },
                {
                    field: 'studentNo',
                    title: '学号',
                    align: 'center'
                },
                {
                    field: 'studentName',
                    title: '姓名',
                    align: 'center'
                },
                {
                    field: 'taskName',
                    title: '任务名称',
                    align: 'center'
                },
                {
                    field: 'dimensionName',
                    title: '维度',
                    align: 'center'
                },
                {
                    field: 'stageNum',
                    title: '阶段',
                    align: 'center'
                },
                {
                    field: 'modifyTime',
                    title: '提交时间',
                    align: 'center'
                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    events: operateEvents,//给按钮注册事件
                    formatter: addFunctionAlty//表格中增加按钮
                }],
            responseHandler: function (res) {  //后台返回的结果
                console.log(res);
                if (res.code == "10000") {
                    var userInfo = res.data.list;
                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'taskRecordId': '',
                                'studentGrade': '',
                                "majorName": '',
                                "studentNo": '',
                                "studentName": '',
                                "taskName": '',
                                "dimensionName": '',
                                "stageNum": '',
                                "modifyTime": '',
                            };
                            for (var j = 0; j < userInfo[i].length; j++) {

                            }
                            for (var z = 0; z < userInfo[i].length; z++) {

                            }
                            for (var a = 0; a < userInfo[i].length; a++) {

                            }
                            for (var b = 0; b < userInfo[i].length; b++) {

                            }
                            var NNUUMM = userInfo[i].taskEntities[a].stageEntities[(userInfo[i].taskEntities[a].stageEntities.length - 1)].stageNum;
                            if (NNUUMM == 100) {
                                NNUUMM = "总结"
                            } else {
                                NNUUMM = NNUUMM + "/" + (userInfo[i].taskEntities[a].stageAll - 1);
                            }
                            dataNewObj.taskRecordId = userInfo[i].taskRecordId;
                            dataNewObj.studentGrade = getstage(userInfo[i].studentEntities[j].studentGrade);
                            dataNewObj.majorName = userInfo[i].studentEntities[j].majorEntities[z].majorName;
                            dataNewObj.studentNo = userInfo[i].studentEntities[j].studentNo;
                            dataNewObj.studentName = userInfo[i].studentEntities[j].studentName;
                            dataNewObj.taskName = userInfo[i].taskEntities[a].taskName;
                            dataNewObj.dimensionName = userInfo[i].taskEntities[a].dimensionEntities[b].dimensionName;
                            dataNewObj.stageNum = NNUUMM;          //判断是否是总结后铺表
                            dataNewObj.taskRecordStatus = userInfo[i].taskRecordStatus;
                            dataNewObj.studentId = userInfo[i].studentId;
                            dataNewObj.taskId = userInfo[i].taskId;
                            dataNewObj.modifyTime = userInfo[i].modifyTime;

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
        var studentGrade = $('#studentGrade').val();
        var taskName = $('#taskName').val();
        var studentName = $('#studentName').val();
        var studentNo = $('#studentNo').val();
        var dimensionName = $('#dimensionName').val();
        var majorName = $("#majorId").val();
        var taskRecordStatus = 0;

        if (studentGrade == "0") {

            studentGrade = "";
        }
        if (majorName == "0") {

            majorName = "";
        }
        if (dimensionName == "0") {

            dimensionName = "";
        }


        var temp = {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,                     //初始化加载第一页，默认第一页
            pageSize: params.pageSize,
            majorName: majorName,
            studentGrade: studentGrade,
            taskName: taskName,
            studentName: studentName,
            studentNo: studentNo,
            taskRecordStatus: 0,
            dimensionName: dimensionName
        };

        return JSON.stringify(temp);
    }

    return oTableInit;
}

//刷新表格
function refreshTable() {
    $("#tbo").bootstrapTable('refresh');
}

function getstage(e) {
    if (e == 1) {
        return '大学一年级'
    }
    if (e == 2) {
        return '大学二年级'
    }
    if (e == 3) {
        return '大学三年级'
    } else {
        return '已毕业'
    }
}

function addFunctionAlty(value, row, index) {//把需要创建的按钮封装在函数中
    return [
        '<button id="see" type="button" class="layui-btn layui-btn-xs btn-com">点评</button>'
    ].join("")
}

window.operateEvents = {//添加一个按钮组在对应的按钮组中写下需要使用的事件
    "click #see": function (e, vlaue, row, index) {//点评按钮事件
        $("#taskQueryInfoStudent").hide();
        $("#taskQueryInfo").show();

        var data = {
            studentId: row.studentId,
            taskId: row.taskId,
            taskRecordStatus: row.taskRecordStatus
        };
        $.ajax({//任务详情
            type: "POST",
            url: httpUrl + "/task/selectTaskStudentStage",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charest=utf-8",
            success: function (rs) {
                console.log(rs);
                data = rs.data.TaskEntity;
                $('#getTaskName').html(data.taskName);
                $('#getDimensionName').html(data.dimensionName);
                $('#getTaskStopTime').html((data.taskStopTime).substr(0, 10));


                $('#bto').empty();

                dataone = rs.data.StageEntity;
                var k = 0;
                for (g = 0; g < dataone.length; g++) {
                    var m;
                    var uptri = dataone[g].stageRecord.length;
                    p = dataone[g].stageRecord[k].stageRecordStatus;
                    tri = dataone[g].stageRecord[(uptri - 1)].taskRecordId;
                    sri = dataone[g].stageRecord[(uptri - 1)].stageRecordId;
                    nn = dataone[g].stageNum;
                    mm = rs.data.TaskEntity.stageAll;
                    pd = dataone[g].stageRecord[k].stageRecordStatus;


                    console.log(pd);
                    var po;
                    if (pd == 5) {
                        po = '优秀';
                    } else if (pd == 4) {
                        po = '良好';
                    } else if (pd == 3) {
                        po = '中等';
                    } else if (pd == 1) {
                        po = '及格';
                    } else if (pd == -1) {
                        po = '不及格';
                    } else {
                        po = '待点评';
                    }
                    if (g == (dataone.length - 1)) {
                        for (m = 0; m < dataone[g].stageRecord.length; m++) {

                            if (dataone[g].stageNum == 100) {
                                zj = '总结';
                            } else {
                                zj = dataone[g].stageNum + '/' + (rs.data.TaskEntity.stageAll - 1);
                            }
                            var piturePath = dataone[g].stageRecord[m].stageRecordImgPath//成果物图片路径
                            var pathArray = piturePath.split("/");//路径截取

                            var Achievements = "";          //总结与阶段展示成果物
                            // if(dataone[g].stageRecord[m].stageRecordImgPath)
                            console.log(pathArray[pathArray.length - 1]);
                            if ((pathArray[pathArray.length - 1]) == "" || (pathArray[pathArray.length - 1]) == "null") {
                                // Achievements = '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>    <p id="res'+i+j+k+ '" style="width:auto;margin-left: 15%">无</p>\n';
                                Achievements += "                                                            <label class=\"layui-form-label\">成果物：</label>";
                                Achievements += "                                                            <div class=\"layui-input-block\">";
                                // Achievements += "                                                                <img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\" />";
                                Achievements += '                                                               <p id="res' + g + m + '" style="width:100%;margin-left: -1%;padding: 10px">无</p>\n';
                                // text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[m].stageRecordContent+"</p>";
                                Achievements += "                                                            </div>";
                            } else {
                                Achievements += "                                                            <label class=\"layui-form-label\">成果物：</label>";
                                Achievements += "                                                            <div class=\"layui-input-block\">";
                                Achievements += "                                                                <img class=\"h-img\" alt='成果物图片' src=\"" + dataone[g].stageRecord[m].stageRecordImgPath + "\" />";
                                // text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[m].stageRecordContent+"</p>";
                                Achievements += "                                                            </div>";
                            }
                            if (m == (dataone[g].stageRecord.length - 1)) {
                                var text = "";
                                text += "<div class=\"layui-colla-item\">";
                                text += "                                        <h2 class=\"layui-colla-title\">【" + zj + "】" + (dataone[g].stageRecord[k].modifyTime).substr(0, 10) + " 上传 <span class=\"layui-badge layui-bg-orange\">待点评</span><span style=\"float: right;color: #b2b2b2;\">" + (dataone[g].stageStopTime).substr(0, 10) + " 截止</span><i class=\"layui-icon layui-colla-icon\"></i></h2>";
                                text += "                                        <div class=\"layui-colla-content layui-show\">";
                                text += "                                            <div class=\"layui-card-body\">";
                                text += "                                                <div class=\"layui-form\" lay-filter=\"component-form-element\" wid100>";
                                text += "                                                    <div class=\"layui-row layui-col-space10 layui-form-item\">";
                                text += "                                                        <div class=\"layui-col-lg6\">";
                                text += "                                                            <label class=\"layui-form-label\">任务阶段：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                <p style=\"padding-top: 7px;\">" + zj + "</p>";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                        <div class=\"layui-col-lg6\">";
                                text += "                                                            <label class=\"layui-form-label\">截止时间：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                <p style=\"padding-top: 7px;\">" + (dataone[g].stageStopTime).substr(0, 10) + "</p>";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                    <div class=\"layui-form-item\">";
                                text += "                                                        <label class=\"layui-form-label\">阶段内容：</label>";
                                text += "                                                        <div class=\"layui-input-block\">";
                                text += "                                                            <p style=\"padding-top: 7px;\">" + dataone[g].stageContent + "</p>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                    <div class=\"media-list\">";
                                text += "                                                        <div class=\"layui-form-item\">";
                                // text += "                                                            <label class=\"layui-form-label\">成果物：</label>";
                                // text += "                                                            <div class=\"layui-input-block\">";
                                // text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\" />";
                                // text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[k].stageRecordContent+"</p>";
                                // text += "                                                            </div>";
                                // text += "                                                            <label class=\"layui-form-label\">成果物：</label>";
                                // text += "                                                            <div class=\"layui-input-block\">";
                                // text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\" />";
                                // // text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[m].stageRecordContent+"</p>";
                                // text += "                                                            </div>";
                                text += Achievements;
                                text += "                                                            <label class=\"layui-form-label\">总结：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                // text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\" />";
                                text += "                                                                <p style=\"padding-top: 7px; word-break: break-all;\">" + dataone[g].stageRecord[m].stageRecordContent + "</p>";
                                text += "                                                            </div>";
                                text += "                                                            <div class=\"media\">";
                                text += "                                                                <div class=\"media-right\">";
                                text += "                                                                    <ul class=\"list-inline\">";
                                text += "                                                                        <li>";
                                text += "                                                                            <span>" + (dataone[g].stageRecord[k].modifyTime).substr(0, 10) + " 上传</span>";
                                text += "                                                                        </li>";
                                text += "                                                                    </ul>";
                                text += "                                                                </div>";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                   <div class=\"media-list\">";
                                text += "                                                        <div class=\"layui-form-item\">";
                                text += "                                                            <label class=\"layui-form-label\">评测等级：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                  <input type=\"radio\" name=\"yes" + g + "\" value=\"5\" title=\"优秀\" checked=\"checked\">";
                                text += "                                                                <input type=\"radio\" name=\"yes" + g + "\" value=\"4\" title=\"良好\">";
                                text += "                                                                <input type=\"radio\" name=\"yes" + g + "\" value=\"3\" title=\"中等\">";
                                text += "                                                                <input type=\"radio\" name=\"yes" + g + "\" value=\"1\" title=\"及格\">";
                                text += "                                                                <input type=\"radio\" name=\"yes" + g + "\" value=\"-1\" title=\"不及格\">";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                        <div class=\"layui-form-item\">";
                                text += "                                                            <label class=\"layui-form-label\">评语：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                <textarea name=\"txt\" id=\"txt\" placeholder=\"请输入评语(255字以内)\" class=\"layui-textarea\" maxlength='255'></textarea>";
                                text += "                                                            </div>";
                                text += "                                                        </div><div style='text-align: right;margin-top: 20px'>";
                                text += "<button class=\"layui-btn\" onclick=\"fun()\">立即提交</button>";
                                text += "                                                    </div></div>";
                                text += "                                                </div>";
                                text += "                                            </div>";
                                text += "                                        </div>";
                                $('#bto').append(text);
                                k++;
                            } else {
                                var text = "";
                                text += "<div class=\"layui-colla-item\">";
                                text += "                                        <h2 class=\"layui-colla-title\">【" + zj + "】" + (dataone[g].stageRecord[k].modifyTime).substr(0, 10) + " 上传 <span class=\"layui-badge layui-bg-blue\">" + po + "</span><span style=\"float: right;color: #b2b2b2;\">" + (dataone[g].stageStopTime).substr(0, 10) + " 截止</span><i class=\"layui-icon layui-colla-icon\"></i></h2>";
                                text += "                                        <div class=\"layui-colla-content\">";
                                text += "                                            <div class=\"layui-card-body\">";
                                text += "                                                <div class=\"layui-form\" lay-filter=\"component-form-element\" wid100>";
                                text += "                                                    <div class=\"layui-row layui-col-space10 layui-form-item\">";
                                text += "                                                        <div class=\"layui-col-lg6\">";
                                text += "                                                            <label class=\"layui-form-label\">任务阶段：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                <p style=\"padding-top: 7px;\">" + zj + "</p>";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                        <div class=\"layui-col-lg6\">";
                                text += "                                                            <label class=\"layui-form-label\">截止时间：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                <p style=\"padding-top: 7px;\">" + (dataone[g].stageStopTime).substr(0, 10) + "</p>";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                    <div class=\"layui-form-item\">";
                                text += "                                                        <label class=\"layui-form-label\">阶段内容：</label>";
                                text += "                                                        <div class=\"layui-input-block\">";
                                text += "                                                            <p style=\"padding-top: 7px;\">" + dataone[g].stageContent + "</p>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                    <div class=\"media-list\">";
                                text += "                                                        <div class=\"layui-form-item\">";
                                text += "                                                            <label class=\"layui-form-label\">成果物：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\"" + dataone[g].stageRecord[m].stageRecordImgPath + "\" />";
                                // text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[m].stageRecordContent+"</p>";
                                text += "                                                            </div>";
                                text += "                                                            <label class=\"layui-form-label\">总结：</label>";
                                text += "                                                            <div class=\"layui-input-block\">";
                                // text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\" />";
                                text += "                                                                <p style=\"padding-top: 10px;width: auto; word-break: break-all;\">" + dataone[g].stageRecord[m].stageRecordContent + "</p>";
                                text += "                                                            </div>";
                                text += "                                                            <div class=\"media\">";
                                text += "                                                                <div class=\"media-right\">";
                                text += "                                                                    <ul class=\"list-inline\">";
                                text += "                                                                        <li>";
                                text += "                                                                            <span>" + (dataone[g].stageRecord[m].modifyTime).substr(0, 10) + " 上传</span>";
                                text += "                                                                        </li>";
                                text += "                                                                    </ul>";
                                text += "                                                                </div>";
                                text += "                                                            </div>";
                                text += "                                                        </div>";
                                text += "                                                    </div>";
                                text += "                                                   <div class=\"media-list\">";
                                text += "                                                       <blockquote class=\"layui-elem-quote\">";
                                text += "                                                        <label class=\"layui-form-label\">评语：</label>" + "<div class=\"layui-input-block\">" + "<p style=\"padding-top: 10px;width: auto; word-break: break-all;\">" + dataone[g].stageRecord[m].stageRecordEvaluate + "</p></div>";
                                text += "                                                       </blockquote>";
                                text += "                                                    </div>";
                                text += "                                                    </div>";
                                text += "                                                </div>";
                                text += "                                            </div>";
                                text += "                                        </div>";
                                $('#bto').append(text);
                                k++;
                            }
                        }
                    } else {
                        for (m = 0; m < dataone[g].stageRecord.length; m++) {
                            pd = dataone[g].stageRecord[m].stageRecordStatus;


                            console.log(pd);
                            var po;
                            if (pd == 5) {
                                po = '优秀';
                            } else if (pd == 4) {
                                po = '良好';
                            } else if (pd == 3) {
                                po = '中等';
                            } else if (pd == 1) {
                                po = '及格';
                            } else if (pd == -1) {
                                po = '不及格';
                            } else {
                                po = '待点评';
                            }
                            var text = "";
                            text += "<div class=\"layui-colla-item\">";
                            text += "                                        <h2 class=\"layui-colla-title\">【" + dataone[g].stageNum + "/" + (rs.data.TaskEntity.stageAll - 1) + "】" + (dataone[g].stageRecord[k].modifyTime).substr(0, 10) + " 上传 <span class=\"layui-badge layui-bg-blue\">" + po + "</span><span style=\"float: right;color: #b2b2b2;\">" + (dataone[g].stageStopTime).substr(0, 10) + " 截止</span><i class=\"layui-icon layui-colla-icon\"></i></h2>";
                            text += "                                        <div class=\"layui-colla-content\">";
                            text += "                                            <div class=\"layui-card-body\">";
                            text += "                                                <div class=\"layui-form\" lay-filter=\"component-form-element\" wid100>";
                            text += "                                                    <div class=\"layui-row layui-col-space10 layui-form-item\">";
                            text += "                                                        <div class=\"layui-col-lg6\">";
                            text += "<labelclass=\"layui-form-label\">任务阶段：</label>";
                            text += "                                                            <div class=\"layui-input-block\">";
                            text += "                                                                <p style=\"padding-top: 7px;\">" + dataone[g].stageNum + "/" + (rs.data.TaskEntity.stageAll - 1) + "</p>";
                            text += "                                                            </div>";
                            text += "                                                        </div>";
                            text += "                                                        <div class=\"layui-col-lg6\">";
                            text += "<labelclass=\"layui-form-label\">截止时间：</label>";
                            text += "                                                            <div class=\"layui-input-block\">";
                            text += "                                                                <p style=\"padding-top: 7px;\">" + (dataone[g].stageStopTime).substr(0, 10) + "</p>";
                            text += "                                                            </div>";
                            text += "                                                        </div>";
                            text += "                                                    </div>";
                            text += "                                                    <div class=\"layui-form-item\">";
                            text += "<labelclass=\"layui-form-label\">阶段内容：</label>";
                            text += "                                                        <div class=\"layui-input-block\">";
                            text += "                                                            <p style=\"padding-top: 7px;\">" + dataone[g].stageContent + "</p>";
                            text += "                                                        </div>";
                            text += "                                                    </div>";
                            text += "                                                    <div class=\"media-list\">";
                            text += "                                                        <div class=\"layui-form-item\">";
                            // text += "<labelclass=\"layui-form-label\">成果物：</label>";
                            // text += "                                                            <div class=\"layui-input-block\">";
                            // text += "<img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\"/>";
                            //         text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[m].stageRecordContent+"</p>";
                            //         text += "                                                            </div>";
                            text += "                                                            <label class=\"layui-form-label\">成果物：</label>";
                            text += "                                                            <div class=\"layui-input-block\">";
                            text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\"" + dataone[g].stageRecord[m].stageRecordImgPath + "\" />";
                            // text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">"+dataone[g].stageRecord[m].stageRecordContent+"</p>";
                            text += "                                                            </div>";
                            text += "                                                            <label class=\"layui-form-label\">总结：</label>";
                            text += "                                                            <div class=\"layui-input-block\">";
                            // text += "                                                                <img class=\"h-img\" alt='成果物图片' src=\""+dataone[g].stageRecord[m].stageRecordImgPath+"\" />";
                            text += "                                                                <p style=\"padding-top: 10px; word-break: break-all;\">" + dataone[g].stageRecord[m].stageRecordContent + "</p>";
                            text += "                                                            </div>";
                            text += "                                                            <div class=\"media\">";
                            text += "                                                                <div class=\"media-right\">";
                            text += "                                                                    <ul class=\"list-inline\">";
                            text += "                                                                        <li>";
                            text += "                                                                            <span>" + (dataone[g].stageRecord[m].modifyTime).substr(0, 10) + " 上传</span>";
                            text += "                                                                        </li>";
                            text += "                                                                    </ul>";
                            text += "                                                                </div>";
                            text += "                                                            </div>";
                            text += "                                                        </div>";
                            text += "                                                    </div>";
                            text += "                                                   <div class=\"media-list\">";
                            text += "                                                       <blockquote class=\"layui-elem-quote\">";
                            text += "                                                        评语：" + dataone[g].stageRecord[m].stageRecordEvaluate + "";
                            text += "                                                       </blockquote>";
                            text += "                                                    </div>";
                            text += "                                                    </div>";
                            text += "                                                </div>";
                            text += "                                            </div>";
                            text += "                                        </div>";
                            $('#bto').append(text);
                        }
                    }
                }

                // 注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
                layui.use('element', function () {
                    var element = layui.element;
                    element.init();
                });

                layui.use('form', function () {
                    var form = layui.form; //单选依赖这一步
                    form.render();
                    form.on('radio', function (data) {
                        isPass = data.value
                        console.log(isPass);
                    });
                });
            }
        });


        $.ajax({//学生信息
            type: "POST",
            url: httpUrl + "/student/selectStudent",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charest=utf-8",
            success: function (rg) {
            	console.log(rg)
                o = rg.data.studentGrade;
                var oo;
                if (o == 1) {
                    oo = '第一学年';
                } else if (o == 2) {
                    oo = '第二学年';
                } else if (o == 3) {
                    oo = '第三学年';
                } else {
                    oo = "已毕业";
                }
                // 数据赋值
                $("#getName").html(rg.data.studentName);
                $("#getNo").html(rg.data.studentNo);
                $("#getSex").html(rg.data.studentSex);
                $("#getAge").html(rg.data.studentAge);
                $("#getGrade").html(oo);
                $("#getMajor").html(rg.data.majorName);
                //add bu fuqiang
                if(rg.data.headImg!= null && rg.data.headImg!=''){
                	$("#taskreviewshead").attr('src',rg.data.headImgPath);
                } 
            }
        })

    }
};

function go_back() {
    $("#taskQueryInfo").hide();
    $("#taskQueryInfoStudent").show();
    $("#taskqueryhead").attr('src','');
}


function fun() {
    sre = $('#txt').val();
    if (isPass == undefined) {
        layer.alert("请选择评测等级");
        return false;
    }
    var uu;
    if (nn == 100) {
        uu = 1;
    } else {
        uu = 0;
    }
    // 需要传得参数:stageRecordId（阶段记录id）  taskRecordId（任务记录id） stageRecordEvaluate（教师点评） stageRecordStatus（是否通过 -1 没通过 ，1  通过  ，2 没点评）
    // end(新添加个参数  表示是否是总结   0 表示不是，1 表示是)
    if (sre == "") {
        layer.alert("请输入评语");
        return false;
    }
    var obj = {
        stageRecordStatus: isPass,
        stageRecordEvaluate: sre,
        taskRecordId: tri,
        stageRecordId: sri,
        end: uu
    };

    $.ajax({
        type: "POST",
        url: httpUrl + "/task/commentStage",
        data: JSON.stringify(obj),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rg) {
            layer.msg('提交成功!', {icon: 1, time: 1000});
            setTimeout("javascript:location.href='taskReviews.html'", 1000);
        }
    });
}