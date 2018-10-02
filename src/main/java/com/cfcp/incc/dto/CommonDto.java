package com.cfcp.incc.dto;


/**
 * 一般操作返回数据
 * Created by zhyj on 2016/11/28.
 */
public class CommonDto <T>{
    //    protected String returnCode;
    protected String message;
    protected CommonResult state;
    private T result;

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

    public CommonDto() {
    }

    public CommonDto(CommonResult state) {
        this.state = state;
    }

    public CommonDto(CommonResult state, String message) {
        this.state = state;
        this.message = message;
    }

    public CommonDto(T result) {
        this.state = CommonResult.SUCCESS;
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getReturnCode() {
        return this.state.getCode();
    }

    public String getMessage() {
        return this.state.getMessage() + (this.message != null ? this.message: "");
    }

    public static void main(String[] args){
        System.out.println(new CommonDto(CommonResult.SUCCESS).getReturnCode());
        System.out.println(new CommonDto(CommonResult.SUCCESS).getMessage());
        CommonDto commonDto = new CommonDto<String >("sss");
        System.out.println(commonDto.getResult());

    }
}
