package com.example.md_back.Handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.md_back.model.WordType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


public class WordTypeHandler extends BaseTypeHandler<WordType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, WordType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public WordType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return WordType.valueOf(rs.getInt(columnName));
    }

    @Override
    public WordType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return WordType.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public WordType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return WordType.valueOf(cs.getInt(columnIndex));
    }
}