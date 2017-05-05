$(function () {
    $('#exel').hide();
    var $button1 = $('#saleANDview');
    $button1.click(function(){
        if($button1.text() == "销售量统计"){
            $('#tit').text("销售量统计");
            $button1.text("浏览量统计");
            var url = "/show/sale";
            showsale(url,getsale);
            $('#exel').show();
        }else if($button1.text() == "浏览量统计") {
            $('#tit').text("浏览量统计");
            var url = "/show/view";
            showsale(url,getview);
            $button1.text("销售量统计");
        }
    return false;
    });
});

function showsale(url,func){
    $.ajax({
        type: "get",
        url: url,
        dataType: 'json',
        data: {
        },
        success: function (data) {
            if (data == null){
                $('showdetile').html("<tr><td>最近没有新的数据</td></tr>");
            }else{
                var str = func(data);
                $("#showdetile").append(str);
            }
        }
    });
}

function getsale(data){
    var conn = '<tr><td width="20%">排名</td><td width="40%">商品名</td><td width="40%">销量</td></tr>';
    $.each(data, function (index, eve) {
       conn += '<tr>';
       conn += '<td width="20%">'+index+'</td>';
       conn += '<td width="40%">'+eve.tit1e+'</td>';
       conn += '<td width="40%">'+eve.sales+'</td>';
    });
    return conn;
}
function getview(data){
    var conn = '<tr><td width="20%">排名</td><td width="40%">商品名</td><td width="40%">浏览量</td></tr>';
    $.each(data, function (index, eve) {
        conn += '<tr>';
        conn += '<td width="20%">'+index+'</td>';
        conn += '<td width="40%">'+eve.tit1e+'</td>';
        conn += '<td width="40%">'+eve.views+'</td>';
    });
    return conn;
}