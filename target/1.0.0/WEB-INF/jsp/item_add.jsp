<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>B2C</title>
    <script type="text/javascript" src="../../resources/js/jquery-1.8.2.min.js"></script>
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../../resources/js/cm_addins.js"></script>
    <script type="text/javascript" src="../../resources/js/checkform.js"></script>
    <script type="text/javascript">
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
                <td height="17"><a href="/">主页</a>&raquo;增加商品</td>
            </tr>
        </table>

        <table width="770" border="0" cellpadding="0" cellspacing="0" class="main">
        <tr valign="top">
         <td>
         <form action="/item/add" method="post"  name="form1" enctype="multipart/form-data" onsubmit="return checkaddform()">
          <table width="100%" border="0" cellpadding="2" cellspacing="1" class="inputTable">
           <tr> <td class="inputTitle">增加商品</td></tr>
            <tr>
             <td>
                <table width="100%" border="0" cellpadding="0" cellspacing="1" class="inputbox">
                <tr>
              <td width="4%" class="inputHeader">&nbsp;</td>
                    <td width="17%" class="inputHeader">商品标题：</td>
                                            <td width="38%" class="inputContent">
                                                <input type="Text"  name="title" class="text width100" maxlength="25"/>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td width="4%" class="inputHeader">&nbsp;</td>
                                            <td width="17%" class="inputHeader">商品标签：</td>
                                            <td width="38%" class="inputContent">
                                                <input type="Text" name="type" class="text width100"  maxlength="25"/>
                                            </td>
                                        </tr>


                                        <tr>
                                            <td class="inputHeader">&nbsp;</td>
                                            <td class="inputHeader">商品图片：</td>
                                            <td class="inputContent">&nbsp;
                                             <div  id="preview">
                                             <img id="imghead" width="69" height="100" border=0 /></div>
                                             <input type="file" name="thempic" size="30" onchange="previewImage(this)"/>   </td>
                                            <td class="inputContent">&nbsp;</td>
                                        </tr>

                                        <tr>
                                            <td class="inputHeader">&nbsp;</td>
                                            <td align="left" class="inputHeader">多组图片：</td>
                                            <td align="left" class="inputHeader">
                                                <div style="margin :0px auto; width:500px;">
                                            <input type="file" name="picturegroup" multiple accept="image/*" onchange="javascript:setImagePreviews()" id="doc"/>
                                                    <div id="dd" style=" width:500px;"></div></div></td>
                                        </tr>

                                        <tr>
                                            <td class="inputHeader">&nbsp;</td>
                                            <td align="left" class="inputHeader">售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</td>
                                            <td align="left" class="inputHeader"><input type="Text" name="price" size="8" /></td>
                                        </tr>

                                        <tr>
                                            <td class="inputHeader">&nbsp;</td>
                                            <td align="left" class="inputHeader">库&nbsp;&nbsp;存&nbsp;&nbsp;量：</td>
                                            <td align="left" class="inputHeader"><input type="Text" name="stock" size="8"/></td>
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
                                               </td>
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