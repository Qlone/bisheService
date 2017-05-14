 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%--
  ~ Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
  ~ Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
  ~ Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
  ~ Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
  ~ Vestibulum commodo. Ut rhoncus gravida arcu.
  --%>


<html>
<head>
    <title>B2C</title>
    <script src="../../resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="../../resources/js/jquery.simplemodal.js" type="text/javascript"></script>
    <link href="../../resources/css/basic.css" rel="stylesheet" type="text/css" media="screen">
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css" >
    <script>
        $(function(){
            var all = '${users}';
            if (all == null|| all.length== 0)
            {
                $('#listItem').submit();
            }
        });


       <%--// $(function ($) {--%>
            <%--$('.basic').click(function (e) {--%>
                <%--$('#basic-modal-content').modal();--%>
                <%--$('#pay').attr("action","/user/pay");--%>
                <%--$('#name').append('${admin.balance}');--%>
                <%--return false;--%>
            <%--});--%>
        <%--});--%>
    </script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div id="container">
<div>
    <iframe src="/top" frameborder="0" marginheight="0" marginwidth="0" width="770" height="130" scrolling="no">
    </iframe>
</div>
<div class="table_div">

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="yrh">
        <tr>
            <td height="17"><a href="/">主页</a> &raquo;用户管理</td>
        </tr>
    </table>
    <table width="770" border="0" cellpadding="0" cellspacing="0" class="main">
        <tr valign="top">
            <td>
                <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
                    <tr>
                        <td class="inputTitle">用户列表</td>
                    </tr>
                    <form action="/user/list" method="post" enctype="multipart/form-data" name="form1" id="listItem">
                        <c:forEach items="${users}" var="admin" varStatus="status">
                    <tr>

                                            <td  align="center" class="inputHeader"><font color="#CC0000">${status.index+1}</font></td>
                                            <td  align="center" class="inputHeader">用户名： ${admin.userName}</td>
                                            <td  align="center" class="inputHeader">余额 ： ${admin.balance}</td>
                                            <td  align="center" class="inputHeader"><button onclick="javascript:$('#basic-modal-content').modal();
                $('#pay').attr('action','/user/pay/${admin.userId}');$('#name').append('${admin.userName}');return false;">充值</button></td>
                                        </tr>
                                    </c:forEach>

                            </form>
                            <div class="holder"></div>
                </table>

            </td>
        </tr>
    </table>
    <div id="basic-modal-content">
        <form method="post" id="pay">
            <h3>用户</h3> <h3 id="name"></h3> <h3>充值</h3>
            ￥<input type="text" name="money"/>
            <input type="submit" value="确认充值"/>
        </form>
    </div>
    <div style='display:none'>
        <img src='../../resources/graphics/x.png'/>
    </div>
</div>
<div id="foot">
    <iframe src="/bottom" frameborder="0" marginheight="0" marginwidth="0" width="770" height="50" scrolling="no">
    </iframe>
</div>
</div>
</body>
</html>