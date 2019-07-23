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
            url: "${pageContext.request.contextPath}/album/selectAllAlbum",
            //  ${pageContext.request.contextPath}/album/selectAllAlbum
            datatype: "json",
            height: 150,
            colNames: ['编号', '名称', '图片', '章节数' , '得分' ,'作者', '播音', '简介', '创建时间'],
            colModel: [
                {name: 'id', hidden: true},
                {name: 'title', editable: true},
                {
                    name: 'cover', editable: true, edittype: "file", formatter: function (value, option, rows) {
                        return "<img style='width:50%;height:20%;' src='${pageContext.request.contextPath}/img/" + rows.cover + "'/>";
                    }
                },
                {name: 'count',editable: true},
                {name: 'score',editable: true},
                {name: 'author', editable: true},
                {name: 'broadcast', editable: true},
                {name: 'brief',editable: true},
                {name: 'createDate'}
            ],
            autowidth: true,
            styleUI: "Bootstrap",
            rowNum: 8,
            height: "500px",
            rowList: [8, 10, 20, 30],
            pager: '#album-pager',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc",
            multiselect: false,
            editurl:'${pageContext.request.contextPath}/album/add',//设置编辑表单提交路径
            caption: "Grid as Subgrid",
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, album_id) {// 1.装子表的容器id    2.关系属性
                var subgrid_table_id = subgrid_id + "_t";   //子表的table  id
                var pager_id = "p_" + subgrid_table_id;     //子表的div  id
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                    "<div id='" + pager_id + "' class='scroll'></div>");
                $("#" + subgrid_table_id).jqGrid(
                    {
                        url: "${pageContext.request.contextPath}/chapter/selectChaptersBuAlbumId?album_id=" + album_id,  //查询当前专辑下的所有章节？当前专辑的id
                        editurl:'${pageContext.request.contextPath}/chapter/add?album_id=' + album_id,//设置编辑表单提交路径
                        datatype: "json",
                        colNames: ['编号', '标题', '大小', '时长', '文件名', "创建日期", "在线播放"],
                        colModel: [
                            {name: "id", hidden: true},
                            {name: "title", width: 60,editable: true},
                            {name: "size", index: "qty", width: 70, align: "right"},
                            {name: "duration", index: "unit", width: 70, align: "right"},
                            {name: "name", index: "unit", width: 70, align: "right",editable: true,edittype: "file"},
                            {name: "createDate", index: "unit", width: 70, align: "right"},
                            {
                                name: "aa", formatter: function (value, options, row) {
                                    return "<audio controls loop>\n" +
                                        "  <source src='${pageContext.request.contextPath}/image/"+row.name+"' type=\"audio/ogg\">\n" +
                                        "</audio>";
                                }
                            }
                        ],
                        autowidth: true,
                        styleUI: "Bootstrap",
                        rowNum: 20,
                        pager: pager_id,
                        sortname: 'num',
                        sortorder: "asc",
                        height: '100%'
                    }).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit: false,
                        add: true,
                        del: false
                    }, {
                        //控制子表的修改

                    }, {
                        //控制子表的添加
                        closeAfterAdd: close,
                        afterSubmit: function (response) {
                            var status = response.responseJSON.status;
                            var id = response.responseJSON.message;
                            if (status) {
                                $.ajaxFileUpload({
                                    url: "${pageContext.request.contextPath}/chapter/upload",
                                    fileElementId: "name",
                                    data: {id: id},
                                    type: "post",
                                    success: function () {
                                        $("#"+ subgrid_table_id).trigger("reloadGrid");
                                    }
                                });
                            }
                            return "12312";
                        }


                    });
            },

        }).navGrid("#album-pager", {edit: false, add: true, del: false, search: false}, {
            //控制修改
            closeAfterEdit: close,
            beforeShowForm: function (frm) {
                frm.find("#cover").attr("disabled", true);
            }
        }, {
            //控制添加
            //关闭添加对话框
            closeAfterAdd: close,
            afterSubmit: function (response) {
                var status = response.responseJSON.status;
                var id = response.responseJSON.message;
                if (status) {
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        data: {id: id},
                        type: "post",
                        success: function () {
                            $("#banner-table").trigger("reloadGrid");
                        }
                    });
                }
                return "12312";
            }
        }, {
            //控制删除
        });
    })
</script>

<body>
<table id="album-table"></table>
<div id="album-pager" style="height: 40px"></div>
</body>
</html>