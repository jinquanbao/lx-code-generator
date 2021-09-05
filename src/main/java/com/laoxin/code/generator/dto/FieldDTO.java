package com.laoxin.code.generator.dto;

public class FieldDTO extends NameDTO {

    private FieldTypeEnum fieldType;

    private String fieldDesc;

    private Boolean isNullable;


    public FieldTypeEnum getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldTypeEnum fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean nullable) {
        isNullable = nullable;
    }

    @Override
    public String toString()
    {
        return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this);
    }
}
