<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>B2C商城</title>
<link href="../../resources/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.2.min.js"></script>
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<table width="770" border="0" cellpadding="0" cellspacing="0" class="pagetop">
<tr>
<td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="pagetopLine">
  <tr>
    <td><img src="../../resources/graphics/space.gif" width="1" height="1"></td>
  </tr>
</table><table width="100%" border="0" cellpadding="0" cellspacing="0" class="pagetop">
 
<tr>
	
<td width="124"><img src="../../resources/graphics/cc_01.gif"></td>
<td align="right" valign="top" class="help">&nbsp;</td>
<td style="text-align: right">管理员：${sessionScope.username} <a href="/logout" target="_top">退出</a></td>
</tr>
</table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td height="25" class="menuMain">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td class="menuMain1"><table width="560" height="16" border="0" cellpadding="0" cellspacing="0">
              <tr align="center">
                <td width="46" class="menuLinkbgSel"><a href="/" class="menuLinkSel" target="_parent">首页</a></td>
                <td width="2"><img src="../graphics/menu_l.gif" width="2" height="23"></td>
               
                <td width="81" class="menuLinkbg"><a href="/item_list" class="menuLink" target="_parent">商品管理</a></td>
                <td width="2"><img src="../../resources/graphics/menu_l.gif" width="2" height="23"></td>
                 <td width="81" class="menuLinkbg"><a href="/item_add" class="menuLink" target="_parent">添加商品</a></td>
                <td width="2"><img src="../../resources/graphics/menu_l.gif" width="2" height="23"></td>
               
                <td width="69" class="menuLinkbg"><a href="/salesdata_admin" class="menuLink" target="_parent">订单管理</a></td>
                <td width="2"><img src="../../resources/graphics/menu_l.gif" width="2" height="23"></td>
				 <td width="85" class="menuLinkbg"><a href="/user_admin" class="menuLink" target="_parent">用户管理</a></td>
				 <td width="2"><img src="../../resources/graphics/menu_l.gif" width="2" height="23"></td>
                 <td width="82" align="left" class="menuLinkbg">&nbsp;</td>
              </tr>
            </table>            
            </td>
          </tr>
    </table></td>
    </tr>
</table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><img src="../../resources/graphics/whole.jpg"></td>
    </tr>
  </table><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="3" background="../../resources/graphics/whole_line.gif"><img src="../../resources/graphics/space.gif" width="1" height="1"></td>
          </tr>
  </table></td>
</tr>
</table>
</body>
</html>