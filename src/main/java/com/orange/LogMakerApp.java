package com.orange;

import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LogMakerApp {
    //主机名--IP
    public static String[] hostNameAndIP = {
            "HQCNKMS01W,10.1.8.153",
            "HQCNNAC02W,10.1.8.154",
            "HQCNDLP01W,10.1.8.155",
            "HQCNMDC01L,10.1.8.120",
            "HQCNNTP03L,10.1.8.121",
            "MRHZSAP01L,10.1.8.122",
            "HQCNNAC03W,10.1.8.123",
            "MRWHCDN01L,10.1.8.55",
            "MRBJMDC02W,10.1.8.56",
            "MRSHKMS01W,10.1.8.57",
            "MRSHNAC02W,10.1.8.58",
            "MRSHDLP01W,10.1.8.210",
            "MRSHMDC01L,10.1.8.211",
            "MRSHNTP03L,10.1.8.212",
            "MRSHSAP01L,10.1.8.180",
            "HQCNNAC06W,10.1.8.181",
            "MRWHPOR01L,10.1.8.182",
            "MRBJNAC02W,10.1.8.183"
    };

    //随机生成百分比
    public static String getPercentage() {
        Random random = new Random();
        return "0." + (random.nextInt(9998) + 1);
    }

    //传入总数和百分比获取当前占用量
    private static String[] hardDeviceTotal = {"cores", "MGB", "HGB", "Mbps"};
    public static String getUsedByTotalAndPercentage(String use, String total) {
        BigDecimal totalDecimal = new BigDecimal(total);
        BigDecimal useDecimal = new BigDecimal(use);
        BigDecimal divide = totalDecimal.multiply(useDecimal);
        return String.valueOf(divide);
    }

    //去掉单位
    private String removeStr(String str) {
        String[] hardDeviceStr = {"cores", "MGB", "TB", "Mbps"};
        for (String s : hardDeviceStr) {
            if (str.contains(s)) {
                return str.replace(s, "");
            }
        }
        return str;
    }

    //随机运行的主机
    public static String sampleHost() {
        int ipNum;
        ipNum = new Random().nextInt(18);
        return hostNameAndIP[ipNum];
    }

    //当前时间的Hash值
    public static String nowSecondMd5() {
        String nowMills = String.valueOf(System.currentTimeMillis() / 1000);
        return DigestUtils.md5DigestAsHex(nowMills.getBytes());
    }

    //格式化时间样式
    public static String formatTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String[] hardDevices = {
            "CPU", "Memory", "HardDisk", "Network"
    };
    public static String[] cpuTotal = {"128", "64", "32", "64"};
    public static String[] memTotal = {"128", "64", "32", "64", "128"};
    public static String[] hddTotal = {"16", "12", "16", "16", "8"};
    public static String[] netTotal = {"10000"};

    //随机获取总数
    public static String getTotal(String[] totalArr) {
        Random random = new Random();
        return String.valueOf(totalArr[random.nextInt(totalArr.length)]);
    }

    //生成日志方法
    public static String generateLog() {
        String hostNameAndIPStr = sampleHost();
        String logId = nowSecondMd5();
        String cpuTotalStr = getTotal(cpuTotal);
        String memTotalStr = getTotal(memTotal);
        String hddTotalStr = getTotal(hddTotal);
        String netTotalStr = getTotal(netTotal);
        String newTime = formatTime();
        String p1 = getPercentage();
        String p2 = getPercentage();
        String p3 = getPercentage();
        String p4 = getPercentage();

        String log = "[" + logId + "]" + newTime + "|" + hostNameAndIPStr + "," +
                hardDevices[0] + "used," + getUsedByTotalAndPercentage(p1, cpuTotalStr) + "," + hardDevices[0] + "_percentage," + p1 + "," +
                hardDevices[1] + "used," + getUsedByTotalAndPercentage(p2, memTotalStr) + "," + hardDevices[1] + "_percentage," + p2 + "," +
                hardDevices[2] + "used," + getUsedByTotalAndPercentage(p3, hddTotalStr) + "," + hardDevices[2] + "_percentage," + p3 + "," +
                hardDevices[3] + "used," + getUsedByTotalAndPercentage(p4, netTotalStr) + "," + hardDevices[3] + "_percentage," + p4 + "," +
                hardDevices[0] + "_total," + cpuTotalStr + "," +
                hardDevices[1] + "_total," + memTotalStr + "," +
                hardDevices[2] + "_total," + hddTotalStr + "," +
                hardDevices[3] + "_total," + netTotalStr;
        System.out.println(log);
        return log;
    }

    //主类
    public static void main(String[] args) throws IOException, InterruptedException {

        //dest生成日志的路径
        String dest = "log/logs.txt";
        File file = new File(dest);

        int num, sleepTime;
        if (args.length == 0) {
            //默认生成日志条数
            num = 100;
            //默认每5秒生成一次
            sleepTime = 5;
        } else if (args.length == 1) {
            //传一个参数
            num = Integer.parseInt(args[0]);
            sleepTime = 10;
        } else {
            //传两个参数
            num = Integer.parseInt(args[0]);
            sleepTime = Integer.parseInt(args[1]);
        }

        while (true) {
            for (int i = 0; i < num; i++) {
                String content = generateLog() + "\n";
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write(content.getBytes());
                fos.close();
            }
            //默认多久日志时间
            TimeUnit.SECONDS.sleep(sleepTime);
        }
    }
}
