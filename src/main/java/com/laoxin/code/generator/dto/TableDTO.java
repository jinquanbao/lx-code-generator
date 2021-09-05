package com.laoxin.code.generator.dto;

import java.util.ArrayList;
import java.util.List;

public class TableDTO extends NameDTO {

    private String packageName;

    private String basePackage;

    private String moduleName;

    protected String schema;

    private String tableDesc;

    private String jdbcType;

    private FieldTypeEnum idFieldType;

    private List<FieldDTO> fieldList=new ArrayList<>();


    public List<FieldDTO> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldDTO> fieldList) {
        this.fieldList = fieldList;
    }


    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public FieldTypeEnum getIdFieldType() {
        return idFieldType;
    }

    public void setIdFieldType(FieldTypeEnum idFieldType) {
        this.idFieldType = idFieldType;
    }
}
