package com.assignment.log_parser;

public class Val {
    private Integer val;
    private Long ts;

    public Val(Integer val, long ts) {
        this.val=val;
        this.ts=ts;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
