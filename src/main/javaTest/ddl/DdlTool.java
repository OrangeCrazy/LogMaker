package ddl;

import ddl.bean.Ddl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/14 11:55
 */
public class DdlTool {

    @Test
    public void ddlReader()throws Exception{

        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader("textFile\\0814ckSQL\\ck01.sql"));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        String ddlStrings = buffer.toString();

        String[] split = ddlStrings.split("--");

        List<Ddl> ddlList = new ArrayList<>();


        for (String tableNameAndDdl : split) {

            //System.out.println("tableNameAndDdl:    "+tableNameAndDdl);
            Ddl ddl = new Ddl();

            String[] tableNameAndDdlList = tableNameAndDdl.split("CREATE");
            try {
                String tableName = tableNameAndDdlList[0];
                String tableNameAndDdlString = tableNameAndDdlList[1];
                //System.out.println("tableNameAndDdlString:    "+tableNameAndDdlString);
                ddl.setTableName(tableName);

                int indexClStart = tableNameAndDdlString.indexOf("(");
                int indexClEnd = tableNameAndDdlString.indexOf(")");

                String col = tableNameAndDdlString.substring(indexClStart+1, indexClEnd);
                String[] colList = col.split(",~");

                List<Map<String,Map<String,String>>> colInfoList = new ArrayList<>() ;

                for (String s1 : colList) {

                    Map<String, String> colTypeMap = new HashMap<>();

                    Map<String, Map<String, String>> colNameMap = new HashMap<>();

                    int ClStart = s1.indexOf("`")+1;
                    int ClEnd = s1.lastIndexOf("`");


                    int comStart = s1.indexOf("'")+1;
                    int comEnd = s1.lastIndexOf("'");

                    String comment = s1.substring(comStart, comEnd);

                    String colName = s1.substring(ClStart,ClEnd);

                    String typeName = s1.replace(comment,"")
                            .replace(colName,"")
                            .replace("`","")
                            .replace("'","")
                            .replace(" ","");

                    colTypeMap.put(typeName,comment);
                    colNameMap.put(colName,colTypeMap);

                    colInfoList.add(colNameMap);

                }

                ddl.setCol(colInfoList);

                ddlToString(ddl);

            }catch (Exception ignored){

            }
        }




    }


    private void ddlToString(Ddl ddl) throws Exception {

        //System.out.println(ddl.getTableName());
        List<Map<String, Map<String, String>>> col = ddl.getCol();

        List<List<String>> list = new ArrayList<>();

        for (Map<String, Map<String, String>> stringMapMap : col) {

            List<String> stringList =  new ArrayList<>();

            for (String s : stringMapMap.keySet()) {
                Map<String, String> stringStringMap = stringMapMap.get(s);

                for (String s1 : stringStringMap.keySet()) {
                    String s2 = stringStringMap.get(s1);

                    System.out.println(ddl.getTableName()+ "\t"+s+ "\t"+s1+ "\t"+""+ "\t"+s2);
                    stringList.add(ddl.getTableName());
                    stringList.add(s);//colName
                    stringList.add(s1);//typeName
                    stringList.add("");//isPrimaryKey
                    stringList.add(s2);//comment
                }


            }

            list.add(stringList);
            List<String> lists = new ArrayList<>();
            list.add(lists);
            //stringList.clear();

        }

        writeExcel(list);

        //System.out.println(ddl.getCol());



    }

    public void writeExcel(List<List<String>> list) throws Exception {

        OutputStream out ;
        // 读取Excel文档
        File finalXlsxFile = new File("D:\\ddl.xls");


        XSSFWorkbook workBook = new XSSFWorkbook();

        // sheet 对应一个工作页
        Sheet sheet = workBook.getSheetAt(0);

        // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
        out =  new FileOutputStream(finalXlsxFile);
        workBook.write(out);

        //往Excel中写新数据
        for (int j = 0; j < list.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j);
            // 得到要插入的每一条记录
            List<String> stringList = list.get(j);
            for (String s : stringList) {
                Cell colName = row.createCell(0);
                colName.setCellValue(stringList.get(0));

                Cell typeName = row.createCell(1);
                typeName.setCellValue(stringList.get(1));

                Cell isPrimaryKey = row.createCell(2);
                isPrimaryKey.setCellValue(stringList.get(2));

                Cell comment = row.createCell(3);
                comment.setCellValue(stringList.get(3));
            }

        }
        // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
        out =  new FileOutputStream(finalXlsxFile);
        workBook.write(out);


    }

}
