package com.laoxin.code.generator.dto;

public class NameDTO implements java.io.Serializable {

    protected String dataBaseName;

    protected String javaFieldName;

    protected String goFieldName;

    protected String javaClassName;

    protected String goModelClassName;

    protected String goRepositoryClassName;

    protected String goServiceClassName;

    protected String getMethodName;

    protected String setMethodName;

    public String getJavaFieldName() {
        return javaFieldName;
    }

    public void setJavaFieldName(String javaFieldName) {
        this.javaFieldName = javaFieldName;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
        this.goModelClassName = javaClassName;
    }

    public String getGetMethodName() {
        return getMethodName;
    }

    public void setGetMethodName(String getMethodName) {
        this.getMethodName = getMethodName;
    }

    public String getSetMethodName() {
        return setMethodName;
    }

    public void setSetMethodName(String setMethodName) {
        this.setMethodName = setMethodName;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getGoFieldName() {
        return goFieldName;
    }

    public void setGoFieldName(String goFieldName) {
        this.goFieldName = goFieldName;
    }

    public String getGoModelClassName() {
        return goModelClassName;
    }

    public void setGoModelClassName(String goModelClassName) {
        this.goModelClassName = goModelClassName;
    }

    public String getGoRepositoryClassName() {
        return goRepositoryClassName;
    }

    public void setGoRepositoryClassName(String goRepositoryClassName) {
        this.goRepositoryClassName = goRepositoryClassName;
    }

    public String getGoServiceClassName() {
        return goServiceClassName;
    }

    public void setGoServiceClassName(String goServiceClassName) {
        this.goServiceClassName = goServiceClassName;
    }
}
