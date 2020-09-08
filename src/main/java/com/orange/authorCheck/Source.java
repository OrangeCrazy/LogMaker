package com.orange.authorCheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/24 13:06
 */
@Data
public class Source {


    private int id;
    @JsonProperty("is_sync")
    private int isSync;
    @JsonProperty("resourceId")
    private int resourceid;
    @JsonProperty("issueId")
    private int issueid;
    private String title;
    private String author;
    private int page;
    @JsonProperty("resourceName")
    private String resourcename;
    private int type;
    @JsonProperty("is_latest")
    private int isLatest;
    private int online;
    private String content;

    @Override
    public String toString() {
        return
                id + "\t" +
                isSync + "\t" +
                resourceid + "\t" +
                issueid + "\t" +
                title + "\t" +
                author + "\t" +
                page + "\t" +
                resourcename + "\t" +
                type + "\t" +
                isLatest + "\t" +
                online + "\t" +
                content ;
    }
}
