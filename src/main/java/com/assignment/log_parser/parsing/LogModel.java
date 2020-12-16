package com.assignment.log_parser.parsing;

public class LogModel {
    private Long timestamp;
    private String url;
    private String method;
    private Integer response_time;
    private Integer response_code;

    public LogModel(Long timestamp, String url, String method, Integer response_time, Integer response_code) {
        this.timestamp = timestamp;
        this.url = url;
        this.method = method;
        this.response_time = response_time;
        this.response_code = response_code;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "timestamp=" + timestamp +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", response_time=" + response_time +
                ", response_code=" + response_code +
                '}';
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getResponse_time() {
        return response_time;
    }

    public void setResponse_time(Integer response_time) {
        this.response_time = response_time;
    }

    public Integer getResponse_code() {
        return response_code;
    }

    public void setResponse_code(Integer response_code) {
        this.response_code = response_code;
    }
}
