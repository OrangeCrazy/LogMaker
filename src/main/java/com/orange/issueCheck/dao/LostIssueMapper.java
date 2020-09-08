package com.orange.issueCheck.dao;

public interface LostIssueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostIssue record);

    int insertSelective(LostIssue record);

    LostIssue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostIssue record);

    int updateByPrimaryKey(LostIssue record);
}