package com.orange.authorCheck;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

import org.apache.http.impl.client.CloseableHttpClient;


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/23 18:40
 */
public class AuthorCheck implements Runnable{

    private static CloseableHttpClient httpClient = null;




    private List<String> readAuthor() throws IOException {

        ArrayList<String> authorList = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader("textFile\\0823ckSQL\\ck01.sql"));

        String s = null;
        while ((s = bf.readLine()) != null) {//使用readLine方法，一次读一行
            authorList.add(s.trim());
        }
        return authorList;
    }


    @Test
    public void doPost() throws IOException {


    }


    @Override
    public void run() {

         int i;

        for(i = 0;i <100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }


    @Test
    public void readJson() throws IOException {

        BufferedReader bf= new BufferedReader(new FileReader("D:\\Bookan\\Documents\\DataOpt\\202008\\0821\\53613\\1.json"));

        StringBuffer buffer = new StringBuffer();

        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        String jsonString = buffer.toString();
        JSON jsonObject = JSONObject.parseObject(jsonString);
        JsonSource jsonSource = JSON.toJavaObject(jsonObject, JsonSource.class);

        System.out.println(jsonSource.getSource());



    }






}
