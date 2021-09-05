package com.laoxin.code.generator.service;


import com.laoxin.code.generator.dto.DaoEnvDTO;
import com.laoxin.code.generator.dto.TableDTO;

public interface TableService {


    TableDTO getTableDTO(DaoEnvDTO dto, String tableName) throws  Exception;


}
