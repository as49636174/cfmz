<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="statics/boot/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">

    <script src="statics/boot/js/jquery-2.2.1.min.js" type="text/javascript"></script>
    <script src="statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="statics/jqgrid/js/ajaxfileupload.js" type="text/javascript"></script>
</head>

<script>
    $(function () {
        $("#album-table").jqGrid({
            url: "${pageContext.request.contextPath}/guru/showall",
            datatype: "json",
            height: 150,
            colNames: ['编号', '名称', '图片', '状态' , '性别'],
            colModel: [
                {name: 'id', hidden: true},
                {name: 'name', editable: true},
                {
                    name: 'photo', editable: true, edittype: "file", formatter: function (value, option, rows) {
                        return "<img style='width:50%;height:12%;' src='${pageContext.request.contextPath}/img/" + rows.cover + "'/>";
                    }
                },
                {name: 'status',editable: true},
                {name: 'sex',editable: true},
            ],
            autowidth: true,
            styleUI: "Bootstrap",
            rowNum: 5,
            height: "500px",
            rowList: [5, 10,],
            pager: '#album-pager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            multiselect: false,
            <%--editurl:'${pageContext.request.contextPath}/album/add',//设置编辑表单提交路径--%>
            caption: "Grid as Subgrid",
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, guru_id) {// 1.装子表的容器id    2.关系属性
                var subgrid_table_id = subgrid_id + "_t";   //子表的table  id
                var pager_id = "p_" + subgrid_table_id;     //子表的div  id
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                    "<div id='" + pager_id + "' class='scroll'></div>");
                $("#" + subgrid_table_id).jqGrid(
                    {
                        url: "${pageContext.request.contextPath}/article/showall?guru_id=" + guru_id,  //查询当前专辑下的所有章节？当前专辑的id
                        editurl:"${pageContext.request.contextPath}/article/dele?guru_id=" + guru_id, //设置编辑表单提交路径
                        datatype: "json",
                        colNames: ['编号', '标题', '章节', "创建日期","上师ID"],
                        colModel: [
                            {name: "id", hidden: true},
                            {name: "title", width: 60,editable: true},
                            {name: "content", index: "qty", width: 70, align: "right"},
                            {name: "create_date", index: "unit", width: 70, align: "right"},
                            {name: "guru_id", hidden: true}

                        ],
                        autowidth: true,
                        styleUI: "Bootstrap",
                        rowNum: 20,
                        pager: pager_id,
                        sortname: 'num',
                        sortorder: "asc",
                        height: '100%'
                    }).jqGrid('navGrid', "#" + pager_id, {edit: false, add: false, del: true}, {
                        //控制子表的修改

                    }, {
                        //控制子表的添加
                    });
            },

        }).navGrid("#album-pager", {edit: false, add: false, del: false, search: false})
    })
</script>

<body>
<table id="album-table"></table>
<div id="album-pager" style="height: 40px"></div>
</body>
</html>