package ddl.bean;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/14 12:58
 */
@Data
@ToString
public class Ddl implements Serializable {

    private String tableName;

    private List<Map<String,Map<String,String>>> col;

    private List<String> primaryKey;

}
