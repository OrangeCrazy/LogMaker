package com.orange.issueCheck.services.impl;

import com.orange.issueCheck.dao.LostIssue;
import com.orange.issueCheck.dao.LostIssueMapper;
import com.orange.issueCheck.services.IssueCheckService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/19 13:27
 */
public class IssueCheckServiceImpl implements IssueCheckService {

    @Autowired
    LostIssueMapper lostIssueMapper;


    @Override
    public int insert(LostIssue record) {
        return lostIssueMapper.insert(record);
    }
}
