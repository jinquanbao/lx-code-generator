package com.laoxin.code.generator.service;


import com.laoxin.code.generator.dto.DaoEnvDTO;

public interface DaoHelperService {

    boolean support(String language);

    void genernate(DaoEnvDTO dto) throws Exception;


}
