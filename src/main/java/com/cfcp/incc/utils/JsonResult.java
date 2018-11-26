package com.cfcp.incc.utils;

public class JsonResult {

    private String message;
    private Object data;

    private Boolean status; // add by wangbo  表达的是请求处理结果的预期 预期结果为True 非预期结果为false

    public JsonResult() {
        this.setMessage(ResultCode.SUCCESS.msg());
    }

    public JsonResult(Object data) {
        this.data = data;
        this.setMessage(ResultCode.SUCCESS.msg());
        this.status = true;
    }
    public JsonResult(Object data,ResultCode code) {
        this.data = data;
        this.setMessage(code.msg());
    }
    public JsonResult(Object data,Boolean status) {
        this.data = data;
        this.status = status;
        this.setMessage(ResultCode.SUCCESS.msg());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
