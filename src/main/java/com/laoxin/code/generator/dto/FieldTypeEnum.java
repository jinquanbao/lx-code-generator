package com.laoxin.code.generator.dto;




import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Types;

@Getter
@AllArgsConstructor
public enum FieldTypeEnum {
    Byte("byte []","[]byte"),
    Long("Long","int64"),
    Short("Integer","int8"),
    Integer("Integer","int32"),
    Smallint("Integer","int16"),
    Character("Character","char"),
    Date("java.time.LocalDateTime","*time.Time"),
    Double("Double","float64"),
    Float("Float","float32"),
    Number("Number","int"),
    String("String","string"),
    ;


	private final String fieldType;

	private final String goFieldType;

//    private FieldTypeEnum(String fieldType) {
//        this.fieldType = fieldType;
//    }



    public java.lang.String getFieldType() {
        return fieldType;
    }


    public static FieldTypeEnum getType(int type){
        switch (type){
            case Types.BINARY:
                return Byte;
            case Types.BIGINT:
                return Long;
            case Types.BIT:
                return Integer;
            case Types.CHAR:
                return Character;
            case Types.DATE:
                return Date;
            case Types.DOUBLE:
                return Double;
            case Types.FLOAT:
                return Float;
            case Types.INTEGER:
                return Integer;
            case Types.SMALLINT:
                return Smallint;
            case Types.NUMERIC:
                return Number;
            case Types.NVARCHAR:
                return String;
            case Types.TIME:
                return Date;
            case Types.TIMESTAMP:
                return Date;
            case Types.TINYINT:
                return Short;
            case Types.VARCHAR:
                return String;
            case Types.REAL:
                return Float;
            case Types.LONGVARCHAR:
                return String;
            case Types.LONGVARBINARY:
                return Byte;
            case Types.ARRAY:
                return null;
            case Types.OTHER:
                return String;
            default:
                return null;
                //throw new RuntimeException("不支持的sql类型:Type.ARRAY");
        }
    }
}
