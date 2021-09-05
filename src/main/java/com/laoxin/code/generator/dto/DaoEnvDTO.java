package com.laoxin.code.generator.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Data
public class DaoEnvDTO {

    @Value(value = "${dao.language:java}")
    private String codeLanguage;

    @Value(value = "${dao.basePath}")
    private String basePath;

    @Value("${dao.basePackage}")
    private String basePackage;

    @Value("${dao.mapperPackage}")
    private String mapperPackage;

    @Value("${dao.databaseName}")
    private String databaseName;

    @Value("${dao.moduleName}")
    private String moduleName;

    @Value("${dao.schema}")
    private String schema;

    @Value("${dao.tables}")
    private String tables;

    @Value("${dao.url}")
    private String url;

    @Value("${dao.username}")
    private String username;

    @Value("${dao.password}")
    private String password;

    @Value("${dao.driverClassName}")
    private String driverClassName;

    @Value("${dao.ingoreEntityFileds:@}")
    private String ingoreEntityFileds;

    @Value("${dao.genernateModules}")
    private String genernateModules;

    public Set<String> transferIngoreEntityFileds(){
        if(this.ingoreEntityFileds != null){
            String[] split = ingoreEntityFileds.split(",");
            if(null != split && split.length>0){
                return Stream.of(split).collect(Collectors.toSet());
            }
        }
        return new HashSet<>();
    }

    public Set<String> transferGenernateModules(){
        if(this.genernateModules != null){
            String[] split = genernateModules.split(",");
            if(null != split && split.length>0){
                return Stream.of(split).collect(Collectors.toSet());
            }
        }
        return new HashSet<>();
    }

    public String transferJdbcType(){
        if(driverClassName.contains("mysql")){
            return "mysql";
        }else if(driverClassName.contains("postgresql")){
            return "postgresql";
        }else if(driverClassName.contains("sqlserver")){
            return "sqlserver";
        }
        return driverClassName;
    }
}
