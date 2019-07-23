<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<html>
<link rel="stylesheet" href="statics/boot/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">

<script src="statics/boot/js/jquery-2.2.1.min.js" type="text/javascript"></script>
<script src="statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
<script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="statics/jqgrid/js/ajaxfileupload.js" type="text/javascript"></script>

<script>
    $(function () {
        $('#cnn').jqGrid({
            url:'${pageContext.request.contextPath}/user/showall',
            datatype:'json',
            styleUI:'Bootstrap',
            // cellEdit:true,
            // multiselect:true,
            colNames:['编号', '手机号', '用户名',  '法名', '省市', '城市', '签名', '图片','性别','创建时间'],
            collEdit:true,
            colModel:[
                {name:"id",align:'center',hidden:true},
                {name:"phone",align:'center'},
                {name:"username",align:'center'},
                {name:"dharma",align:'center'},
                {name:"province",align:'center'},
                {name:"city",align:'center'},
                {name:"sign",align:'center'},
                {name:'photo',index:'cover',edittype:"file",editable:true,
                    formatter:function (value,option,rows) {
                        return "<img  style='width:30%;height:10s%;' src='${pageContext.request.contextPath}/img/"+rows.photo+"'/>";}},
                {name:'sex',align:'center',edittype:"select",
                    editoptions:{value:"正常:正常;冻结:冻结"}},
                {name:'create_date',align:'center'}
            ],
            pager:'#pager',
            autowidth:true,
            height:'100%',
            rowNum : 5,
            rowList : [ 5, 10 ],
            caption : "用户表",
            editurl:'#',//设置编辑表单提交路径
            viewrecords : true,
        }).navGrid('#pager',{edit : false,add : false,del : false,search:false},{
            //修改的额外控制
        },{
            //添加的额外控制
        },{
            //删除的额外控制
        });



    })


    function out() {
        window.location.href="${pageContext.request.contextPath}/user/show";
    }
</script>

<body>
<%--头部标题栏--%>
<div>
<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">展示所有用户</a></li>
    <li role="presentation"><a href="#" aria-controls="profile" role="tab" data-toggle="tab" onclick="out()">下载表格</a></li>
</ul>
</div>
<table id="cnn"></table>
<div id="pager"></div>
</body>
</html>
