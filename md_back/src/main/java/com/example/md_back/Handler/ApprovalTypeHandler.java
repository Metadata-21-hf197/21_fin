package com.example.md_back.Handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.md_back.model.ApprovalType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


public class ApprovalTypeHandler extends BaseTypeHandler<ApprovalType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ApprovalType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public ApprovalType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return ApprovalType.valueOf(rs.getInt(columnName));
    }

    @Override
    public ApprovalType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return ApprovalType.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public ApprovalType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ApprovalType.valueOf(cs.getInt(columnIndex));
    }
}