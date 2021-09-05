package com.laoxin.code.generator;


import com.laoxin.code.generator.config.DaoEnvConfig;
import com.laoxin.code.generator.dto.DaoEnvDTO;
import com.laoxin.code.generator.service.DaoHelperFactory;
import com.laoxin.code.generator.service.DaoHelperService;
import com.laoxin.code.generator.service.impl.GoDaoHelperServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CodeGenerator {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DaoEnvConfig.class);
        DaoEnvDTO dto = ac.getBean(DaoEnvDTO.class);

        DaoHelperFactory.getInstance(dto.getCodeLanguage()).genernate(dto);
    }
}
