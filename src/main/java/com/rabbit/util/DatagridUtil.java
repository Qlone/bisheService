package com.rabbit.util;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */
public class DatagridUtil {
    private long total;
    private List<?> rows;



    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
