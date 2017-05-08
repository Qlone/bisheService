<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>B2C</title>
    <link href="../../resources/css/style.css"rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../../resources/js/jquery-1.8.2.min.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div>
    <iframe src="/top" frameborder="0" marginheight="0" marginwidth="0" width="770" height="130" scrolling="no">
    </iframe>
</div>
<div class="table_div">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="yrh">
        <tr>
            <td height="17"><a href="/">主页</a> &raquo;订单查看</td>
        </tr>
    </table>
    <table width="770" border="0" cellpadding="0" cellspacing="0" class="main">
        <tr valign="top">
            <td><form action="" method="post" enctype="multipart/form-data" name="form1">
                <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
                    <tr>
                        <td class="inputTitle" id="tit"></td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="inputTable" id="showdetile">
                                <tr>
                                    <td><a href="/order_list" style="font-size: 20px; text-decoration: none">所有订单</a> </td>
                                    <td><a href="/order_admin" style="font-size: 20px;text-decoration: none">未处理订单</a> </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </table>
            </form>
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