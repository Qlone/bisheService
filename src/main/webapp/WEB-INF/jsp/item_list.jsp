<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>B2C</title>
    <script src="../../resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css">

    <script src="../../resources/js/checkform.js" type="text/javascript"></script>
    <script>
        $(function(){
            var all = '${alladmin}';
            if (all == null|| all.length== 0)
            {
                $('#listItem').submit();
            }
        });
    </script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<div>
    <iframe src="/top" frameborder="0" marginheight="0" marginwidth="0" width="770" height="130" scrolling="no">
    </iframe>
</div>
<div class="table_div">

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="yrh">
        <tr>
            <td height="17"><a href="/">主页</a> &raquo;商品管理</td>
        </tr>
    </table>
    <table width="770" border="0" cellpadding="0" cellspacing="0" class="main">
        <tr valign="top">
            <td>
                <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
                    <tr>
                        <td class="inputTitle">商品列表</td>
                    </tr>
                    <tr>
                        <td class="inputHeader">



                            <form action="/item/list" method="post" enctype="multipart/form-data" name="form1" id="listItem">
                                <table width="100%" border="0" cellpadding="0" cellspacing="1" class="inputbox" id="itemContainer">
                                    <c:forEach items="${alladmin}" var="admin" varStatus="status">
                                        <tr>
                                            <td width="5%" rowspan="4" align="center" class="inputHeader"><font color="#CC0000">${status.index+1}</font></td>
                                            <td width="19%" rowspan="4" align="center" class="inputHeader"><a href="/item/info/${admin.goodsId}"><img src="${admin.picture}" alt="${admin.title}" width="69" height="100" border="0"></a></td>
                                            <td width="76%" align="left" class="inputHeader"><font color="#CC0000"><b>${admin.title}</b></font></td>
                                        </tr>
                                        <tr>
                                            <td align="left" class="inputHeader">售价：￥${admin.price}</td>
                                        </tr>
                                        <tr>
                                            <td align="left" class="inputHeader">库存：${admin.stock}</td>
                                        </tr>
                                        <tr>
                                            <td align="left" class="inputHeader"><a href="/item/edit/${admin.goodsId}"><img src="../../resources/graphics/bt_change.gif" alt="修改" width="37" height="19" border="0"></a>&nbsp;&nbsp;
                                                <a href="#"  onClick="javascript:if(window.confirm('确定删除？')) window.location.href='/item/delete/${admin.goodsId}';else return false;"><img src="../../resources/graphics/bt_delete.gif" alt="删除" width="37" height="19" border="0"></a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </form>
                            <div class="holder"></div>
                </table>

            </td>
        </tr>
    </table>
</div>
<div id="foot">
    <iframe src="/bottom" frameborder="0" marginheight="0" marginwidth="0" width="770" height="50" scrolling="no">
    </iframe>
</div>
</body>
</html>