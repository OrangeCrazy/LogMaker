package tableGet;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/18 16:47
 */
public class TableGetTest {

    @Test
    public void getTableName() throws Exception {

        String filepath = "D:\\Bookan\\Bookan_Svn\\log-service\\job-woker\\jobs";

        List<File> fileList = getFileList(filepath);

        for (File file : fileList) {
            StringBuffer buffer = new StringBuffer();

            BufferedReader bf= new BufferedReader(new FileReader(file));

            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行

                buffer.append(s.trim());
            }

            String ddlString = buffer.toString();

            List<String> tableNameList = getTableNameList(ddlString);
        }
    }

    @Test
    public void getTable() throws Exception {

        String filepath = "textFile\\0819ckSQL\\ck01.sql";

        List<File> fileList = getFileList(filepath);

        for (File file : fileList) {
            StringBuffer buffer = new StringBuffer();

            BufferedReader bf= new BufferedReader(new FileReader(file));

            String s = null;
            while((s = bf.readLine())!=null){//使用readLine方法，一次读一行



                buffer.append(s.trim());
            }

            String ddlString = buffer.toString();

            List<String> tableNameList = getTableNameList(ddlString);
        }
    }









    private List<String> getTableNameList(String ddlString) {

        return null;
    }


    private List<File> getFileList(String filepath){

        //File类型可以是文件也可以是文件夹
        File file = new File(filepath);

        File[] fileList = file.listFiles();

        //将该目录下的所有文件放置在一个File类型的数组中
        List<File> wjList = new ArrayList<>();

        for (File value : fileList) {
            if (value.isDirectory()) {
                File[] files = value.listFiles();
                for (File file1 : files) {
                    if (file1.toString().contains("_config.php")) {
                        wjList.add(file1);
                    }
                }
            }
        }
        return wjList;

    }




}
