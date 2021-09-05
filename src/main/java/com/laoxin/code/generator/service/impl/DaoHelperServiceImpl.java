package com.laoxin.code.generator.service.impl;

import com.laoxin.code.generator.dto.DaoEnvDTO;
import com.laoxin.code.generator.dto.TableDTO;
import com.laoxin.code.generator.enums.CodeLanguageEnum;
import com.laoxin.code.generator.service.DaoHelperService;
import com.laoxin.code.generator.service.TableService;
import com.laoxin.code.generator.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;


@Service
public class DaoHelperServiceImpl implements DaoHelperService {


    @Override
    public boolean support(String language) {
        return CodeLanguageEnum.java.name().equalsIgnoreCase(language);
    }

    @Override
    public void genernate(DaoEnvDTO dto) throws Exception {
        TableService tableService = new TableServiceImpl().init(dto.getDriverClassName());
        String tables = dto.getTables();
        String basePath = dto.getBasePath();
        Set<String> genernateModules = dto.transferGenernateModules();
        for (String name : StringUtils.split(tables, ",")) {
            TableDTO tableDTO = tableService.getTableDTO(dto, name);
            tableDTO.setModuleName(dto.getModuleName());
            tableDTO.setBasePackage(dto.getBasePackage());
            tableDTO.setPackageName(dto.getMapperPackage());
            tableDTO.setJdbcType(dto.transferJdbcType());
            String srcPath = basePath + File.separator + "src";
            //entity直接覆盖
            genernateEntity(dto,tableDTO,srcPath);
            //Mapper
            genernateMapper(dto,tableDTO,srcPath);
            //daomanager
            genernateDaomanager(dto,tableDTO,srcPath);
            //daomanagerImpl
            genernateDaomanagerImpl(dto,tableDTO,srcPath);
            //xml
            genernateXml(dto,tableDTO,srcPath);
            
            if(genernateModules.contains("service") || genernateModules.contains("controller")){
                //DTO
                genernateDTO(dto,tableDTO,srcPath);
                //VO
                genernateVO(dto,tableDTO,srcPath);
                //service
                genernateService(dto,tableDTO,srcPath);
                if(genernateModules.contains("controller")){
                    //controller
                    genernateController(dto,tableDTO,srcPath);
                }
            }
        }
    }

    private void genernateEntity(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getMapperPackage();
        String entityString = Utils.createContentByTemplate(tableDTO, dto.getMapperPackage(), "entity.template");
        //entity直接覆盖
        String entityPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "entity" + File.separator  + dto.getModuleName() + File.separator + tableDTO.getJavaClassName() + "Entity.java";
        Utils.createFile(entityPath, entityString);
    }

    private void genernateMapper(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getMapperPackage();
        String daoString = Utils.createContentByTemplate(tableDTO, packageName, "mapper.template");
        //mapper
        String daoPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "mapper" + File.separator  + dto.getModuleName() + File.separator + tableDTO.getJavaClassName() + "Mapper.java";
        File file = new File(daoPath);
        if (!file.exists()) {
            Utils.createFile(daoPath, daoString);
        }
    }

    private void genernateDaomanager(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getMapperPackage();
        String daomanager = Utils.createContentByTemplate(tableDTO, packageName, "daomanager.template");
        //daomanager
        String daomanagerPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "daomanager" + File.separator  + dto.getModuleName() + File.separator + tableDTO.getJavaClassName() + "DaoManager.java";
        File file = new File(daomanagerPath);
        if (!file.exists()) {
            Utils.createFile(daomanagerPath, daomanager);
        }
    }

    private void genernateDaomanagerImpl(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getMapperPackage();
        String daomanagerImpl = Utils.createContentByTemplate(tableDTO, packageName, "daomanagerImpl.template");
        //daomanagerImpl
        String daomanagerImplPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "daomanager" + File.separator  + dto.getModuleName() + File.separator +"impl" + File.separator + tableDTO.getJavaClassName() + "DaoManagerImpl.java";
        File file = new File(daomanagerImplPath);
        if (!file.exists()) {
            Utils.createFile(daomanagerImplPath, daomanagerImpl);
        }
    }

    private void genernateXml(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getMapperPackage();
        String xmlString = Utils.createContentByTemplate(tableDTO, packageName, "xml.template");
        //xml
        String xmlPath = srcPath + File.separator + "main" + File.separator + "resources" + File.separator + "mybatis" + File.separator +dto.getModuleName() + File.separator + tableDTO.getJavaClassName() + "Mapper.xml";
        File file = new File(xmlPath);
        if (!file.exists()) {
            Utils.createFile(xmlPath, xmlString);
        }
    }

    private void genernateDTO(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getBasePackage();
        String moudleName = dto.getModuleName();
        //dto vo放在api服务路径
        srcPath = srcPath.replace(File.separator,"/");
        String[] split = srcPath.split("/");
        String serviceName = split[split.length-2];
        String apiName = serviceName.replace("service","api");
        srcPath = srcPath.replace(serviceName,apiName);

        String listString = Utils.createContentByTemplate(tableDTO, packageName, "dto/list.template");
        //listString
        String listPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "dto" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "ListDTO.java";
        File file = new File(listPath);
        if (!file.exists()) {
            Utils.createFile(listPath, listString);
        }

        String pageString = Utils.createContentByTemplate(tableDTO, packageName, "dto/page.template");
        //pageString
        String pagePath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "dto" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "PageDTO.java";
        file = new File(pagePath);
        if (!file.exists()) {
            Utils.createFile(pagePath, pageString);
        }

        String saveString = Utils.createContentByTemplate(tableDTO, packageName, "dto/save.template");
        //saveString
        String savePath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "dto" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "SaveDTO.java";
        file = new File(savePath);
        if (!file.exists()) {
            Utils.createFile(savePath, saveString);
        }

        String updateString = Utils.createContentByTemplate(tableDTO, packageName, "dto/update.template");
        //updateString
        String updatePath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "dto" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "UpdateDTO.java";
        file = new File(updatePath);
        if (!file.exists()) {
            Utils.createFile(updatePath, updateString);
        }
    }

    private void genernateVO(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getBasePackage();
        String moudleName = dto.getModuleName();
        String voString = Utils.createContentByTemplate(tableDTO, packageName, "vo/vo.template");
        //dto vo放在api服务路径
        srcPath = srcPath.replace(File.separator,"/");
        String[] split = srcPath.split("/");
        String serviceName = split[split.length-2];
        String apiName = serviceName.replace("service","api");
        srcPath = srcPath.replace(serviceName,apiName);
        //voString
        String voPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "vo" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "VO.java";
        File file = new File(voPath);
        if (!file.exists()) {
            Utils.createFile(voPath, voString);
        }
    }

    private void genernateService(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getBasePackage();
        String moudleName = dto.getModuleName();
        String serviceString = Utils.createContentByTemplate(tableDTO, dto.getMapperPackage(), "service.template");
        //serviceString
        String servicePath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "service" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "Service.java";
        File file = new File(servicePath);
        if (!file.exists()) {
            Utils.createFile(servicePath, serviceString);
        }
    }

    private void genernateController(DaoEnvDTO dto,TableDTO tableDTO,String srcPath) throws Exception {
        String packageName = dto.getBasePackage();
        String moudleName = dto.getModuleName();
        String controllerString = Utils.createContentByTemplate(tableDTO, dto.getMapperPackage(), "controller.template");
        //controllerString
        String controllerPath = srcPath + File.separator + "main" + File.separator + "java" + File.separator + (packageName.replaceAll("\\.", "/")) + File.separator + "controller" + File.separator +moudleName + File.separator + tableDTO.getJavaClassName() + "Controller.java";
        File file = new File(controllerPath);
        if (!file.exists()) {
            Utils.createFile(controllerPath, controllerString);
        }
    }
}
