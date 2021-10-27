package com.example.md_back.mappers;

import com.example.md_back.model.Approval;
import com.example.md_back.model.Term;
import com.example.md_back.model.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalMapper {
    List<Approval> getApprovalListByUserId(int userId);
}
