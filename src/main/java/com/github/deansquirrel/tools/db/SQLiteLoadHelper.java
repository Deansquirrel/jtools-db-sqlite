package com.github.deansquirrel.tools.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class SQLiteLoadHelper {

    private static final Logger logger = LoggerFactory.getLogger(SQLiteLoadHelper.class);

    private final IToolsDbHelper iToolsDbHelper;

    public SQLiteLoadHelper(IToolsDbHelper iToolsDbHelper) {
        this.iToolsDbHelper = iToolsDbHelper;
    }

    public void addSQLiteConn(String connName, String dbPath) throws Exception {
        if(connName == null || "".equals(connName) || dbPath == null || "".equals(dbPath)) {
            throw new Exception("连接地址或名称不允许为空");
        }
        if(iToolsDbHelper.isExistDataSource(connName)) {
            throw new Exception(MessageFormat.format("连接名称[{0}]已存在", connName));
        }
        SQLiteConnHelper conn = SQLiteConnHelper.builder(connName).setPath(dbPath);
        iToolsDbHelper.addDataSource(conn.getName(), conn.getDataSource());
    }
}
