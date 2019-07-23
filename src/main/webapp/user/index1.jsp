
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

    <script src="/echarts/echarts.min.js"></script>
    <script src="/statics/boot/js/jquery-2.2.1.min.js"></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '用户趋势图'
            },
            tooltip: {},
            legend: {
                data:['用户']
            },
            xAxis: {
                data: ["一月","二月","三月","四月","五月","六月"]
            },
            yAxis: {},

        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);


        $.ajax({
            url:"${pageContext.request.contextPath}/user/title",
            type:"get",
            dataType:"json",
            success:function (response) {
                myChart.setOption({
                    series: [{
                        name: '用户',
                        type: 'line',
                        data: response
                    }]
                })
            }
        })


    </script>
</body>
</html>
