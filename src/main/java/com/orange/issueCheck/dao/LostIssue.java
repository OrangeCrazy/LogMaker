package com.orange.issueCheck.dao;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LostIssue {
    private Integer id;

    private String name;

    private String code;

    private Integer year;

    private Integer ck;

    private Integer dk;

    private Integer qk;


}