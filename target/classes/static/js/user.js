$(document).ready(function(){
    listUser()
    test();
});


function listUser() {
    var load = layer.load(0);
    $.ajax({
        url:"/user/list/",
        type:"POST",
        async:true,
        success:function (user) {
            var tab="";
            console.log(user)
            var list = new Array();
            list = user.list;
            $("#pageCount").text(user.pages);
            $("#dataCount").text(user.total);
            if(list.length>0){
                $('.M-box3').pagination({
                    pageCount: user.pages,
                    totalData: user.total,
                    showData: user.pageSize,
                    current:user.pageNum,
                    jump: true,
                    coping: true,
                    // mode: 'fixed',
                    count:3,
                    homePage: '首页',
                    endPage: '末页',
                    prevContent: '上页',
                    nextContent: '下页',
                    callback: function (api) {
                        findUserPage(api.getCurrent(),user.pageSize);
                        console.log(api.getCurrent())
                        $("#now").text(api.getCurrent());
                    }
                });
                for(var i=0;i<list.length;i++){
                    tab+="     <tr>\n" +
                        "            <td>"+((user.pageNum-1)*user.pageSize+i+1)+"</td>\n" +
                        "            <td>"+list[i].username+"</td>\n" +
                        "            <td>"+list[i].tel+"</td>\n" +
                        "            <td>"+list[i].name+"</td>\n" +
                        "            <td>"+list[i].id_card+"</td>\n" +
                        "            <td>\n" +
                        "                <div class=\"layui-btn-group\">\n" +
                        "                    <button class=\"layui-btn\" id=\"edit\" onclick=\"openEdit("+list[i].id+")\">编辑</button>\n" +
                        "                    <button class=\"layui-btn\" id=\"del\" onclick=\"Del("+list[i].id+")\">删除</button>\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "        </tr>"
                }

            }else {
                tab+="     <tr>\n" +
                    " <td colspan='6' style='text-align: center'>"+ "未找到数据" +"</td>\n" +
                    "        </tr>"
            }

            $("#user_list tr").remove();
            $("#user_list").append(tab);

        }
    })
    layer.close(load);
}

function findUserPage(pageNo,pageSize) {
    console.log(pageNo);
    var load = layer.load(0);
    $.ajax({
        url:"/user/list/",
        type:"POST",
        async:true,
        data:'pageNo='+pageNo+'&pageSize='+pageSize,
        success:function (user) {
            var tab="";
            console.log(user)
            var list = new Array();
            list = user.list;
            if(list.length>0){
                for(var i=0;i<list.length;i++){
                    tab+="     <tr>\n" +
                        "            <td>"+((pageNo-1)*pageSize+i+1)+"</td>\n" +
                        "            <td>"+list[i].username+"</td>\n" +
                        "            <td>"+list[i].tel+"</td>\n" +
                        "            <td>"+list[i].name+"</td>\n" +
                        "            <td>"+list[i].id_card+"</td>\n" +
                        "            <td>\n" +
                        "                <div class=\"layui-btn-group\">\n" +
                        "                    <button class=\"layui-btn\" id=\"edit\" onclick=\"openEdit("+list[i].id+")\">编辑</button>\n" +
                        "                    <button class=\"layui-btn\" id=\"del\" onclick=\"Del("+list[i].id+")\">删除</button>\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "        </tr>"
                }

            }else {
                tab+="     <tr>\n" +
                    " <td colspan='6' style='text-align: center'>"+ "未找到数据" +"</td>\n" +
                    "        </tr>"
            }

            $("#user_list tr").remove();
            $("#user_list").append(tab);

        }
    })
    layer.close(load);
}

function openEdit(id) {

    layer.open({
        type: 2,
        title: '修改用户',
        shadeClose: false,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['560px', '440px'],
        content: '/user/toEditUser/'+id
    })
}

function Edit(id) {
    console.log(id)
    var user = {
        id:id,
        username:$("#username").val(),
        name:$("#name").val(),
        tel:$("#tel").val(),
        id_card:$("#id_card").val(),
        password:$("#password").val()
    }
    $.ajax({
        url:"/user/updateUser",
        type:"POST",
        async:true,
        data:user,
        success:function (data) {
            if(data="success"){
                layer.alert("修改成功",{icon: 1,closeBtn: 0},function(index){
                    parent.layer.closeAll('iframe');//关闭弹窗
                    parent.setTimeout("location.reload()",100); //父页面刷新 必须在关闭iframe之前
                });
            }else{
                layer.alert("修改失败",{icon: 1,closeBtn: 0},function(index){
                    parent.layer.closeAll('iframe');//关闭弹窗
                    parent.setTimeout("location.reload()",100); //父页面刷新 必须在关闭iframe之前
                });
            }

        }
    })
}

function Del(id) {

    if(id!=""){
        layer.confirm('确定删除吗？',{btn:['确定','取消']},function () {
            $.ajax({
                url:"/user/deleteUserById",
                type:"POST",
                async:true,
                data:{id:id},
                success:function (data) {
                    if (data = "success") {
                        layer.alert("删除成功",{icon: 1,closeBtn: 0},function(index){
                            parent.layer.closeAll('iframe');//关闭弹窗
                            parent.setTimeout("location.reload()",100); //父页面刷新 必须在关闭iframe之前
                        });
                    }

                }
            });
            refresh();
        })
    }

}

function openAddUser() {
    layer.open({
        type: 2,
        title: '添加用户',
        shadeClose: false,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['560px', '440px'],
        content: '/user/toAddUser'
    })
}

function addUser() {
    // 校验输入数据
    //邮箱正则
    var emailRegex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    //手机正则
    var phoneRegex = /^[1][34578][0-9]{9}$/;
    //身份证正则
    var idCardRegex = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))((\d{4})|(\d{3}[Xx]))$/;
    //申请数量为数字
    var numRegex = /^[0-9]*$/;
    //空格
    var empRegex = /^[ ]+$/;
    //字母汉字
    var strRegex=/^[A-Za-z\u4e00-\u9fa5]+$/;

    //校验
    if(!strRegex.test($("username").val())){
        layer.tips()
    }
    // 获取user参数
    var user = {
        username:$("#username").val(),
        name:$("#name").val(),
        tel:$("#tel").val(),
        id_card:$("#id_card").val(),
        password:$("#password").val()

    }

    $.ajax({
        url:"/user/addUser",
        type:"POST",
        async:true,
        data:user,
        success:function (data) {
            layer.alert("添加成功",{icon: 1,closeBtn: 0},function(index){
                parent.layer.closeAll('iframe');//关闭弹窗
                parent.setTimeout("location.reload()",100); //父页面刷新 必须在关闭iframe之前
            });
        }
    });

}

function searchUser() {

    var user_data = $("#user_data").val();
    //console.log(user_data);
    var load = layer.load(0);
    $.ajax({
        url:"/user/searchUser",
        type:"POST",
        async:true,
        data:{user_data:user_data},
        success:function (user) {
            var tab="";
            if(user.length>0){

                for(var i=0;i<user.length;i++){
                    tab+="     <tr>\n" +
                        "            <td>"+(i+1)+"</td>\n" +
                        "            <td>"+user[i].username+"</td>\n" +
                        "            <td>"+user[i].tel+"</td>\n" +
                        "            <td>"+user[i].name+"</td>\n" +
                        "            <td>"+user[i].id_card+"</td>\n" +
                        "            <td>\n" +
                        "                <div class=\"layui-btn-group\">\n" +
                        "                    <button class=\"layui-btn\" id=\"edit\" onclick=\"openEdit("+user[i].id+")\">编辑</button>\n" +
                        "                    <button class=\"layui-btn\" id=\"del\" onclick=\"Del("+user[i].id+")\">删除</button>\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "        </tr>"
                }

            }else {
                tab="     <tr>\n" +
                    " <td colspan='6' style='text-align: center'>"+ "未找到数据" +"</td>\n" +
                    "     </tr>"
            }

            $("#user_list tr").remove();
            $("#user_list").append(tab);


        }
    })
    layer.close(load);
}

function test() {
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        x = table.render({
            elem: '#list'
            ,height: 676
            ,url: '/user/list' //数据接口
            ,parseData: function(res){ //res 即为原始返回的数据
                // return {
                //     "code": res.status, //解析接口状态
                //     "msg": res.message, //解析提示文本
                //     "count": res.total, //解析数据长度
                //     "data": res.data.item //解析数据列表
                // };
                alert(res);
            }
            ,page: true //开启分页
            // ,cols: [[ //表头
            //     {field:'id',title:'主键',hide:true},
            //     {field:'sbname',title:'报警设备',align:'left'},
            //     {field:'azwz',title:'报警位置',align:'left'},
            //     {field:'kstime',title:'报警时间',align:'center',sort:true},
            //     {field:'hjlevel',title:'报警级别',align:'center'},
            //     {field:'clzt',title:'处理状态',align:'left'},
            //     {field:'bjdz',title:'部件地址',align:'left'}
            // ]]
        });

    });
}

function refresh() {
    window.location.reload();
}

function Close() {
    parent.layer.closeAll('iframe');//关闭弹窗
}




