
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>


    <link rel="stylesheet" href="statics/boot/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">

    <script src="statics/boot/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="statics/boot/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="statics/jqgrid/js/ajaxfileupload.js" type="text/javascript"></script>
    <script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>



    <script>
        $(function () {
            $('#article-table').jqGrid({
                url: "${pageContext.request.contextPath}/article/showall",//?guru_id=" + guru_id,  //查询当前专辑下的所有章节？当前专辑的id
                editurl:"${pageContext.request.contextPath}/article/dele",//?guru_id=" + guru_id, //设置编辑表单提交路径
                datatype:'json',
                styleUI:'Bootstrap',
                collEdit:true,
                colNames: ['编号', '标题', '作者','内容', "创建日期","操作"],
                colModel: [
                    {name: "id", hidden: true},
                    {name: "title", width: 50,editable: true},
                    {name: "author",width: 50},
                    {name: "content", width: 150,index: "qty", width: 70, align: "right"},
                    {name: "create_date", width: 80,index: "unit", width: 70, align: "right"},
                    {name: "add",width: 50,formatter:function (value,options,row) {
                            return "<a class='btn btn-warning' onclick=\"openModal('edit','"+row.id+"')\">修改</a>";
                        }}

                ],
                pager:'#article-pager',
                autowidth:true,
                height:'600px',
                rowNum : 5,
                rowList : [ 5, 10 ],
                caption : "展示文章的详细信息",
                viewrecords : true,
            }).navGrid('#article-pager',{edit : false,add : false,del : true,search:false})

        })


<%--//打开模态框--%>

        function openModal(oper,id) {
            KindEditor.html("#editor_id","");
            var article = $("#article-table").jqGrid("getRowData",id);
            //给表单设置默认值
            $("#article-id").val(article.id);
            $("#article-title").val(article.title);
            $("#article-author").val(article.author);
            KindEditor.html("#editor_id",article.content);
            $("#article-oper").val(oper);



            $("#article-modal").modal("show");

        }
        //文章输入框
        KindEditor.create('#editor_id',{
            //展示图片空间
            allowFileManager:true,
            //图片空间对应的地址
            fileManagerJson:"${pageContext.request.contextPath}/article/browser",
            //上传图片的地址
            uploadJson:"${pageContext.request.contextPath}/article/upload",
            //上传图片时接受的参数
            filePostName:"articleImage",
            //设置只能改变宽度，不能改变高度
            resizeType:1,
            //集成项目时必须添加,同步KindEditor的值到textarea文本框
            afterBlur:function () {
                this.sync();
            }
        });

        function delSavce(id) {
            //文章添加使用ajax
            $.ajax({
                url:"${pageContext.request.contextPath}/article/dele",
                data:$("#article-form").serialize(),
                type:"post",
                dataType:"json",
                success:function (){
                    //    关闭模态框
                    $("#article-modal").modal("hide");
                    //    刷新jqgrid
                    $("#article-table").trigger("reloadGrid");
                }
            })
        }
    </script>







</head>
<body>
<%--头部标题栏--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">展示所有文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab" onclick="openModal('add')">添加新文章</a></li>
    </ul>
</div>
<%--模态框--%>

<div class="modal fade" tabindex="-1" role="dialog" id="article-modal" >
    <div class="modal-dialog" role="document" style="width: 702px">
        <div class="modal-content">

            <%--头--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">文章</h4>
            </div>
                <%--内容--%>
            <div class="modal-body">
                <form class="form-horizontal" id="article-form">

                    <input type="hidden" id="article-id" name="id">
                    <input type="hidden" id="article-oper" name="oper">
                    <div class="form-group">
                        <label for="article-title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" name="title" class="form-control" id="article-title" placeholder="标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article-author" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input type="text" name="author" class="form-control" id="article-author" placeholder="作者">
                        </div >
                    </div>
                    <%--文章输入框--%>
                    <div class="form-group">
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                    </div>
                </form>
            </div>
                <%--结尾--%>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="delSavce()">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<%--jqgrid表格--%>
<table id="article-table"></table>
<div id="article-pager" style="height: 40px"></div>
</body>
</html>
