package com.assignment.log_parser.parsing;

import java.util.Objects;

public class MethodUrlMap {
    private String method;
    private String url;

    public MethodUrlMap(String method, String url) {
        this.method = method;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodUrlMap that = (MethodUrlMap) o;
        return method.equals(that.method) &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, url);
    }

    @Override
    public String toString() {
        return "MethodUrlMap{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
