package com.njupt.system.util;

/**
 * @author Elaine Huang
 * @date 2021/12/20
 */
public class HttpResponse<T> {
    private boolean success;
    private Integer errCode;
    private String errMsg;
    private T data;

    public HttpResponse() {
    }

    private HttpResponse(boolean success, Integer errCode, String errMsg, T data) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public static <T> HttpResponse success (T data){
        return new HttpResponse<>(true,null,null,data);
    }

    public static HttpResponse<Void> failure (Integer errCode,String errMsg){
        return new HttpResponse<>(false,errCode,errMsg,null);
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

