package net.solooo.demo.other.json.entity;

import java.util.List;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/14
 */
public class SubTableInfo extends SubTable {

    private List<SubColumn> columns;

    public List<SubColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<SubColumn> columns) {
        this.columns = columns;
    }
}
