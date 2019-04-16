package com.zpan.gaodelocation.bean;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class LocationFailedBean {

    /** 错误码 */
    private int errorCode;
    /** 错误信息 */
    private String errorInfo;
    /** 错误描述 */
    private String errorDetail;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
