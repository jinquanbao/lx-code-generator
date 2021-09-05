package com.laoxin.code.generator.dto;

import java.util.ArrayList;
import java.util.List;

public class TableListDTO {

    private List<TableDTO> tableList = new ArrayList<>();


    public List<TableDTO> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableDTO> tableList) {
        this.tableList = tableList;
    }
}
