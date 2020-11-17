package com.joey.persistent.custom.config;

import com.joey.persistent.custom.util.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

public class BoundSql {

    private String sqlText;

    private List<ParameterMapping> parameterMappingsList = new ArrayList<ParameterMapping>();

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingsList() {
        return parameterMappingsList;
    }

    public void setParameterMappingsList(List<ParameterMapping> parameterMappingsList) {
        this.parameterMappingsList = parameterMappingsList;
    }
}
