package com.ha.generator.config.querys;

import com.ha.generator.common.DbType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 *  MySql 表数据查询
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/21
 */
public class MySqlQuery extends AbstractDbQuery {


    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }


    @Override
    public String tablesSql() {
        return "show table status";
    }


    @Override
    public String tableFieldsSql() {
        return "show full fields from `%s`";
    }


    @Override
    public String tableName() {
        return "NAME";
    }


    @Override
    public String tableComment() {
        return "COMMENT";
    }


    @Override
    public String fieldName() {
        return "FIELD";
    }


    @Override
    public String fieldType() {
        return "TYPE";
    }


    @Override
    public String fieldComment() {
        return "COMMENT";
    }


    @Override
    public String fieldKey() {
        return "KEY";
    }


    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return "auto_increment".equals(results.getString("Extra"));
    }
}
