<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>B2C</title>
<script type="text/javascript" src="../../resources/js/jquery-1.8.2.min.js"></script>
<link href="../../resources/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../resources/js/checkform.js"></script>
    <style type="text/css">
        #preview{width:69px;height:100px;border:1px solid #000;overflow:hidden;}
        #imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
    </style>
<script>
    $(function(){
        var statusval = '${good.status}';
        if(statusval.length ==0||statusval==null) {
            $('#selectStatus').val('');
        }else if(statusval == 'hot'){
            $('#selectStatus').val('hot');
        }else{
            $('#selectStatus').val('recommend');
        }
    });

    //图片上传预览    IE是用了滤镜。
    function previewImage(file)
    {
        var MAXWIDTH  = 69;
        var MAXHEIGHT = 100;
        var div = document.getElementById('preview');
        if (file.files && file.files[0])
        {
            div.innerHTML ='<img id=imghead>';
            var img = document.getElementById('imghead');
            img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
            }
            var reader = new FileReader();
            reader.onload = function(evt){img.src = evt.target.result;}
            reader.readAsDataURL(file.files[0]);
        }
        else //兼容IE
        {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
        }
    }
    function clacImgZoomParam( maxWidth, maxHeight, width, height ){
        var param = {top:0, left:0, width:width, height:height};
        if( width>maxWidth || height>maxHeight )
        {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;

            if( rateWidth > rateHeight )
            {
                param.width =  maxWidth;
                param.height = Math.round(height / rateWidth);
            }else
            {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }

        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }


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
    <td height="17"><a href="/item_list">商品列表</a>&raquo;商品编辑</td>
  </tr>
</table>
<table width="770" border="0" cellpadding="0" cellspacing="0" class="main">
<tr valign="top">
<td>
<form action="/item/edit/update" method="post" enctype="multipart/form-data" name="form1" onsubmit="return checkitemform()">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
    

    <tr>
      <td class="inputTitle">编辑商品</td>
        <input type="hidden" name="goodsId" value="${good.goodsId}"/>

    </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="inputbox">
          <tr>
            <td width="4%" class="inputHeader">&nbsp;</td>
            <td width="17%" class="inputHeader">商品标题：</td>
            <td width="38%" class="inputContent">
              <input type="Text" class="text width100" name="title" value="${good.title}" maxlength="25"/>
            </td>
          </tr>

            <tr>
                <td width="4%" class="inputHeader">&nbsp;</td>
                <td width="17%" class="inputHeader">商品标签：</td>
                <td width="38%" class="inputContent">
                    <input type="Text" class="text width100" name="type" value="${good.type}" maxlength="25"/>
                </td>
            </tr>

            <tr>
                <td width="4%" class="inputHeader">&nbsp;</td>
                <td width="17%" class="inputHeader">已付款人数：</td>
                <td width="38%" class="inputContent">
                  ${good.views}
                </td>
            </tr>


            <tr>
                <td width="4%" class="inputHeader">&nbsp;</td>
                <td width="17%" class="inputHeader">商品状态：</td>
                <td width="38%" class="inputContent">
                    <select class="text width100" id="selectStatus" name="status">
                        <option value="">请添加。。</option>
                        <option value="hot">hot</option>
                        <option value="recommend">recommend</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td width="4%" class="inputHeader">&nbsp;</td>
                <td width="17%" class="inputHeader">商品销量：</td>
                <td width="38%" class="inputContent">
                ${good.sales}
                </td>
            </tr>



            <tr>
                <td class="inputHeader">&nbsp;</td>
                <td class="inputHeader">商品图片：</td>
                <td class="inputContent">&nbsp;
                    <div  id="preview">
                        <img id="imghead" width="69" height="100" border=0 src="${good.picture}"/></div>
                    <input type="file" name="themeImg" size="30" onchange="previewImage(this)"/>   </td>
                <td class="inputContent">&nbsp;</td>
            </tr>
          <tr>
            <td class="inputHeader">&nbsp;</td>
            <td align="left" class="inputHeader">售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</td>
            <td align="left" class="inputHeader"><input type="Text" name="price" size="8" value="${good.price}"/></td>
          </tr>
          <tr>
            <td class="inputHeader">&nbsp;</td>
            <td align="left" class="inputHeader">库&nbsp;&nbsp;存&nbsp;&nbsp;量：</td>
            <td align="left" class="inputHeader"><input type="Text" name="stock" size="8" value="${good.stock}"/></td>
          </tr>
          
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr>
            <td width="4%" class="inputContent">&nbsp;</td>
            <td width="17%" class="inputHeader">&nbsp;</td>
            <td width="39%" class="inputContent">
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;
&nbsp;&nbsp;
<input type="Reset" class="bt2" name="button1" value="重填" onClick="clear()">
&nbsp;
&nbsp;
&nbsp;
<input type="submit" class="bt2" name="button2" value="提交" />
&nbsp;
&nbsp;
&nbsp;            <input type="Button" class="bt2" name="button22" value="返回" onClick="javascript:window.location.href='/item_list'"></td>
            <td width="40%" class="inputContent">&nbsp;</td>
          </tr>
        </table></td>
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
