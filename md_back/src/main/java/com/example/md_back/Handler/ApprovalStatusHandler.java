package com.example.md_back.Handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.md_back.model.ApprovalStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


public class ApprovalStatusHandler extends BaseTypeHandler<ApprovalStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ApprovalStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public ApprovalStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return ApprovalStatus.valueOf(rs.getInt(columnName));
    }

    @Override
    public ApprovalStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return ApprovalStatus.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public ApprovalStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ApprovalStatus.valueOf(cs.getInt(columnIndex));
    }
}