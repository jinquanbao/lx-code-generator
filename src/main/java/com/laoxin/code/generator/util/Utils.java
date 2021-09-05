package com.laoxin.code.generator.util;

import com.laoxin.code.generator.dto.TableDTO;
import com.laoxin.code.generator.dto.TableListDTO;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Utils {


    public static String dbName2DOName(String dbName, boolean isFirstUpper) {
        if (StringUtils.isBlank(dbName)) {
            return dbName;
        }
        StringBuilder sb = new StringBuilder();
        char lastChar = dbName.charAt(0);
        //首字母
        sb.append(isFirstUpper ? Character.toUpperCase(lastChar) : Character.toLowerCase(lastChar));
        for (int i = 1; i < dbName.length(); i++) {
            char c = dbName.charAt(i);
            //如果不划线
            if (c != '_') {
                //如果上一字母是下划线,大写,否则下写
                sb.append(lastChar == '_' ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }
            lastChar = c;
        }
        return sb.toString();
    }


    public static String dbName2ClassName(String dbName) {
        return dbName2DOName(dbName, true);
    }

    public static String dbName2FieldName(String dbName) {
        return dbName2DOName(dbName, false);
    }

    public static String dbName2GetName(String dbName) {
        return "get" + dbName2DOName(dbName, true);
    }

    public static String dbName2SetName(String dbName) {
        return "set" + dbName2DOName(dbName, true);
    }


    public static String createContentByTemplate(TableDTO tableDTO, String packageName, String templateFileName) throws Exception {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        ClassTemplateLoader templateLoader = new ClassTemplateLoader(Utils.class, "/");
        configuration.setTemplateLoader(templateLoader);
        Template template = configuration.getTemplate(templateFileName);
        tableDTO.setPackageName(packageName);
        ByteArrayOutputStream sb = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(sb);
        template.process(tableDTO, writer);
        return sb.toString("utf-8");
    }

    public static String createContentByTemplate(TableListDTO tableDTO, String templateFileName) throws Exception {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        ClassTemplateLoader templateLoader = new ClassTemplateLoader(Utils.class, "/");
        configuration.setTemplateLoader(templateLoader);
        Template template = configuration.getTemplate(templateFileName);
        ByteArrayOutputStream sb = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(sb);
        template.process(tableDTO, writer);
        return sb.toString("utf-8");
    }

    public static void createFile(String path, String content) throws Exception {
        File filePath = new File(path);
        FileUtils.forceMkdirParent(filePath);
        FileUtils.write(filePath, content, "utf-8");
    }

}
