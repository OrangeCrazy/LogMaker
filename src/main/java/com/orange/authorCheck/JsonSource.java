package com.orange.authorCheck;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/24 13:06
 */
@Data
public class JsonSource {


    @JsonProperty("_index")
    private String Index;
    @JsonProperty("_type")
    private String Type;
    @JsonProperty("_id")
    private String Id;
    @JsonProperty("_score")
    private double Score;
    @JsonProperty("_source")
    private Source Source;

}
