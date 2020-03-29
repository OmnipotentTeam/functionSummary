
var username;
var state = '';
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
var i=1;
var stoptime;
var renwu;
var b_stoptime;
var b_renwu;
var kk;  //这是编辑任务的阶段数
var identification;  //登录角色
$(function () {

    //发布人字典表
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"grade/selectSysUser",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            SysUserList=res.data;
            for (var i = 0; i < res.data.length; i++) {
                username=res.data[i].username;
                var item = res.data[i].id;
                $('#userId').append($('<option value ="' + item + '">' + username + '</option>'));
            }
        },
    });


    //维度字典表
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"system/SelectAllDimension",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            DimensionList=res.data;
            for (var i = 0; i < res.data.length; i++) {
                username=res.data[i].dimensionName;
                var item = res.data[i].dimensionId;
                $('#dimensionId').append($('<option value ="' + item + '">' + username + '</option>'));
                $('#dimensionName').append($('<option value ="' + item + '">' + username + '</option>'));
                $('#update-dimensionName').append($('<option value ="' + item + '">' + username + '</option>'));
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

function gradeEvaluation(e) {
    var obj1 = document.getElementById("state0");
    var obj2 = document.getElementById("state1");
    var obj3 = document.getElementById("state2");
    state = e.replace(/[^0-9]/ig,"");

    if(state==0||state==""){
        $('#searchBt0').show();
        $('#searchBt1').hide();
        $('#searchBt2').hide();
        obj1.style.backgroundColor= "#CEEBE9";
        obj2.style.backgroundColor= "#F7F7F7";
        obj3.style.backgroundColor= "#F7F7F7";
    } if(state==1){
        $('#searchBt1').show();
        $('#searchBt2').hide();
        $('#searchBt0').hide();
        obj2.style.backgroundColor= "#CEEBE9";
        obj1.style.backgroundColor= "#F7F7F7";
        obj3.style.backgroundColor= "#F7F7F7";
    } if(state==2){
        $('#searchBt2').show();
        $('#searchBt1').hide();
        $('#searchBt0').hide();
        obj3.style.backgroundColor= "#CEEBE9";
        obj2.style.backgroundColor= "#F7F7F7";
        obj1.style.backgroundColor= "#F7F7F7";
    }
    refreshTable();

}
//加载表格
function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#task').bootstrapTable({
            url: httpUrl+"task/selectUnpublishedTask",         //请求后台的URL（*）
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
                    field: 'taskName',
                    title: '任务名称',
                    align: 'center'
                },
                {
                    field: 'dimensionId',
                    title: '维度',
                    align: 'center'
                },
                {
                    field: 'taskStopTime',
                    title: '截止时间',
                    align: 'center'
                },
                {
                    field: 'publisher',
                    title: '发布方',
                    align: 'center'
                },
                {
                    field: 'userId',
                    title: '发布人姓名',
                    align: 'center'
                },
                {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (state == ''||state == '0') {
                            return addFunctionAlty(value, row, index);
                        } else {
                            return addFunctionAltys(value, row, index);
                        }
                    }
                }
            ],
            responseHandler: function (res) {  //后台返回的结果

                console.log(res);
                if(res.code == "10000"){
                    var userInfo = res.data.list;

                    var NewData = [];
                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            if(userInfo[i].taskStatus == 1){
                                var date = new Date();
                                var myDate = date.toISOString();
                                if(userInfo[i].taskStopTime>myDate){
                                    stageEntityList.push(userInfo[i].stageEntityList);
                                }else{
                                    endEntityList.push(userInfo[i].stageEntityList);
                                }
                            }else{
                                unreleasedList.push(userInfo[i].stageEntityList);
                            }
                            var dataNewObj = {
                                'taskId': '',
                                "taskName": '',
                                "dimensionId": '',
                                "taskStopTime": '',
                                "userId": '',
                                "identification":''
                            };
                            dataNewObj.taskId = userInfo[i].taskId;  //序号
                            dataNewObj.taskName = userInfo[i].taskName;   //任务名称
                            dataNewObj.dimensionId = getDimension(userInfo[i].dimensionId);  //维度
                            dataNewObj.taskStopTime = userInfo[i].taskStopTime;   //截止时间
                            dataNewObj.publisher = userInfo[i].publisher;   //发布方
                            dataNewObj.userId = getUserName(userInfo[i].userId);  //发布人姓名
                            identification = userInfo[0].identification;   //登录角色
                            NewData.push(dataNewObj);
                        }
                    }
                    var data = {
                        total: res.data.total,
                        rows: NewData
                    };
                    return data;
                }
            }

        });
    };

    // 得到查询的参数
    function queryParams(params) {
        var dimensionId= $('#dimensionId').val();
        var taskName= $('#taskName').val();
        var userId= $('#userId').val();
        var temp = {//这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,                     //初始化加载第一页，默认第一页
            pageSize: params.pageSize,
            dimensionId:dimensionId,
            taskName:taskName,
            userId:userId,
            state:state
        };
        unreleasedList = [];
        return JSON.stringify(temp);
    }
    return oTableInit;
}

//刷新表格
function refreshTable() {
    $("#task").bootstrapTable('refresh');
}
//未发布状态下的表格 操作列
function addFunctionAlty(value, row, index) {
    var btnText = '';
    var loginStatus = identification;
    if(loginStatus=='企业'){   //企业用户
        btnText += "<input type=\"button\" data-toggle=\"modal\"  class=\"layui-btn layui-btn-xs btn-edit\"onclick=\"updateTask("+"'" + index +"'" +","+"'" + row.taskName+"'" +","+"'" + row.dimensionId +"'"+","+"'" + row.taskStopTime +"'"+","+"'" + row.taskId +"'"+")\" value=\"编辑\">";
        btnText += "<input type=\"button\"  class=\"layui-btn  layui-btn-xs btn-del\"   onclick=\"deleteTask("+"'" + row.taskId +"'"+")\" value=\"删除\" >";
    }else{    //教师用户
        btnText += "<input type=\"button\"  class=\"layui-btn layui-btn-xs btn-rel\"  onclick=\"releaseTask("+"'" + row.taskId +"'"+","+"'" + row.taskStopTime +"'"+")\" value=\"发布\">";
        btnText += "<input type=\"button\" data-toggle=\"modal\"  class=\"layui-btn layui-btn-xs btn-edit\"onclick=\"updateTask("+"'" + index +"'" +","+"'" + row.taskName+"'" +","+"'" + row.dimensionId +"'"+","+"'" + row.taskStopTime +"'"+","+"'" + row.taskId +"'"+")\" value=\"编辑\">";
        btnText += "<input type=\"button\"  class=\"layui-btn  layui-btn-xs btn-del\"   onclick=\"deleteTask("+"'" + row.taskId +"'"+")\" value=\"删除\" >";
    }
    return btnText;
}
//进行中&&已结束状态下的表格 操作列
function addFunctionAltys(value, row, index) {
    var btnText = '';
    btnText += "<input type=\"button\" data-toggle=\"modal\" class=\"layui-btn layui-btn-xs btn-dtl\" onclick=\"details("+"'" + index +"'" +","+"'" + row.taskName+"'" +","+"'" + row.dimensionId +"'"+","+"'" + row.taskStopTime +"'"+")\" value=\"详情\">";
    return btnText;
}
//表格内信息处理方法
function getUserName(e) {
    for (var i = 0; i < SysUserList.length; i++) {
        if(e==SysUserList[i].id){
            return  SysUserList[i].nickname;
        }
    }
}
//表格内信息处理方法
function getDimension(e) {
    for (var i = 0; i < DimensionList.length; i++) {
        if(e==DimensionList[i].dimensionId){
            return  DimensionList[i].dimensionName;
        }
    }
}
//显示添加模态框
function addModal() {
    $("#addModal").modal('show');
}
//添加阶段
function addDiv() {
    var text = "";
    text += "<div class=\"layui-fluid stageDiv\" id=\"stageDiv"+i+"\">";
    text += "                                <div class=\"layui-card\">";
    text += "                                    <div class=\"layui-card-header\"><span class=\"phase\">阶段 "+i+"</span>";
    text += "                                        <button type=\"button\" class=\"close\" id=\"deleteStage"+i+"\" onclick=\"deleteStage()\" style='display: none'>×</button>";
    text += "                                    </div>";
    text += "                                    <div class=\"layui-card-body\">";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">截止时间</label>";
    text += "                                                <div class=\"layui-input-inline\">";
    text += "                                                    <div class=\"input-group date\" id=\"date"+i+"\">";
    text += "                                                        <input type=\"text\" class=\"form-control\" maxlength=\"0\">";
    text += "                                                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-calendar\"></i></span>";
    text += "                                                    </div>";
    text += "                                                </div>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">任务内容</label>";
    text += "                                               <textarea style='width: 350px'  maxlength=\"200\"  placeholder=\"请填写任务内容（200字以内）\" class=\"stageContent layui-textarea\"></textarea>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                    </div>";
    text += "                                </div>";
    text += "                            </div>";

    $("#stage").append(text);

    $('#date'+i).datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    $('#deleteStage'+i).show();
    var k=i-1;
    if(k>0){
        $('#deleteStage'+k).hide();
    }
    ++i;
}
//添加框提交信息
function submit() {
    var taskName = $('#taskTame').val();
    var dimensionId = $('#dimensionName').val();
    var taskStopTime = $('#evaluationDate').val();
    var userId = sessionStorage.getItem("userId");
        stageEntityList=[];
    $.each($('.stageDiv'), function (i, item) {
        var stage = {};
        var phase= $(this).find('.phase')[0];
        var stageNum = $(phase).text();
        stage.stageNum= stageNum.replace(/[^0-9]/ig,"");

        var evaluationDate = $(this).find('.form-control')[0];
        stage.stageStopTime = $(evaluationDate).val();
        stoptime = stage.stageStopTime = $(evaluationDate).val();
        var stageContent = $(this).find('.stageContent')[0];
        stage.stageContent = $(stageContent).val();
        renwu = stage.stageContent = $(stageContent).val();
        if(stage.stageStopTime==""){
            // layer.alert('请填写阶段内容');
            return false;
        }else if(stage.stageContent==""){
            // layer.alert('请填写阶段内容');
            return false;
        }else{
            unreleasedList=[];
            stageEntityList.push(stage);
        }

    });


    var js={
        taskName:taskName,
        dimensionId:dimensionId,
        taskStopTime:taskStopTime,
        userId:userId,
        stageEntityList:stageEntityList
    };

    if(taskName==""){
        layer.alert('请输入任务名称')
    }else if(dimensionId==""){
        layer.alert('请选择维度名称')
    }else if(taskStopTime==""){
        layer.alert('请选择截止时间')
    }else if(userId==undefined||userId==""){
        layer.alert('请登录后再发布项目')
    }
    else if(stageEntityList.length ==0){
        console.log(stageEntityList);
        layer.alert('请填写阶段内容');
    }else if (stoptime == ""){
        layer.alert('请选择截止时间')
    }else if (renwu == ""){
        layer.alert('请填写任务内容')
    }
    else{
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            url: httpUrl+"task/insertUnpublishedTask",
            data: JSON.stringify(js),
            dataType: 'json',
            success: function (rs) {
                if(rs.code==10000){
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
}
//删除阶段
function deleteStage() {
    i--;
    var k=i-1;
    console.log(i);
    console.log(k);
    $('#deleteStage'+k).show();
    $("#stageDiv"+i).remove();
}
//编辑状态下的删除阶段
function deleteStages() {
    i--;
    kk--;
    console.log(i);
    console.log(kk);
    $('#deleteStages'+kk).show();
    $("#stageDiv"+i).remove();
}
//编辑框添加阶段
function addDivs() {
    l=long+1;
    var text = "";
    text += "<div class=\"layui-fluid stageDivs\" id=\"stageDivs"+l+"\">";
    text += "                                <div class=\"layui-card\">";
    text += "                                    <div class=\"layui-card-header\"><span class=\"phase\">阶段 "+l+"</span>";
    text += "                                        <button type=\"button\" class=\"close\" id=\"update-deleteStage"+l+"\" onclick=\"deleteStages()\" style='display: none'>×</button>";
    text += "                                    </div>";
    text += "                                    <div class=\"layui-card-body\">";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">截止时间</label>";
    text += "                                                <div class=\"layui-input-inline\">";
    text += "                                                    <div class=\"input-group date\" id=\"update-date"+l+"\">";
    text += "                                                        <input type=\"text\" class=\"form-control\" maxlength=\"0\">";
    text += "                                                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-calendar\"></i></span>";
    text += "                                                    </div>";
    text += "                                                </div>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">任务内容</label>";
    text += "                                                <textarea style='width: 350px' maxlength=\"200\"  placeholder=\"请填写任务内容(200字以内)\" class=\"stageContent layui-textarea\"></textarea>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                    </div>";
    text += "                                </div>";
    text += "                            </div>";

    $("#update-stage").append(text);
    $('#update-date'+l).datetimepicker({
        format: 'YYYY-MM-DD HH:mm:ss',
        locale: moment.locale('zh-cn')
    });
    $('#update-deleteStage'+l).show();
    var k=l-1;
    if(k>0){
        $('#update-deleteStage'+k).hide();
    }
    ++long;
}
//删除编辑中的阶段
function deleteStages() {
    var k=long-1;
    $('#update-deleteStage'+k).show();
    $("#stageDivs"+long).remove();
    long--;
}
//已发布任务的详情
function details(a,b,c,d) {
    $("#detailedModal").modal('show');
    $('#taskTames').text(b);
    $('#dimensionNames').text(c);
    $('#evaluationDates').text(d);
    var date = new Date();
    var myDate = date.toISOString();
    if(d>myDate){
        num = stageEntityList[a];
    }else{
        num = endEntityList[a];
    }
    var html = '';
    var stages = document.getElementById('stages');
    for (var i=0;i<num.length;i++){
        var k=i+1;
        html += setDiv(k);
    }
    stages.innerHTML = html;

    for (var i=0;i<num.length;i++){
        var stageStopTime=num[i].stageStopTime;
        var stageContent=num[i].stageContent;
        var stageNum=num[i].stageNum;
        $('#stageStopTimes'+stageNum).text(stageStopTime);
        $('#stageContents'+stageNum).text(stageContent);
    }

}
//生成一个一个可以编辑的div方法
function setDivs(item) {

    var text = "";
    text += "<div class=\"layui-fluid stageDivs\" id=\"stageDivs"+item+"\">";
    text += "                                <div class=\"layui-card\">";
    text += "                                    <div class=\"layui-card-header\"><span class=\"phase\">阶段 "+item+"</span>";
    text += "                                        <button type=\"button\" class=\"close\" id=\"update-deleteStage"+item+"\" onclick=\"deleteStages()\" style='display: none'>×</button>";
    text += "                                    </div>";
    text += "                                    <div class=\"layui-card-body\">";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">截止时间</label>";
    text += "                                                <div class=\"layui-input-inline\">";
    text += "                                                    <div class=\"input-group date\" id=\"update-date"+item+"\">";
    text += "                                                        <input type=\"text\" class=\"form-control\" maxlength=\"0\" id=\"update-stageStopTime"+item+"\">";
    text += "                                                        <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-calendar\"></i></span>";
    text += "                                                    </div>";
    text += "                                                </div>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">任务内容</label>";
    text += "                                              <textarea  maxlength=\"200\" style='width: 350px' placeholder=\"请填写任务内容(200字以内)\" class=\"stageContent layui-textarea\" id=\"update-stageContent"+item+"\"></textarea>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                    </div>";
    text += "                                </div>";
    text += "                            </div>";
    return text;
}
//生成一个都是标签的的div方法
function setDiv(item) {

    var text = "";
    text += "<div class=\"layui-fluid stageDiv\" id=\"stageDiv"+item+"\">";
    text += "                                <div class=\"layui-card\">";
    text += "                                    <div class=\"layui-card-header\"><span class=\"phase\">阶段 "+item+"</span>";
    text += "                                        <button type=\"button\" class=\"close\" id=\"update-deleteStage"+item+"\" onclick=\"deleteStage()\" style='display: none'>×</button>";
    text += "                                    </div>";
    text += "                                    <div class=\"layui-card-body\">";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">截止时间</label>";
    text += "                                                <div class=\"layui-input-inline\">";
    text += "                                                    <span class=\"layui-form-mid\" id=\"stageStopTimes"+item+"\">";
    text += "                                                    </span>";
    text += "                                                </div>";


    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                        <div class=\"layui-form\" wid110=\"\">";
    text += "                                            <div class=\"layui-form-item\">";
    text += "                                                <label class=\"layui-form-label\">任务内容</label>";
    text += "                                                <span class=\"layui-form-mid\" id=\"stageContents"+item+"\"></span>";
    text += "                                            </div>";
    text += "                                        </div>";
    text += "                                    </div>";
    text += "                                </div>";
    text += "                            </div>";


    return text;
}
//编辑任务信息
function updateTask(a,b,c,d,e) {
    $("#updateModal").modal('show');
    $('#update-taskTame').val(b);
    var dimensionName = setDimension(c);
    $('#update-dimensionName').val(dimensionName);
    $('#update-evaluationDate').val(d);
    taskId=e;
    var html = '';
    var stage = document.getElementById('update-stage');
    nums = unreleasedList[a];
    long = nums.length;
    kk=nums.length;
    for (var i=0;i<nums.length;i++){
        var k=i+1;
        html += setDivs(k);

    }
    stage.innerHTML = html;
    for (var i=0;i<nums.length;i++){
        var stageStopTime=nums[i].stageStopTime;
        var stageContent=nums[i].stageContent;
        var stageNum=nums[i].stageNum;
        $('#update-stageStopTime'+stageNum).val(stageStopTime);
        $('#update-stageContent'+stageNum).val(stageContent);
        $('#update-date'+stageNum).datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            locale: moment.locale('zh-cn')
        });
    }
    $('#update-deleteStage'+k).show();
}
//维度转id
function setDimension(e) {
    for (var i = 0; i < DimensionList.length; i++) {
        if(e==DimensionList[i].dimensionName){
            return  DimensionList[i].dimensionId;
        }
    }
}
//编辑框提交任务
function updateSubmit() {
    var taskName = $('#update-taskTame').val();
    var dimensionId = $('#update-dimensionName').val();
    var taskStopTime = $('#update-evaluationDate').val();
    var userId = sessionStorage.getItem("userId");
    var stageEntityList=[];
    $.each($('.stageDivs'), function (i, item) {
        var stage = {};
        var phase= $(this).find('.phase')[0];
        var stageNum = $(phase).text();
        stage.stageNum= stageNum.replace(/[^0-9]/ig,"");

        var evaluationDate = $(this).find('.form-control')[0];
        stage.stageStopTime = $(evaluationDate).val();
        b_stoptime = stage.stageStopTime = $(evaluationDate).val();
        var stageContent = $(this).find('.stageContent')[0];
        stage.stageContent = $(stageContent).val();
        b_renwu =  stage.stageContent = $(stageContent).val();
        if(stage.stageStopTime==""){
          return false;
        }else if(stage.stageContent==""){
            return false;
        }else{
            stageEntityList.push(stage);
        }
    });

    var js={
        taskId:taskId,
        taskName:taskName,
        dimensionId:dimensionId,
        taskStopTime:taskStopTime,
        userId:userId,
        stageEntityList:stageEntityList
    };

    if(taskName==""){
        layer.alert('请输入任务名称')
    }else if(dimensionId==""){
        layer.alert('请选择维度名称')
    }else if(taskStopTime==""){
        layer.alert('请选择截止时间')
    }else if(userId==undefined||userId==""){
        layer.alert('请登录后再发布项目')
    }else if(stageEntityList.length==0){
        layer.alert('请填写阶段内容')
    }else if (b_stoptime == ""){
        layer.alert('请选择截止时间')
    }else if (b_renwu == ""){
        layer.alert('请填写任务内容')
    }
    else{
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            url: httpUrl+"task/updateUnpublishedTasks",
            data: JSON.stringify(js),
            dataType: 'json',
            success: function (rs) {
                if(rs.code==10000){
                    layer.alert("修改成功");
                    unreleasedList=[];     //添加成功后重置任务编辑模态框
                    refreshTable();
                    empty();
                    $('#updateModal').modal('hide');
                }
            },
            error: function () {
            }
        });
    }
}
//通过id删除未发布信息
function deleteTask(e) {
    layer.open({
        content: "确认要删除吗?",
        btn:["确认","取消"],
        yes: function () {
            var js={
                taskId:e
            };
            $.ajax({
                type: "post",//发送
                url:  httpUrl+"task/deleteUnpublishedTask",//地址
                data: JSON.stringify(js),//传送的数据
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                success: function (res) {
                    if(res.code==10000){
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
//通过id发布信息
function releaseTask(e,time) {
    var js={
        taskId:e
    };
    var date = new Date();
    var myDate = date.toISOString();
    if(myDate>time){
        layer.alert("已经过了截止时间不可发布！")
    }else{
        $.ajax({
            type: "post",//发送
            url:  httpUrl+"task/updateUnpublishedTask",//地址
            data: JSON.stringify(js),//传送的数据
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (res) {
                if(res.code==10000){
                    layer.alert('发布成功');
                    refreshTable();
                }
            },
            error: function () {
                layer.alert('发布失败');
            }
        })
    }

}
//清空文本框
function empty() {
    i=1;
    $('#taskTame').val("");
    $('#dimensionName').val("").trigger("change");
    $('#evaluationDate').val("");
    $('#update-taskTame').val("");
    $('#update-dimensionName').val("");
    $('#update-evaluationDate').val("");
    $('#stage').empty();
    $('#update-stage').empty();
}