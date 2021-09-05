package com.laoxin.code.generator.service.impl;

import com.laoxin.code.generator.dto.DaoEnvDTO;
import com.laoxin.code.generator.dto.TableDTO;
import com.laoxin.code.generator.dto.TableListDTO;
import com.laoxin.code.generator.enums.CodeLanguageEnum;
import com.laoxin.code.generator.service.DaoHelperService;
import com.laoxin.code.generator.service.TableService;
import com.laoxin.code.generator.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GoDaoHelperServiceImpl implements DaoHelperService {


    @Override
    public boolean support(String language) {
        return CodeLanguageEnum.go.name().equalsIgnoreCase(language);
    }

    @Override
    public void genernate(DaoEnvDTO dto) throws Exception {
        TableService tableService = new TableServiceImpl().init(dto.getDriverClassName());
        String tables = dto.getTables();
        String basePath = dto.getBasePath();
        Set<String> genernateModules = dto.transferGenernateModules();
        TableListDTO tableListDTO = new TableListDTO();
        List<TableDTO> tableDTOs = new ArrayList<>();
        for (String name : StringUtils.split(tables, ",")) {
            TableDTO tableDTO = tableService.getTableDTO(dto, name);
            tableDTO.setBasePackage(dto.getBasePackage());
            genernateModel(tableDTO,basePath);
            //genernateDTO(tableDTO,basePath);
            //genernateVO(tableDTO,basePath);
            genernateDao(tableDTO,basePath);
            //genernateRepository(tableDTO,basePath);
            genernateService(tableDTO,basePath);
            tableDTOs.add(tableDTO);
        }
        tableListDTO.setTableList(tableDTOs);
    }

    private void genernateModel(TableDTO tableDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableDTO,"protos",  "go/model.template");
        //model直接覆盖
        String path = srcPath +File.separator +"protos" + File.separator+tableDTO.getDataBaseName() + ".model.go";
        Utils.createFile(path, content);
    }

    private void genernateDTO(TableDTO tableDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableDTO,"protos",  "go/request.template");
        String path = srcPath +File.separator +"protos"+ File.separator+tableDTO.getDataBaseName() + ".request.go";
        File file = new File(path);
        if (!file.exists()) {
            Utils.createFile(path, content);
        }
    }

    private void genernateVO(TableDTO tableDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableDTO,"protos",  "go/response.template");
        String path = srcPath +File.separator +"protos"+ File.separator+tableDTO.getDataBaseName() + ".response.go";
        File file = new File(path);
        if (!file.exists()) {
            Utils.createFile(path, content);
        }
    }

    private void genernateDao(TableDTO tableDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableDTO,"dao",  "go/dao.template");
        String path = srcPath +File.separator +"dao" + File.separator+tableDTO.getDataBaseName() + ".dao.go";
        File file = new File(path);
        if (!file.exists()) {
            Utils.createFile(path, content);
        }
    }

    private void genernateRepository(TableDTO tableDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableDTO,"repositories",  "go/repository.template");
        String path = srcPath +File.separator +"repositories" + File.separator+tableDTO.getDataBaseName() + ".repository.go";
        File file = new File(path);
        if (!file.exists()) {
            Utils.createFile(path, content);
        }
    }

    private void genernateService(TableDTO tableDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableDTO,"service",  "go/service.template");
        String path = srcPath +File.separator +"service"+ File.separator+tableDTO.getDataBaseName() + ".service.go";
        File file = new File(path);
        if (!file.exists()) {
            Utils.createFile(path, content);
        }
    }

    private void genernateModels(TableListDTO tableListDTO,String srcPath) throws Exception {
        String content = Utils.createContentByTemplate(tableListDTO,  "go/models.template");
        //entity直接覆盖
        String path = srcPath +File.separator +"protos"+ File.separator + "models.go";
        File file = new File(path);
        if (!file.exists()) {
            Utils.createFile(path, content);
        }

    }
}
