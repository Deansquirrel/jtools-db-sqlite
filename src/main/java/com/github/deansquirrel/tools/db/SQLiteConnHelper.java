package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;

import java.text.MessageFormat;

public class SQLiteConnHelper extends ABaseConn {

    private static final String connStr = "jdbc:sqlite:{0}";

    private String path;
    private String sConnStr;

    protected SQLiteConnHelper(String name){
        super(name);
    }

    public static SQLiteConnHelper builder(String name) {
        return new SQLiteConnHelper(name);
    }

    public SQLiteConnHelper setConnStr(String connStr) {
        this.sConnStr = connStr;
        return this;
    }

    public SQLiteConnHelper setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public DruidDataSource getDataSource() {
        DruidDataSource ds = new DruidDataSource();
        if(this.getName() != null && "".equals(ds.getName().trim())) {
            ds.setName(this.getName().trim());
        }
        if(this.sConnStr == null || "".equals(this.sConnStr)) {
            ds.setUrl(MessageFormat.format(SQLiteConnHelper.connStr,this.path));
        } else {
            ds.setUrl(this.sConnStr);
        }
        this.setSourceAttributes(ds);
        ds.setMaxActive(1);
        return ds;
    }

    public DruidDataSource getDataSource(Integer queryTimeout) {
        return super.getDataSource(queryTimeout, 1);
    }

}
