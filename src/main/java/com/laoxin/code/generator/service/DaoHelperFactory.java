package com.laoxin.code.generator.service;

import com.laoxin.code.generator.service.impl.DaoHelperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DaoHelperFactory {

    private static List<DaoHelperService> list = new ArrayList<>();

    @Autowired
    public DaoHelperFactory(List<DaoHelperService> list){
        DaoHelperFactory.list = list;
    }

    public static DaoHelperService getInstance(String language){
        return list.stream().filter(x->x.support(language)).findAny().orElse(new DaoHelperServiceImpl());
    }
}
