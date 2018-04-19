package com.qdch.core;

import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;

import java.util.List;

public class PostgreSqlDialect extends com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect {

    private String handle(String tablename) {
        if (StringUtils.isNotBlank(tablename)) {
            tablename = tablename.indexOf(".") > 0 ?
                    tablename.replace(".", "\".\"") : tablename;

        }
        return tablename;
    }

    @Override
    public void forDbSave(String tableName, String[] pKeys, Record record, StringBuilder sql, List<Object> paras) {
        tableName = handle(tableName);
        super.forDbSave(tableName, pKeys, record, sql, paras);
    }
}
