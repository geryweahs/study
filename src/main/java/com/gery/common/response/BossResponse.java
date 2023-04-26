package com.gery.common.response;

import com.gery.common.constant.BossConstants;

import java.io.Serializable;
import java.util.Objects;

public class BossResponse<T> implements Serializable {

    private static final long serialVersionUID = 1626120430566031974L;
    private int code;
    private String traceId;
    private String msg;
    private String debugMsg;
    private T data;

    public BossResponse() {
    }

    public static <T> BossResponse<T> success() {
        return restResult((T)null, BossConstants.SUCCESS, "操作成功");
    }

    public static <T> BossResponse<T> success(T data) {
        return restResult(data, BossConstants.SUCCESS, "操作成功");
    }

    public static <T> BossResponse<T> success(T data, String msg) {
        return restResult(data, BossConstants.SUCCESS, msg);
    }

    public static <T> BossResponse<T> error() {
        return restResult(null, BossConstants.FAIL, "操作失败");
    }



    public static <T> BossResponse<T> error(String msg) {
        return restResult(null, BossConstants.FAIL, msg);
    }

    public static <T> BossResponse<T> error(String msg, String debugMsg) {
        return restResult(null, BossConstants.FAIL, msg, debugMsg);
    }

    public static <T> BossResponse<T> error(T data) {
        return restResult(data, BossConstants.FAIL, "操作失败");
    }

    public static <T> BossResponse<T> error(T data, String msg) {
        return restResult(data, BossConstants.FAIL, msg);
    }

    public static <T> BossResponse<T> error(int code, String msg) {
        return restResult(null, code, msg);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getDebugMsg() {
        return this.debugMsg;
    }

    public void setDebugMsg(String debugMsg) {
        this.debugMsg = debugMsg;
    }

    public boolean isSuccess() {
        return Objects.equals(this.code, BossConstants.SUCCESS);
    }

    public static boolean isSuccess(BossResponse response) {
        return Objects.isNull(response) ? false : response.isSuccess();
    }

    private static <T> BossResponse<T> restResult(T data, int code, String msg) {
        BossResponse<T> apiResult = new BossResponse();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    private static <T> BossResponse<T> restResult(T data, int code, String msg, String debugMsg) {
        BossResponse<T> apiResult = new BossResponse();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        apiResult.setDebugMsg(debugMsg);
        return apiResult;
    }

    public String toString() {
        return "BossResponse(code=" + this.getCode() + ", traceId=" + this.getTraceId() + ", msg=" + this.getMsg() + ", debugMsg=" + this.getDebugMsg() + ", data=" + this.getData() + ")";
    }
}
