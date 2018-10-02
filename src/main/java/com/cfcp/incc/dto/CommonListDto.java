package com.cfcp.incc.dto;


import java.util.List;

/**
 * 一般操作返回数据
 * Created by zhyj on 2016/11/28.
 */
public class CommonListDto<T>{
    //    protected String returnCode;
    protected String message;
    protected CommonResult state;
    private List<T> result;

    public enum CommonResult {
        SUCCESS("请求成功", "200"),
        FAILED("请求失败", "500");
        private String message;
        private String code;

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        CommonResult(String message, String code) {
            this.message = message;
            this.code = code;
        }
    }

    public CommonListDto() {
    }

    public CommonListDto(CommonResult state) {
        this.state = state;
    }

    public CommonListDto(CommonResult state, String message) {
        this.state = state;
        this.message = message;
    }

    public CommonListDto(List<T> result) {
        this.state = CommonResult.SUCCESS;
        this.result = result;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String getReturnCode() {
        return this.state.getCode();
    }

    public String getMessage() {
        return this.state.getMessage() + (this.message != null ? this.message: "");
    }

    public static void main(String[] args){
        System.out.println(new CommonListDto(CommonResult.SUCCESS).getReturnCode());
        System.out.println(new CommonListDto(CommonResult.SUCCESS).getMessage());

    }
}
