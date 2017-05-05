$(function(){
    var $category = $('#comment');
    $category.hide();							   //隐藏上面获取的jQuery对象
    var $toggleBtn = $('#clickComment');
    $toggleBtn.click(function(){				   //添加onclick事件
        if($category.is(":visible")){				//如果显示
            $category.hide();
        }else {
            $category.show();
        }
    });
});



$(function(){
    $.ajax({
        type: "get",
        url: "/comment/list/"+itemId,
        dataType: 'json',
        data: {
        },
        success: function (data) {
            if (data == null) {
                $('#total').append("共有0条评论");
            } else {
                $('#total').append("共有" + data.length + "条评论");
                conn = "";
                $.each(data, function (index, eve) {
                    var newTime = new Date(eve.mCommentData).toLocaleString(); //转换时间
                    var star ='';                           //星星
                    for (var i =0;i<eve.mStart;i++)
                    {
                        star += '★';
                    }
                    conn += '<tr>';
                    conn += '<td width="8%" align="left" class="titlegrey">用户名：	    </td>';
                    conn += '<td width="23%" align="left" class="titlegrey">' + eve.mUserName + '</td>';
                    conn += ' <td width="10%" align="left" class="titlegrey">发表时间：</td>';
                    conn += '<td width="18%" align="left" class="titlegrey">' + newTime + '</td>';
                    conn += '<td width="6%" align="left" class="titlegrey">打分：</td>';
                    conn += ' <td width="28%" align="left" class="titlegrey">' +star + '</td>';
                    conn += '<td width="7%" align="left" class="titlegrey">&nbsp;</td>';
                    conn += '</tr><tr>';
                    conn += ' <td colspan="7" align="left" class="inputContent">' + eve.mContext + '</td>';
                    conn += '<tr>';
                });
                $('#tabcomment').html(conn);
            }
        }
    });
});
