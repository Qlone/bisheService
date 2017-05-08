<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>B2C</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themes/default/easyui.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui-lang-zh_CN.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function() {
            var pager = $('#orderList').datagrid().datagrid('getPager');	// get the pager of datagrid
            function getSelectionsIds(){                             //获取ID
                var orderList = $("#orderList");
                var sels = orderList.datagrid("getSelections");
                var ids = [];
                for(var i in sels){
                    ids.push(sels[i].mOrdersId);
                }
                ids = ids.join(",");
                return ids;
            }
            pager.pagination({
                buttons: [
                    {
                        text: '发货',
                        iconCls:'icon-edit',
                        handler: function () {
                            var ids = getSelectionsIds();
                            if (ids.length == 0) {
                                $.messager.alert('提示', '未选中订单!');
                                return;
                            }
                            $.messager.confirm('确认', '确定发送ID为'+ids+'的订单吗？', function (r) {
                                if (r) {
                                    var params = {"ids": ids};
                                    $.post("/send/item", params, function (data) {
                                        if (data.status == 200) {
                                            $.messager.alert('提示', '商品发送成功!', undefined, function () {
                                                $("#orderList").datagrid("reload");
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    }
                ]
            });
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
            <td height="17"><a href="/salesdata_admin">订单查看</a> &raquo; 未处理订单</td>
        </tr>
        <tr>
            <table class="easyui-datagrid" id="orderList" title="订单列表"
                   data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:20,pageList: [10, 20, 30, 50],striped : true,toolbar:toolbar">
                <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <th data-options="field:'mReciver'">收件人</th>
                    <th data-options="field:'mTitle'">商品</th>
                    <th data-options="field:'mPrice'">价格</th>
                    <th data-options="field:'mAmount'">数量</th>
                    <th data-options="field:'mPhone'">电话</th>
                    <th data-options="field:'mAddress'">地址</th>
                    <th data-options="field:'mPaidTime'">付款时间</th>
                </tr>
                </thead>
            </table>
</tr>
</table>
 </div>
<div class="foot">
	<iframe src="/bottom" frameborder="0" marginheight="0" marginwidth="0" width="770" height="50" scrolling="no">
	</iframe>
</div>
</body>
</html>
