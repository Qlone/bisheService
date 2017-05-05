<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>B2C</title>
<script type="text/javascript" src="../../resources/js/jquery-1.8.2.min.js"></script>
<link href="../../resources/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../resources/js/menu.js"></script>
<script type="text/javascript">
    var itemId = ${good.goodsId};

    $(function(){
        var num = '${score}';
        var solid = parseInt(num);
        var star ='';
        for (var i =0;i<solid;i++)
        {
            star += '★';
        }
        if(solid != num){
            star += '☆';
        }
        $('#pinfen').append(star);
    });

    $(function () {
        var biaoqian = '${good.type}';
        var temp = biaoqian.substr(1,biaoqian.length);
        var t = temp.split("#");
        var bq = t.join(", ");
        $('#biao').append(bq);
    })
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
    <td height="17"><a href="home.htm">主页</a> &raquo; 商品详细信息</td>
  </tr>
</table>
<table width="770" border="0" cellpadding="0" cellspacing="0" class="main">
<tr valign="top">
<td><form action="" method="post" enctype="multipart/form-data" name="form1">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
    <tr>
      <td class="inputTitle">商品信息</td>
    </tr>
    <tr>
      <td align="left" class="inputHeader">
	   <table width="100%" border="0" cellpadding="0" cellspacing="1" class="inputbox">
         <tr>
            <td colspan="7" align="left" class="inputHeader"><font color="#CC0000"><b>${good.title}</b></font></td>
			</tr>
		   <tr>
            	<td colspan="2" rowspan="5" align="center" class="inputHeader"><img src="${good.picture}" alt="${good.title}" width="138" height="200" hspace="0" vspace="0" border="0"></td>
			<td width="15%" align="left" class="inputHeader">售&nbsp;&nbsp;&nbsp;&nbsp;价：￥${good.price}</td>
			<td colspan="4" align="left" class="inputHeader">&nbsp;</td>
			</tr>
			 
			 
			 <tr>
			   <td align="left" class="inputHeader">库存量：${good.stock}</td>
			   <td colspan="4" align="left" class="inputHeader">&nbsp;</td>
			   </tr>
			 <tr>
			   <td align="left" class="inputHeader" id="pinfen">用户评分：</td>
			   <td colspan="4"align="left" class="inputHeader">&nbsp;</td>
			   </tr>

           <tr>
               <td align="left" class="inputHeader" id="biao">商品标签：</td>
               <td colspan="4" align="left" class="inputHeader">&nbsp;</td>
           </tr>
            
        </table>	  
        </td>
    </tr>
    <tr>
      <td class="inputTitle"><a href="javascript:void (0)" id="clickComment">【查看用户详细评论】</a></td>
    </tr>
  </table>
  </form>  
</td>
</tr>
</table>
 </div>








<div id="comment">

    <table width="770" border="0" cellpadding="0" cellspacing="0" class="table_div">
        <tr valign="top">
            <td>
                <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
                    <tr>
                        <td class="inputTitle" id="total"></td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="inputTable" id="tabcomment">

                            </table>
                        </td>
                    </tr>

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
