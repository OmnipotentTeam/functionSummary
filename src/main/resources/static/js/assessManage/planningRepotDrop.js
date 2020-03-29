//学生管理修改
var update_id;
var studentList=[];
var MajorList = [] ;
var studentID;
var planningId;
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


function update_modal(ids,id){
    $("#myModal-update").modal('show');
    update_id=id;
    $("#studentNumber").text(studentList[ids].studentNo);
    $("#studentName").text(studentList[ids].studentName);
    $("#studentSex").text(studentList[ids].studentSex);
    $("#studentAge").text(studentList[ids].studentAge);
    $("#studentStartSchool").text(studentList[ids].startSchool);
    var Select=studentList[ids].studentGrade;
    if(studentList[ids].studentGrade>3){
        Select =0;
    }
    $("#studentGrade_update").val(Select);
    $("#studentMajor").val(studentList[ids].majorId);
    // $("#student-yn").val(student-yn);
    $("#studentPhoneNumber").text(studentList[ids].studentPhoneNumber);
    // $("#studentChangeDescribe").val(studentChangeDescribe);
}
function update(){

    var studentGrade=document.getElementById("studentGrade_update").value;
    var studentMajor=document.getElementById("studentMajor").value;
    // var studentChangeDescribe=document.getElementById("studentChangeDescribe").value;
    obj={
        studentId:update_id,
        userId:userId,
        studentGrade:studentGrade,
        majorId:studentMajor,
        studentChangeDescribe:" "
    }
    if(studentGrade==0){
        layer.msg('请选择年级！',{
            icon: 2,
            time: 2000
        })
    }else if(studentMajor==0){
        layer.msg('请选择专业！',{
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
    else{
        $.ajax({
            url:httpUrl+"information/updateStudent",
            type:"POST",
            data:JSON.stringify(obj),
            contentType : "application/json;charset=utf-8",
            dataType:"json",
            success:function (res) {
                if(res.code==10000&&res.data==77777){
                    layer.msg('学生的评测或任务,未完成不可修改',{
                        icon: 1,
                        time: 3000
                    })
                    $("#myModal-update").modal('hide');
                    getUserList();
                }else if(res.code==10000){
                layer.msg('修改成功！',{
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
                            dataNewObj.majorId = getMajor(userInfo[i].majorId);
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

// 成长轨迹相关js！！！！！

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
//成长轨迹信息查询
function getGrow (getStudentId) {
    $("#getInfo").hide();
    $("#evaluationQueryStudent").show();
    studentID = getStudentId;
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
            	$("#reporthead").attr('src',rs.data.headImgPath);
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
    $("#reportcomment").val("");
    $("#reporthead").attr('src','');
}
function gradeEvaluation(e) {
   $("#growthreport").empty();
   $("#submitbutton").empty();
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

var date = new Date();
var n=date .getFullYear();
var m=date .getMonth()+1;
var r=date .getDate();
var mytime="<div style='margin-top: 100px'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    +n+"年&nbsp;&nbsp;"+m+"月&nbsp;&nbsp;"+r+"日</div>"; //获取当前日期
function displatgrowth(grade){
	var stugrade = '5'+grade;
	var textbutton ='';
	var data = {
			studentId:studentID,
			studenGrade:stugrade
	}
	$.ajax({
        type: "POST",
        url: httpUrl + "/growth/queryreport",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
            //console.log(rs);
            if(rs.data.length != 0){
            	
                if(rs.code == 10000){
                	if(rs.data.length != 0){
	                	planningId = rs.data[0].planningId;
	                	console.log(rs);
	                	var text='<div  id = "growthreportcomment" style="min-height:550px;margin-left:200px;margin-right:200px;background-color: white;" align="center" >'+
		                		     '<div>'+  
		                	           '<h1 style="font-size: xx-large;padding:20px">成绩单</h1>'+
		                		     '<div></br></br></br>'+
		                		     '<div align="left">'+
		                                '<span style="font-size:large;">&nbsp;&nbsp;&nbsp;&nbsp;<u>'+rs.data[0].studentName+'</u>  同学，&nbsp;&nbsp;<u>'+
		                                  rs.data[0].gradeYear+'</u>&nbsp;&nbsp;学年， 完成以下成长规划任务：</p>'+
		                             '</div></br>'+
                                        '<table width="600px" border="2 solid #000"; cellpadding="2" cellspacing="0"><tr >' +
                                        '<th><div class="row" style="padding: 6px;"><div class="col-xs-6 col-md-12">模型</div></th>'+
                                        '<th ><div class="col-xs-6 col-md-12">等级</div></th>'+
                                        '<th><div class="col-xs-6 col-md-12">等级内容</div></th></tr>'+
		                             '</div>';             	      
	                	if(rs.data !=""){
                            text +='<div class="model-list">';
                            for(var i =0;i<rs.data[0].growthPlanningCommentEntity.length;i++){
	                			text +=
                                    '<tr><div class="row">'+
                                    '<td><div class="col-xs-6 col-md-12">'+rs.data[0].growthPlanningCommentEntity[i].modelTitle+'</div></td>'+
                                    '<td><div class="col-xs-6 col-md-12">'+rs.data[0].growthPlanningCommentEntity[i].optionOrder+'</div></td>'+
                                    '<td><div class="col-xs-6 col-md-12">'+ rs.data[0].growthPlanningCommentEntity[i].optionComment+'</div></td>'+
                                    '</div></tr>';
	                		}
                            text +='</div></table>'
	                	}
	                	if(rs.data[0].commentComtent !=null && rs.data[0].commentComtent!= ''){
	                		text +='<div class="row evalute-content">'+
	                			'<div>&nbsp;&nbsp;&nbsp;&nbsp;教师评语   : '+rs.data[0].commentComtent+
	                		   '</div></div></div>'
	                	}
	                	//获取当前时间
                        text+=mytime;
	                	textbutton =' <button class="layui-btn" onclick="submitreportcomment('+grade+')">立即提交</button>'
	                		         
	                	$("#growthreport").append(text);   
	                	$("#submitbutton").append(textbutton);  
                }
            }
            
        }
       }
    });  
}
function submitreportcomment(grade){
	var reportcomment = $("#reportcomment").val();
	var data = {
			planningId:planningId,
			commentComtent:reportcomment
	};
	//console.log(data);
	$.ajax({
        type: "POST",
        url: httpUrl + "/growth/addcomment",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json;charest=utf-8",
        success: function (rs) {
           // console.log(rs);
            if(rs.code == 10000){
            	$("#reportcomment").val("");
            	gradeEvaluation("grade"+grade);
            }
        }
    });  
}
function download(grade){
	html2canvas(
			 document.getElementById("growthreportcomment"),{
				 dpi: 172,//导出pdf清晰度
				 allowTaint: true,
	             height: $("#growthreportcomment").outerHeight(),
                 onrendered: function (canvas) {
                	 var contentWidth = canvas.width;
                     var contentHeight = canvas.height;
                

                     //一页pdf显示html页面生成的canvas高度;
                     var pageHeight = contentWidth / 595.28 * 841.89;
                     //未生成pdf的html页面高度
                     var leftHeight = contentHeight;
                     //pdf页面偏移
                     var position = 0;
                     //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
                     var imgWidth = 555.28;
                     var imgHeight = 555.28/contentWidth * contentHeight;
                     var pageData = canvas.toDataURL('image/jpeg', 1.0);
                	   var pdf = new jsPDF('', 'pt', 'a4');
                	   if (leftHeight < pageHeight) {
                           pdf.addImage(pageData, 'JPEG', 20, 0, imgWidth, imgHeight );
                       } else {
                           while(leftHeight > 0) {
                               pdf.addImage(pageData, 'JPEG', 20, position, imgWidth, imgHeight)
                               leftHeight -= pageHeight;
                               position -= 841.89;
                               //避免添加空白页
                               if(leftHeight > 0) {
                                   pdf.addPage();
                               }
                           }
                       }
				       pdf.save('content.pdf');
			   }		 
			 })
}