package com.orange.issueCheck.services;

import com.orange.issueCheck.dao.LostIssue;
import com.orange.issueCheck.dao.LostIssueMapper;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/19 13:26
 */
public interface IssueCheckService {


    int insert(LostIssue record);

}
