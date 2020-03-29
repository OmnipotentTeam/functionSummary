
//维度添加信息操作
function addOnclick() {
    $('#add-dimenName').val("");
    $('#myModal-add').modal('show');
}
function addBt(){
    var dimensionName=document.getElementById("add-dimenName").value;
    var obj={
        dimensionName:dimensionName,
    }
    if(dimensionName==""){
        layer.msg('请填写维度名称！',{
            icon: 2,
            time: 2000
        })
    }else{
    $.ajax({
        url:httpUrl+"system/insertDimension",
        type:"POST",
        data:JSON.stringify(obj),
        contentType : "application/json",
        dataType:"json",
        success:function (res) {
            if(res.code==10000){
            layer.msg('添加成功！',{
                icon: 1,
                time: 2000
            });
            $("#myModal-add").modal('hide');
            $("#add-dimenName").val("");
            getUserList();
            }else {
                layer.msg('添加失败！',{
                    icon: 1,
                    time: 2000
                });
                $("#myModal-add").modal('hide');
            }
        }
    })
    }
}
//维度执行修改操作
function update() {
    //console.log(asd);
    var a=document.getElementById("update-dimenName").value;
    var b={
        dimensionId:asd,
        dimensionName:a,
    }
    if(a==""){
        layer.msg('请填写维度名称！',{
            icon: 2,
            time: 2000
        })
    }else{
    $.ajax({
        url:httpUrl+"system/updateDimension",
        type:"POST",
        data:JSON.stringify(b),
        contentType : "application/json",
        dataType:"json",
        success:function (res) {
            if(res.code==10000){
            layer.msg('修改成功！',{
                icon: 1,
                time: 2000
            })
            $("#myModal-update").modal('hide');
            $("#update-dimenName").val("");
            getUserList();
            }
        }
    })
    }
}

var asd;
function updateBt(id){
    $('#update-dimenName').val("");
    $('#myModal-update').modal("show");
    asd=id;
}
function del(id){
    layer.open({
        content: "确认要删除吗?",
        btn:["确认","取消"],
        yes: function () {
            var a={
                dimensionId:id
            };
            $.ajax({
                url:httpUrl+"system/deleteDimension",
                type:"POST",
                data:JSON.stringify(a),
                contentType:"application/json",
                dataType:"json",

                success:function (res) {
                    layer.msg('删除成功！',{
                        icon:1,
                        time:2000
                    })
                    getUserList();
                }
            })
        }
    })
}



$(function () {
    //初始化表格
    var oTable = new TableInit();
    oTable.Init();
});

function TableInit() {
    // console.log("1111111111111111111");
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#myTable').bootstrapTable({
            url: httpUrl+'system/selectDimension',         //请求后台的URL（*）
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
            pageSize: 10,//单页记录hang数
            pageList: [10, 20, 30, 40],//分页步进值 可供选择的每页的行数（*）
            search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            silent: true,
            showRefresh: false,                  //是否显示刷新按钮
            showToggle: false,                   //是否显示详细视图和列表视图的切换
            minimumCountColumns: 2,             //最少允许的列数
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列

            columns: [{
                checkbox: true,
                visible: false
            }, {
                field: 'dimensionId',
                title: '维度编号',
                align: 'center',
                // formatter: function (value, row, index) {
                //     return index + 1;
                // }
            }, {
                    field: 'dimensionName',
                    title: '维度名称',
                    align: 'center',
                verticalAlign: 'middle'
                },  {
                    field: 'operation',
                    title: '操作',
                    align: 'center',
                verticalAlign: 'middle',
                    formatter: addFunctionAlty//表格中增加按钮
                }],
            responseHandler: function (res) {  //后台返回的结果 res返回json对象
                console.log(res);
                var NewData = [];
                if(res.code == "10000"){
                    var userInfo = res.data.list;

                    if (userInfo.length) {
                        for (var i = 0; i < userInfo.length; i++) {
                            var dataNewObj = {
                                'dimensionId': '',
                                "dimensionName": ''

                            };

                            dataNewObj.dimensionId = userInfo[i].dimensionId;
                            dataNewObj.dimensionName = userInfo[i].dimensionName;


                            NewData.push(dataNewObj);
                        }
                        console.log(NewData)
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
        var dimensionName=$("#search-input").val();
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageNum: params.pageNumber,
            pageSize: params.pageSize,
            dimensionName:dimensionName

        };
        return JSON.stringify(temp);
    }

    return oTableInit;
}


// 表格中按钮
function addFunctionAlty(value, row, index) {
    var btnText = '';
    btnText += '<button type=\"button\" id=\"btn_look\" onclick=\"updateBt(\''+ row.dimensionId +'\' )\" class=\"layui-btn  layui-btn-xs btn-edit\" toggle=\"modal\" target=\"#myModal-update\">编辑</button>';
    //<i className='layui-icon'>&#xe642;</i>
    //编辑按钮图标
    btnText += '<button type=\"button\" id=\"btn_stop\"  onclick=\"del(\'' + row.dimensionId + '\')\" class=\"layui-btn  layui-btn-xs btn-del\">删除</button>';
    //删除按钮图标<i className='layui-icon'>&#xe640;</i>
    return btnText;
}


//刷新表格
function getUserList() {
    $("#myTable").bootstrapTable('refresh');
}
