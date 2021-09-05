package com.laoxin.code.generator.service.impl;



import com.laoxin.code.generator.dto.DaoEnvDTO;
import com.laoxin.code.generator.dto.FieldDTO;
import com.laoxin.code.generator.dto.FieldTypeEnum;
import com.laoxin.code.generator.dto.TableDTO;
import com.laoxin.code.generator.service.TableService;
import com.laoxin.code.generator.util.Utils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Set;


public class TableServiceImpl implements TableService {


    public TableService init(String driverClassName)throws Exception{
        Class.forName(driverClassName);
        return this;
    }

    @Override
    public TableDTO getTableDTO(DaoEnvDTO dto, String tableName) throws Exception {
        String schema = dto.getSchema();
        Connection conn = DriverManager.getConnection(dto.getUrl(), dto.getUsername(), dto.getPassword());
        DatabaseMetaData dbMetaData = conn.getMetaData();
        ResultSet rs = dbMetaData.getColumns(null, schema, tableName, "%");
        TableDTO tableDTO = new TableDTO();
        tableDTO.setDataBaseName(tableName);
        tableDTO.setSchema(schema);
        tableDTO.setGetMethodName(Utils.dbName2GetName(tableName));
        tableDTO.setSetMethodName(Utils.dbName2SetName(tableName));
        tableDTO.setJavaClassName(Utils.dbName2ClassName(tableName));
        tableDTO.setJavaFieldName(Utils.dbName2FieldName(tableName));
        tableDTO.setGoRepositoryClassName(Utils.dbName2FieldName(tableName));
        tableDTO.setGoServiceClassName(Utils.dbName2FieldName(tableName));
        Set<String> ingnoreFields = dto.transferIngoreEntityFileds();
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            String typeName = rs.getString("TYPE_NAME");
            Integer columnType = rs.getInt("DATA_TYPE");
            String remark = rs.getString("REMARKS");
            String fieldName = Utils.dbName2FieldName(columnName);
            if(ingnoreFields.contains(fieldName) || ingnoreFields.contains(columnName)){
                continue;
            }
            if (StringUtils.isNotBlank(remark)){
                remark = remark.replaceAll("\\s", "");
            }
            else {
                remark = "无";
            }
            String isNullable = rs.getString("IS_NULLABLE");

            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setDataBaseName(columnName);
            fieldDTO.setGetMethodName(Utils.dbName2GetName(columnName));
            fieldDTO.setSetMethodName(Utils.dbName2SetName(columnName));
            fieldDTO.setJavaClassName(Utils.dbName2ClassName(columnName));
            fieldDTO.setJavaFieldName(fieldName);
            fieldDTO.setGoFieldName(Utils.dbName2DOName(columnName,true));
            fieldDTO.setFieldDesc(remark);
            fieldDTO.setIsNullable(StringUtils.equals(isNullable, "NO") ? Boolean.FALSE : Boolean.TRUE);
            fieldDTO.setFieldType(FieldTypeEnum.getType(columnType.intValue()));
            if(fieldDTO.getFieldType() == null){
                System.out.println(columnName+"是特殊的字段类型，需要单独设置");
                continue;
            }
            if("id".equals(columnName)){
                tableDTO.setIdFieldType(fieldDTO.getFieldType());
            }
            tableDTO.getFieldList().add(fieldDTO);
        }
        conn.close();
        return tableDTO;
    }

}
