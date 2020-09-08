package jsonRead;

import bean.Data;
import bean.JsonRootBean;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jsonRead.bean.AliLog;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/9/4 17:39
 */
public class AliJson08 {


    @Test
    public void jsonRead() throws Exception {
        StringBuffer buffer = new StringBuffer();
        //BufferedReader bf= new BufferedReader(new FileReader("textFile\\0731ckSQL\\renwu.json"));
//        BufferedReader bf= new BufferedReader(new FileReader("textFile\\0811ckSQL\\zhuanqu.json"));
//        BufferedReader bf= new BufferedReader(new FileReader("textFile\\0811ckSQL\\weikan.json"));
        BufferedReader bf= new BufferedReader(new FileReader("D:\\Bookan\\Documents\\数仓工作\\ali_log_08\\downloaded.txt"));
//        BufferedReader bf = new BufferedReader(new FileReader("D:\\Bookan\\Documents\\数仓工作\\ali_log_08\\log.txt"));

        List<String> openIdList = new ArrayList<>();

        String s = null;
        while ((s = bf.readLine()) != null) {//使用readLine方法，一次读一行
            JSON jsonObject = JSONObject.parseObject(s.trim());
            AliLog aliLog = JSON.toJavaObject(jsonObject, AliLog.class);
            String request_uri = aliLog.getRequest_uri();
            int openId = request_uri.indexOf("openId=");
            int instanceId = request_uri.indexOf("&instanceId=");
            String openIdStr = request_uri.substring(openId + 7, instanceId);

            System.out.println(openIdStr);

            if (!openIdList.contains(openIdStr)) {
                openIdList.add(openIdStr);
            }

        }
        writeLocalFile(openIdList);

    }

    public void writeLocalFile(List<String> openIdList) throws Exception {

        System.out.println("Start Insert Write To local File");

        File newfile = new File("D:\\Bookan\\Documents\\数仓工作\\ali_log_08\\openIdList.txt");
        FileOutputStream fos = new FileOutputStream(newfile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);

        for (String openId : openIdList) {
            System.out.println(openId);
            bw.write(openId+"\n");
        }
        bw.close();
        osw.close();
        fos.close();

        System.out.println("写入完成");


    }


}
