<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/17
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script>

    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id',{
            //相关参数
            width : '1000px',
            height: "500px",
            //展示图片空间
            allowFileManager:true,
            //图片空间对应的地址
            fileManagerJson:"${pageContext.request.contextPath}/article/browser",
            //上传图片的地址
            uploadJson:"${pageContext.request.contextPath}/article/upload",
            //上传图片时接受的参数
            filePostName:"articleImage"
        });
    });





    var goEasy = new GoEasy({
    //接收的appkey
        appkey: "BC-8add987f434a4520af4b3e88e7b54dfb"
    });
    goEasy.subscribe({
    //当前的channel名称
        channel: "cmfz-article",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
            KindEditor.html("#editor_id",message.content);
        }
    });
</script>

</body>
</html>
