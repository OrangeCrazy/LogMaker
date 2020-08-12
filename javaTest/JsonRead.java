import bean.Data;
import bean.JsonRootBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONString;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import static javafx.scene.input.KeyCode.J;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/7/30 18:13
 */
public class JsonRead {

    @Test
    public void  jsonRead() throws Exception{
        StringBuffer buffer = new StringBuffer();
        //BufferedReader bf= new BufferedReader(new FileReader("textFile\\0731ckSQL\\renwu.json"));
//        BufferedReader bf= new BufferedReader(new FileReader("textFile\\0811ckSQL\\zhuanqu.json"));
//        BufferedReader bf= new BufferedReader(new FileReader("textFile\\0811ckSQL\\weikan.json"));
        BufferedReader bf= new BufferedReader(new FileReader("textFile\\0811ckSQL\\zqts.json"));

        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        String jsonString = buffer.toString();
        JSON jsonObject = JSONObject.parseObject(jsonString);
        JsonRootBean jsonRootBean = JSON.toJavaObject(jsonObject, JsonRootBean.class);

        List<Data> data = jsonRootBean.getData();
        for (Data datum : data) {
            String context = datum.getContext();
            if (context.contains("李天琦")){
                System.out.println(datum.toString());
                System.out.println("");
            }
        }




    }

}
