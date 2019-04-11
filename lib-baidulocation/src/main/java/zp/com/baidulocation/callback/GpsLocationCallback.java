package zp.com.baidulocation.callback;

import zp.com.baidulocation.bean.LocationFailedBean;
import zp.com.baidulocation.bean.LocationSuccessBean;

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
