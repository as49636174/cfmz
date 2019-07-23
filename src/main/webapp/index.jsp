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
            url:'${pageContext.request.contextPath}banner/showall',
            datatype:'json',
            styleUI:'Bootstrap',
            // cellEdit:true,
            // multiselect:true,
            colNames:['编号', '名称', '图片', '描述', '状态','创建时间'],
            collEdit:true,
            colModel:[
                {name:"id",align:'center',hidden:true},
                {name:'name',align:'center',editable:true},
                {name:'cover',index:'cover',edittype:"file",editable:true,
                    formatter:function (value,option,rows) {
                        return "<img  style='width:30%;height:10s%;' src='${pageContext.request.contextPath}/img/"+rows.cover+"'/>";}},
                {name:'description',align:'center',editable:true},
                {name:'status',align:'center',editable:true,edittype:"select",
                    editoptions:{value:"正常:正常;冻结:冻结"}},
                {name:'create_date',align:'center'}
            ],
            pager:'#pager',
            autowidth:true,
            height:'100%',
            rowNum : 5,
            rowList : [ 5, 10 ],
            caption : "轮播图的详细信息",
            editurl:'${pageContext.request.contextPath}/banner/add',//设置编辑表单提交路径
            viewrecords : true,
        }).navGrid('#pager',{edit : true,add : true,del : true,search:false},{
            //修改的额外控制
            closeAfterEdit:close,
        },{
            //添加的额外控制
            closeAfterAdd:close,
            afterSubmit:function (response) {
                var status = response.responseJSON.status;
                var id = response.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        fileElementId:"cover",
                        data:{id:id},
                        type:"post",
                        success:function () {
                            $("#cnn").trigger("reloadGrid");
                        }
                    });
                }
                return "12312";
            }
        },{
            //删除的额外控制
        });



    })
</script>

<body>
<table id="cnn"></table>
<div id="pager"></div>
</body>
</html>
