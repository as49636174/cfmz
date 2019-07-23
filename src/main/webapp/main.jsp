<%@page pageEncoding="UTF-8"  contentType="text/html; utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                CHIMINGFAZHOU
            </a>
        </div>
        <div align="right" style="line-height: 50px; color:#eee;">
            <span class="text">欢迎:${sessionScope.login.nickname}</span>
            <button id="cmm" type="button" class="btn btn-danger">退出</button>
        </div>
    </div>
</nav>

<div class="row">
    <div class="col-md-2" style="margin:80px 0 0 20px;">
        <div class="panel-group" id="pg1">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a class="panel-title" href="#ddd2" data-toggle="collapse" data-parent="#pg1">轮播图展示</a>
                </div>
                <div class="collapse" id="ddd2">
                    <div class="panel-body" >
                       <a href="javascript:$('#nmm').load('index.jsp')">展示轮播图</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <a class="panel-title" href="#ddd3" data-toggle="collapse" data-parent="#pg1">专辑管理</a>
                </div>
                <div class="collapse" id="ddd3">
                    <div class="panel-body" >
                        <a href="javascript:$('#nmm').load('index2.jsp')">展示专辑</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a class="panel-title" href="#ddd4" data-toggle="collapse" data-parent="#pg1">文章管理</a>
                </div>
                <div class="collapse" id="ddd4">
                    <div class="panel-body" >
                        <a href="javascript:$('#nmm').load('/guru/article.jsp')">展示文章</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-info">
                <div class="panel-heading">
                    <a class="panel-title" href="#ddd" data-toggle="collapse" data-parent="#pg1">用户管理</a>
                </div>
                <div class="collapse" id="ddd">
                    <div class="panel-body" >
                        <a href="javascript:$('#nmm').load('/user/index.jsp')">展示用户</a>
                        <br>
                        <a href="javascript:$('#nmm').load('/user/index1.jsp')">用户趋势图</a>
                        <br>
                        <a href="javascript:$('#nmm').load('/user/user-map.jsp')">用户分布图</a>
                    </div>
                </div>
            </div>


        </div>

    </div>
    <div class="col-md-9"  style="margin:80px 0 0 20px;" id="nmm">
        <div class="jumbotron" style="height:190px;">
            <h2 align="center">欢迎来到持明法州管理系统</h2>
        </div>

        <!-- 轮播图 -->
        <div class="carousel slide" data-ride="carousel" data-interval="1000">
            <div class="carousel-inner">
                <div class="item active">
                    <img src="img/q.png" alt="1">
                </div>
                <div class="item">
                    <img src="img/q.png" alt="2">
                </div>
                <div class="item">
                    <img src="img/q.png" alt="3">
                </div>
                <div class="item">
                    <img src="img/q.png" alt="4">
                </div>
            </div>
        </div>
    </div>



</div>
<div style="width: 100%;height:60px;line-height: 60px; background: #eee;margin-top:290px;">
    <p align="center">持明法州后台管理系统@百知教育2017.7.9</p>
</div>
<script type="text/javascript">
    $("#cmm").click(function (){
            alert("已退出");
            window.location.href="/admin/remove";
    })
</script>
</body>
</html>