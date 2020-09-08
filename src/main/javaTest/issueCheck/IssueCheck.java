package issueCheck;

import com.orange.issueCheck.dao.LostIssue;
import com.orange.issueCheck.dbUtils.SQLServerDbUtils;
import com.orange.issueCheck.services.IssueCheckService;
import com.orange.issueCheck.services.impl.IssueCheckServiceImpl;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/19 10:51
 */
public class IssueCheck {


    @Test
    public  void issueCheck() throws Exception {

        Connection connection = SQLServerDbUtils.getConnection();

        //3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = connection.createStatement();
        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
        ResultSet rs = stmt.executeQuery("DECLARE @data1 datetime \n" +
                "DECLARE\t@date2 datetime\n" +
                "SET @data1 = '1999-01-01'\n" +
                "SET @date2 = '2020-12-31'\n" +
                "SELECT c.bkmc, a.bkdh, a.nd, COUNT ( a.qishuid ) ck, COUNT ( b.qishuid ) dk, COUNT ( a.qishuid ) - COUNT ( b.qishuid ) qk FROM qkdj..qsMapping a LEFT JOIN qkdj..smdjb b ON a.bkdh= b.bkdh AND a.nd= b.nd AND a.qb= b.qb LEFT JOIN qkdj..ykinfo c ON a.bkdh= c.bkdh LEFT JOIN ksms..magprice d ON a.bkdh= d.bkdh AND d.nd= a.nd WHERE a.fxstatus IN ( '正常', '增刊' ) AND a.the_date BETWEEN @data1 AND @date2 AND RIGHT ( LEFT ( a.bkdh, 4 ), 1 ) != '-' GROUP BY c.bkmc, a.bkdh, a.nd");
        List<LostIssue> lostIssues = new ArrayList<>();

        while (rs.next()) {//如果对象中有数据，就会循环打印出来
            LostIssue lostIssue = new LostIssue();

            lostIssue.setName(rs.getString("bkmc"));
            lostIssue.setCode(rs.getString("bkdh"));
            lostIssue.setYear(rs.getInt("nd"));;
            lostIssue.setCk(rs.getInt("ck"));
            lostIssue.setDk(rs.getInt("dk"));
            lostIssue.setQk(rs.getInt("qk"));

            lostIssues.add(lostIssue);
        }

        int insertIssueCheck = insertIssueCheck(lostIssues);
    }


    public int insertIssueCheck(List<LostIssue> lostIssues){











        IssueCheckService issueCheckService = new IssueCheckServiceImpl();

        int i = 0;

        for (LostIssue lostIssue : lostIssues) {

            i = issueCheckService.insert(lostIssue) + i;
        }

        return i;
    }






}
