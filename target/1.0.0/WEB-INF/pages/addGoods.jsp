<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  ~ Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
  ~ Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
  ~ Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
  ~ Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
  ~ Vestibulum commodo. Ut rhoncus gravida arcu.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/6
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
<form action="/home/add" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>商品标题</td>
            <td><input type="text" name="title" /></td>
        </tr>
        <tr>
            <td>商品类别</td>
            <td><select name="type">
                <option value="" selected>a</option>
                <option value="">b</option>
                <option value="">c</option>
                <option value="">d</option>
            </select></td>
        </tr>
        <tr>
            <td>商品状态</td>
            <td><select name="status">
                <option value="" selected>a</option>
                <option value="">b</option>
                <option value="">c</option>
                <option value="">d</option>
            </select></td>
        </tr>
        <tr>
        <td>商品价格</td>
        <td><input type="text" name="price" /></td>
        </tr>
        <tr>
            <td>商品库存</td>
            <td><input type="text" name="stock" /></td>
        </tr>
        <tr>
            <td>商品描述</td>
            <td><input type="textarea" name="describe" /></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td><img src="/" width="100" height="100" /><br />
            <input type="file" name="pictureGroup" multiple /></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td><img src="/" width="100" height="100" /><br />
                <input type="file" name="picture"  /></td>
        </tr>
        <tr>
            <td><input type="submit" /></td>
        </tr>
    </table>
</form>

</body>
</html>
