/**
  * Copyright 2020 bejson.com 
  */
package bean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2020-07-30 18:18:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@lombok.Data
public class JsonRootBean implements Serializable {
    private int status;
    private String errorCode;
    private List<Data> data;


}