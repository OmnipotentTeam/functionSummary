/**
 * Created by 张鸿铭 on 2018/12/11.
 */
//通过维度字典表获取维度名称
var DimensionList =[];
$(function () {
    //维度字典表
    $.ajax({
        type: "post",//发送
        url: httpUrl+ "/system/SelectAllDimension",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            DimensionList=res.data;

        },
    });
})
function getDimension(e) {
    for (var i = 0; i < DimensionList.length; i++) {
        if(e==DimensionList[i].dimensionId){
            return  DimensionList[i].dimensionName;
        }
    }
}

function comeBack() {
    var data = {
        studentId: "2"
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
            $("#getGrade").text("第"+rs.data.studentGrade+"学年");
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
            for(var I=0;I<res.data.length;I++){
                var i=I+1;
                var tittles;
                var taskTittle;
                taskTittle="任务："+res.data[I].TaskEntity.taskName+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况："+res.data[I].TaskEntity.taskStatus;
                if(res.data[I].MarkEntity.markContent==""){
                    tittles="评测："+res.data[I].GradeEvaluationEntity.evaluationName+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：已完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+res.data[I].MarkEntity.modifyTime;
                }else {
                    tittles="评测："+res.data[I].GradeEvaluationEntity.evaluationName+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;完成情况：未完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+res.data[I].MarkEntity.modifyTime;

                }
                $("#scroll").append(
                '<section>\n'+
                    '<ul id="test'+i+'" class="container anim-label-4 anim-art " style="height: auto;width:100%"> \n'+
                        '<li>\n'+
                            '<input type="radio" id="aa'+i+ '" name="ac-3D" checked="checked"/>\n'+
                            '<label for="aa'+i+ '" onclick="" title='+tittles+'>'+tittles+'</label>\n'+
                           ' <article>\n'+
                               ' <div id="echarts'+i+ '" style="width: 40%;float: left;height:300px"></div>\n'+
                                    '<div id="content'+i+ '" style="width: 55%;margin-left: 1%;float: left">\n'+
                                        '<p  id="name'+i+ '" >测试名称：</p>\n'+
                                        '<p  id="type'+i+ '" >测评类型：</p>\n'+
                                        '<p  id="stage'+i+ '" >阶段名称：</p>\n'+
                                        '<p  id="date'+i+ '" >发布日期：</p>\n'+
                                        '<p  id="dimension'+i+ '" >指派维度：</p>\n'+
                                        '<p  id="system'+i+ '" >系统点评：</p>\n'+
                                        '<p  id="teacher'+i+ '" >教师点评：</p>\n'+
                                '</div>\n'+

                            '</article>\n'+
                        '</li>\n'+

                        '<li>\n'+
                            '<input type="radio" id="ab'+i+ '" name="ac-3D" />\n'+
                           ' <label for="ab'+i+ '" onclick="" title='+taskTittle+'>'+taskTittle+'</label>\n'+
                            '<article>\n'+
                                '<div id="allTask'+i+ '" style="height:600px;overflow-y: scroll">\n'+
                                    // '<div style="margin-left: 10%">\n'+
                                    //     '<p id="task'+i+ '" style="width:auto;margin-left:30%">任务名称：</p>\n'+
                                    //     '<p id="deminsions'+i+ '" style="width:auto;margin-left: 30%">维度：</p>\n'+
                                    //     '<p id="sTime'+i+ '" style="width:auto;margin-left: 30%">任务截止时间：</p>\n'+
                                    // '</div>\n'+
                                    // '<div style="width: 96%;height: auto;margin:2% 10px;border: 1px solid black;">\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">任务阶段：</p>           <p id="tStage'+i+ '" style="width:auto;margin-left: 15%">fdfd</p>    <br>\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">截止时间：</p>           <p id="taskTime'+i+ '" style="width:auto;margin-left: 15%">fdfd</p><br>\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">阶段内容：</p>           <p id="tContent'+i+ '" style="width:auto;margin-left: 15%">fdfd</p><br>\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">上传时间：</p>           <p id="tPushTime'+i+ '" style="width:auto;margin-left: 15%">fdfd</p><br>\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">是否通过：</p>           <p id="pass'+i+ '" style="width:auto;margin-left: 15%">fdfd</p><br>\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">评语：</p>               <p id="eva'+i+ '" style="width:auto;margin-left: 15%">fdfd</p><br>\n'+
                                    //     '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>             <p id="res'+i+ '" style="width:auto;margin-left: 15%"><img src="1859cbdfcec7442a8fb4b44bbe8a8aa1.jpg"></p>\n'+
                                    // '</div>\n'+
                                '</div>\n'+
                            '</article>\n'+
                        '</li>\n'+
                    '</ul>\n'+
                '</section>\n'
                );
                var infoFirst=res.data[I];
     //测评铺值
                $("#name"+i).html("测试名称："+infoFirst.GradeEvaluationEntity.evaluationName);  //评测名称
                var getType=infoFirst.GradeEvaluationEntity.evaluationType;//评测类型
                if(getType==0){
                    $("#type"+i).html("测试类型：入学")
                }else {
                    $("#type"+i).html("测评类型：阶段")
                }
                $("#stage"+i).html("阶段名称：第"+infoFirst.GradeEvaluationEntity.grade+"学年");//阶段名称
                $("#date"+i).html("发布日期："+infoFirst.MarkEntity.createTime);//创造日期
                $("#dimension"+i).html("指派维度："+getDimension(infoFirst.MarkEntity.dimensionId));//指派维度
                $("#system"+i).html("系统点评："+infoFirst.MarkEntity.sysContent);//系统点评
                $("#teacher"+i).html("教师点评："+infoFirst.MarkEntity.markContent);//教师点评

                var myChart = echarts.init(document.getElementById('echarts'+i));//echarts表
                var list1=[];
                var list2=[];
                var dimensionInfo=infoFirst.MarkEntity.dimensionRecordEntity;
                for(var m=0;m<dimensionInfo.length;m++) {
                    var a = {
                        text:'',
                        max:100
                    };
                    a.text =dimensionInfo[m].dimensionName;
                    list1.push(a);
                    list2.push(dimensionInfo[m].dimensionRecordNum)
                }
                // console.log(list1,list2)
                option = {
                    title: {
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        x: 'center',
                    },
                    radar: [
                        {
                            indicator: list1,
                            center: ['50%','50%'],
                            radius:80
                        }
                    ],
                    series: [
                        {
                            name:'维度图',
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
    //任务铺值

                for(var J=0;J<res.data[I].StageEntity.length;J++){
                    var j=J+1;
                    var taskStage=res.data[I].StageEntity[J].stageNum+"/"+res.data[I].StageEntity.length;//任务阶段
                    var StageStopTime=res.data[I].StageEntity[J].stageStopTime;//截止时间
                    var StageContent=res.data[I].StageEntity[J].stageContent;//阶段内容
                    var StagePushTime=res.data[I].StageEntity[J].modifyTime;//上传时间

                    $("#allTask"+i).append(
                        '<div style="margin-left: 10%">\n'+
                        '<p id="task'+j+ '" style="width:auto;margin-left:30%">任务名称：</p>\n'+
                        '<p id="deminsions'+j+ '" style="width:auto;margin-left: 30%">维度：</p>\n'+
                        '<p id="sTime'+j+ '" style="width:auto;margin-left: 30%">任务截止时间：</p>\n'+
                        '</div>\n'+
                        '<div id="allTaskContent'+j+'" style="width: 96%;height: auto;margin:2% 10px;">\n'+
                        '</div>\n')
                    $("#task"+j).html("任务名称："+infoFirst.TaskEntity.taskName);//任务名称
                    $("#deminsions"+j).html("维度："+infoFirst.TaskEntity.dimensionName);//维度
                    $("#sTime"+j).html("任务截止时间："+infoFirst.TaskEntity.taskStopTime);//任务截止时间
     // 阶段铺值
                    for (var K=0;K<res.data[I].StageEntity[J].stageRecord.length;K++){
                        k=K+1;
                        var  guss=res.data[I].StageEntity[J].stageRecord[K].stageRecordStatus;//是否通过
                        var PassOrNO;
                        if(guss == 5){
                            PassOrNO="优秀"
                        }else if(guss == 4){
                            PassOrNO="良好"
                        }else if(guss==(-1)){
                             PassOrNO="不及格"
                        }else if(guss==3){
                             PassOrNO="中等"
                        }else if(guss == 1){
                            PassOrNO="及格"
                        }{
                            PassOrNO="待点评"
                        }

                        var StageRecordEvaluate=res.data[I].StageEntity[J].stageRecord[K].stageRecordEvaluate;//评语
                        var piturePath=res.data[I].StageEntity[J].stageRecord[K].stageRecordImgPath//成果物图片路径
                        $("#allTaskContent"+j).append(

                            '<div style="width: 96%;height: auto;margin:2% 10px;border: 1px solid black;">\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">任务阶段：</p>  <p id="tStage'+k+ '" style="width:auto;margin-left: 15%"></p>    <br>\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">截止时间：</p>  <p id="taskTime'+k+ '" style="width:auto;margin-left: 15%"></p><br>\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">阶段内容：</p>  <p id="tContent'+k+ '" style="width:auto;margin-left: 15%"></p><br>\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">上传时间：</p>  <p id="tPushTime'+k+ '" style="width:auto;margin-left: 15%"></p><br>\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">评测等级：</p>  <p id="pass'+k+ '" style="width:auto;margin-left: 15%"></p><br>\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">评语：</p>      <p id="eva'+k+ '" style="width:auto;margin-left: 15%">无评语</p><br>\n'+
                            '<p  style="width: 150px;float: left;margin-left: 15%">成果物：</p>    <p id="res'+k+ '" style="width:auto;margin-left: 15%"><img src="../../../img/logo/logo.png"></p>\n'+
                            '</div>\n'
                        )

                        $("#tStage"+k).html(taskStage);//任务阶段
                        $("#taskTime"+k).html(StageStopTime);//截止时间
                        $("#tContent"+k).html(StageContent);//任务内容
                        $("#tPushTime"+k).html(StagePushTime);//上传时间
                        debugger;
                        $("#pass"+k).html(PassOrNO);//是否通过
                        if(StageRecordEvaluate==null){//教师评价
                            $("#eva"+k).html("暂无评价");
                        }else {
                            $("#eva"+k).html(StageRecordEvaluate);

                        }

                    }

                }
            }


        }
    })

}