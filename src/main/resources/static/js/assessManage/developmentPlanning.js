//学生管理修改
var update_id;
var studentList=[];
var MajorList = [] ;
var studentID;
var userId = sessionStorage.getItem("userId");
//专业id转换名称
function getMajor(e) {
    for (var i = 0; i < MajorList.length; i++) {
        if(e==MajorList[i].majorId){
            return  MajorList[i].majorName;
        }
    }
}
getdeat();
function getdeat() {  //学级铺值
    var myDate = new Date();
    // myDate.getYear();        //获取当前年份(2位)
    var date =myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    var startSchoolTxt='  <option value="0">请选择</option>';
    for (var i =0; i<10;i++){
        startSchoolTxt +='<option value="'+(date-i)+'">'+(date-i)+'</option>';
    }
    $("#startSchool").html(startSchoolTxt);
}

//学生信息查询
$(function () {
    //初始化表格
    var oTable = new TableInit();
    oTable.Init();
        //专业 字典表
    $.ajax({
        type: "post",//发送
        url:  httpUrl+"system/selectMajor",//地址
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (res) {
            MajorList = res.data;
            for (var i = 0; i < res.data.length; i++) {
                majorName=res.data[i].majorName;
                var item = res.data[i].majorId;
                $('#studentMajor').append($('<option value ="' + item + '">' + majorName + '</option>'));
                $('#majorId').append($('<option value ="' + item + '">' + majorName + '</option>'));
            }
        },
    });
});

function TableInit() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#myTable').bootstrapTable({
            url: httpUrl+'information/selectStudentAll',         //请求后台的URL（*）
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
            },{
                field: 'studentName',
                title: '姓名'
             },{
                    field: 'startSchool',
                    title: '学级'
                },{
                field: 'studentGrade',
                title: '学年'
            },{
                field: 'majorId',
                title: '专业'
            },{
                field: 'studentNo',
                title: '学号'
            },{
                field: 'studentPhoneNumber',
                title: '手机号'
            }, {
                field: 'operation',
                title: '操作',
                formatter: addFunctionAlty//表格中增加按钮
            }],
            responseHandler: function (res) {  //后台返回的结果 res返回json对象
               //console.log(res);
                studentList=res.data.list;
                var NewData = [];
                if(res.code == "10000"){
                    var userInfo = res.data.list;

                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'studentId':'',
                                'studentPhoneNumber': '',
                                "studentName": '',
                                "studentNo":'',
                                "majorId":'',
                                "studentGrade":'',
                                "startSchool":''

                            };
                            dataNewObj.studentId = userInfo[i].studentId;
                            dataNewObj.studentPhoneNumber = userInfo[i].studentPhoneNumber;
                            dataNewObj.studentName = userInfo[i].studentName;
                            dataNewObj.startSchool = userInfo[i].startSchool;
                            dataNewObj.studentNo = userInfo[i].studentNo;
                            dataNewObj.majorId = userInfo[i].majorName;

                            var getGrade= userInfo[i].studentGrade;
                            if(getGrade==1){
                                dataNewObj.studentGrade = "大学一年级";
                            }else if(getGrade==2){
                                dataNewObj.studentGrade = "大学二年级";
                            }else if(getGrade==3){
                                dataNewObj.studentGrade = "大学三年级";
                            }
                            else{
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
                }else{
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
        var studentGrade=$("#studentGrade").val();
        var majorId=$("#majorId").val();
        var studentNo=$("#studentNo").val();
        var studentNamee=$("#studentNamee").val();
        var studentPhoneNumber=$("#search-studentPhoneNumber").val();
        var startSchool=$("#startSchool").val();
        if(majorId=="0"){
            majorId="";
        }
        if(studentGrade=="0"){
            studentGrade="";
        }
        if(startSchool=="0"){
            startSchool="";
        }
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,
            pageSize: params.pageSize,
            studentGrade:studentGrade,
            majorId:majorId,
            studentNo:studentNo,
            studentName:studentNamee,
            studentPhoneNumber:studentPhoneNumber,
            startSchool:startSchool


        };
        return JSON.stringify(temp);
    }

    return oTableInit;
}



// 表格中按钮
function addFunctionAlty(value, row, index) {
    var btnText = '';
    btnText += '<button type=\"button\" id=\"btn_stop\" onclick=\"getGrow( \''+ row.studentId +'\')\"  class=\"layui-btn  layui-btn-xs btn-see\">查看</button>';
    return btnText;
}


//刷新表格
function getUserList() {
    $("#myTable").bootstrapTable('refresh');
}

//成长轨迹信息查询
function getGrow (getStudentId) {
	studentID = getStudentId;
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
             console.log(rs);
            $("#getNo").text(rs.data.studentNo);
            $("#getName").text(rs.data.studentName);

            $("#getSex").text(rs.data.studentSex);
            $("#getAge").text(rs.data.studentAge);
            var Grade ="";
            if (rs.data.studentGrade==1){
                Grade ="第一学年";
            }else if(rs.data.studentGrade==2){
                Grade ="第二学年";
            }else if(rs.data.studentGrade==3){
                Grade ="第三学年";
            }else{
                Grade ="已毕业"
            }
            $("#getGrade").text(Grade);
            $("#getMajor").text(rs.data.majorName);
            if(rs.data.headImg!= null && rs.data.headImg!=''){
            	$("#develophead").attr('src',rs.data.headImgPath);
            }  
            gradeEvaluation("grade"+rs.data.studentGrade);
        }
    });                
}
function comeBack() {
    $("#getInfo").show();
    $("#evaluationQueryStudent").hide();
    getUserList();
    //$("#scroll").empty();
    $("#develophead").attr('src','');
}
function gradeEvaluation(e) {
	$("#test").empty();
    var obj1 = document.getElementById("grade1");
    var obj2 = document.getElementById("grade2");
    var obj3 = document.getElementById("grade3");
    grade = e.replace(/[^0-9]/ig,"");
    if(grade==1){
        obj1.style.backgroundColor= "#CEEBE9";
        obj2.style.backgroundColor= "#FFFFFF";
        obj3.style.backgroundColor= "#FFFFFF";
        displatgrowth(1);
    } if(grade==2){
        obj2.style.backgroundColor= "#CEEBE9";
        obj1.style.backgroundColor= "#FFFFFF";
        obj3.style.backgroundColor= "#FFFFFF";
        displatgrowth(2);
    } if(grade==3){
        obj3.style.backgroundColor= "#CEEBE9";
        obj2.style.backgroundColor= "#FFFFFF";
        obj1.style.backgroundColor= "#FFFFFF";
        displatgrowth(3);
    }
}
function displatgrowth(grade){
	var stugrade = '5'+grade;
	var data = {
			studentId:studentID,
			studenGrade:stugrade
	}
	$.ajax({
        type: "POST",
        url: httpUrl + "/growth/queryAppointGrade",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            //console.log(rs);
            if(rs.code == 10000){
            	var text="";
            	if(rs.data !=""){
            		for(var i =0;i<rs.data[0].growthPlanningCommentEntity.length;i++){
            			var finishstate = rs.data[0].growthPlanningCommentEntity[i].finishState;
            			var submitState = rs.data[0].growthPlanningCommentEntity[i].submitState;
            			var statecolor;
            			var statecomment;
            			if(finishstate==0&&submitState==0){
            				statecolor = "#ddd";
            				statecomment = "已选择"
            			}else if(finishstate==1&&submitState==0){
            				statecolor = "#FFFF00";
            				statecomment = "已完成"
            			}else{
            				statecolor = "#99CC66";
            				statecomment = "已提交"
            			}
            			var tittles = "模型  ："+rs.data[0].growthPlanningCommentEntity[i].modelTitle+
            			    " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 程度："+rs.data[0].growthPlanningCommentEntity[i].optionComment+
            			    " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 完成时间："+rs.data[0].growthPlanningCommentEntity[i].updatetime;
            			text +=  '<li>\n'+
                               '<input type="radio" id="aa'+i+ '"name="ac-3D" />\n'+
                                '<label style="background:'+statecolor+'" for="aa'+i+ '"onclick="outputfun('+i+')" title="">'+
                                '<div class="row">'+
                                '<div class="col-xs-6 col-md-4">模型:'+
                                rs.data[0].growthPlanningCommentEntity[i].modelTitle+
                                 '</div><div class="col-xs-6 col-md-4">程度：'+
                                 rs.data[0].growthPlanningCommentEntity[i].optionComment+
                                 '</div><div class="col-xs-6 col-md-4">状态：'+
                                 statecomment+
                                 '</div><div id ="growthid'+i+'" style="display:none">'+
                                 rs.data[0].growthPlanningCommentEntity[i].id+
                                 '</div></div></label>\n'+
                                ' <article>\n'+
                                '<div  id="content'+i+ '" style="margin-left: 1%;float: left">\n'+
                                '</div>\n'+
                                '</article>\n'+
                                '</li>'
                            	
            		}
            		
            	}
            	$("#test").append(text);
            }
        }
    });  
}
function outputfun(j){
	var no = j;
	var id = $("#growthid"+no)[0].innerHTML;
	var data = {
			growthPlanningId:id
	}
	$("#content"+no).empty()
	$.ajax({
        type: "POST",
        url: httpUrl + "/growth/queryoutput",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            console.log(rs);
            if(rs.code == 10000){
            	var outputimage ="<div style='width:100%;float: left;'><div class='plan-result-label'>图片成果物：</div><div id='layer-photos-demo"+no+"' >";
            	var outputtext = "<div style='width:100%;float: left;'><div class='plan-result-label'>文字成果物：</div>";
            	if(rs.data != null && rs.data != ''){
            		for(var i=0;i<rs.data[0].growthOutputEntityList.length;i++){
                		var imgname = rs.data[0].growthOutputEntityList[i].outputUrl.split("/")[4];
                		//console.log(imgname);
                		if(rs.data[0].growthOutputEntityList[i].outputType != 0){
                		 
                		/* outputimage += "  <div class=\"layui-input-block\">";*/
                		/* outputimage += "  <img class=\"h-img\" alt='成果物图片' src=\"" + rs.data[0].growthOutputEntityList[i].outputUrl + "\" />";*/
                			outputimage += "  <img  height=\"200\" width=\"200\"  alt=\""+imgname+"\" layer-src=\"" + rs.data[0].growthOutputEntityList[i].outputUrl + "\"  src=\"" + rs.data[0].growthOutputEntityList[i].outputUrl + "\" />";
                		 /*outputimage += " </div>";*/
	               		}else{
	               			
	               			outputtext += "  <div class=\"layui-input-block\">";
	               			outputtext += "  <textarea style='margin-block-end: 10px;' class=\"form-control\" rows=\"3\" disabled=\"disabled\">\"" + rs.data[0].growthOutputEntityList[i].outputUrlComtent + "\" </textarea>";
	               			outputtext += "  </div>";
	               		}
                      
                	}
            	}
            	outputimage += "</div></div>";
            	outputtext += "</div>";
            	$("#content"+no).append(outputimage+outputtext);
            	layer.photos({
            		  photos: '#layer-photos-demo'+no
            		  ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            		}); 
            }
        }
    });  
}
