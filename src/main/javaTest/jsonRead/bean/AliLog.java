package jsonRead.bean;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/9/6 15:04
 */
@Data
@ToString
public class AliLog {

    private String __source__;
    private String __time__;
    private String __topic__;
    private String body_bytes_sent;
    private String client_ip;
    private String client_port;
    private String host;
    private String http_host;
    private String http_referer;
    private String http_user_agent;
    private String http_x_forwarded_for;
    private String http_x_real_ip;
    private String read_request_time;
    private String request_length;
    private String request_method;
    private String request_time;
    private String request_uri;
    private String scheme;
    private String server_protocol;
    private String slb_vport;
    private String slbid;
    private String ssl_cipher;
    private String ssl_protocol;
    private String status;
    private String tcpinfo_rtt;
    private String time;
    private String upstream_addr;
    private String upstream_response_time;
    private String upstream_status;
    private String vip_addr;
    private String write_response_time;


}
