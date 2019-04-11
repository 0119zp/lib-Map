package com.zpan.gaodelocation.callback;

import com.zpan.gaodelocation.bean.LocationFailedBean;
import com.zpan.gaodelocation.bean.LocationSuccessBean;

/**
 * @author zpan
 * @date 2019/3/18
 */
public interface GpsLocationCallback {

    /**
     * 定位成功
     *
     * @param response 定位成功 bean
     */
    void success(LocationSuccessBean response);

    /**
     * 定位失败
     *
     * @param response 定位失败 bean
     */
    void failed(LocationFailedBean response);

    /**
     * 定位完成
     */
    void complete();
}
