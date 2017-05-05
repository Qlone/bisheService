    var pagination = {
        $divPagination: $('#pagination'), //分页控件容器
        $divDatagrid: $('#datagrid'), //数据容器
        urlRecordCount: "index.php?c=homepage&m=prizeRecordCount", //接口返回数据的总行数
        urlRecordLists: "index.php?c=homepage&m=prizeLogLists&pageIndex=", //接口返回JSON格式的数据
        callback: function(pageIndex, $divPagination) {
        $.getJSON(pagination.urlRecordLists + pageIndex, function(jsonRecordLists) {
        if (jsonRecordLists == undefined || jsonRecordLists.length == 0) {
        pagination.$divDatagrid.html('没有数据');
        return false;
        }

        html = '';
        $.each(jsonRecordLists, function(index, item) {
        html ='<table></table>'
        html += '奖品:' + item['prize_name'] + '    时间:' + item['create_time'] + '<br/>';
        });

        pagination.$divDatagrid.html(html);
        });
        return false;
        },
        initPagination: function() {
        $.getJSON(pagination.urlRecordCount, function(recordCount) {
        pagination.$divPagination.pagination(recordCount, {
        callback: pagination.callback,
        items_per_page: 10, //pageSize
        next_text: '下一页',
        next_show_always: false, //当下一页无法使用时是否显示,true(默认)
        prev_text: '上一页',
        prev_show_always: false,
        link_to: 'javascript:void(0);', //分页href中的内容,#(默认)
        num_display_entries: 11, //最多显示多少分页链接:11(默认),0(只显示上一下和下一页)
        num_edge_entries: 1, //1(任何情况下都显示第一页和最后一页),0(不显示)
        ellipse_text: '...', //当num_edge_entries>0时,分页使用的省略符号
        current_page: 0, //默认pageIndex,0(默认),false(不加载)
        load_first_page: true //第一次自动加载,true(默认),false(不加载)
        });
        });
        }
        };

    $(document).ready(function() {
        pagination.initPagination();//分页控件初始化
        });


