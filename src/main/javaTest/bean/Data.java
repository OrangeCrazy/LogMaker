/**
  * Copyright 2020 bejson.com 
  */
package bean;


import java.io.Serializable;

/**
 * Auto-generated: 2020-07-30 18:18:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@lombok.Data
public class Data implements Serializable {

    private long id;
    private int resourceType;
    private int searchType;
    private int resourceId;
    private String title;
    private String resourceName;
    private long issueId;
    private int pageInfo;
    private int format;
    private String context;
    private IssueInfo issueInfo;

    @Override
    public String toString() {
        return
                "资源类型=" + resourceTypeCon(resourceType) +"\r\n"+
                "资源ID=" + resourceId +"\r\n"+
                "文章标题='" + title +"\r\n"+
                "资源名称='" + resourceName + '\'' +"\r\n"+
                "期Id=" + issueId +"\r\n"+
                "页码=" + pageInfo +"\r\n"+
                "上下文='" + context  +"\r\n"+
                "博看代号=" + issueInfo.getResourceCode() +"\r\n"+
                "期年份=" + issueInfo.getIssueYear() +"\r\n"+
                "期号=" + issueInfo.getIssueNo() +"\r\n"+
                "期名=" + issueInfo.getIssueName() +"\r\n";

    }

    private String resourceTypeCon(int resourceType){
        switch (resourceType){
            case 1 :
                return  "期刊";
            case 2 :
                return "报纸";
            case 3 :
                return "图书";
                default:
                    return String.valueOf(resourceType);
        }
    }

}