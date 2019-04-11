package zp.com.baidulocation.baidulocation;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import zp.com.baidulocation.bean.LocationFailedBean;
import zp.com.baidulocation.bean.LocationSuccessBean;
import zp.com.baidulocation.callback.GpsLocationCallback;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class BaiduLocationListener extends BDAbstractLocationListener {

    /**
     * GPS定位结果，GPS定位成功
     */
    private static final int BDLOCATION_LOC_TYPE_61 = 61;
    /**
     * 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果
     */
    private static final int BDLOCATION_LOC_TYPE_66 = 66;
    /**
     * 网络定位结果，网络定位成功
     */
    private static final int BDLOCATION_LOC_TYPE_161 = 161;

    GpsLocationCallback mBaiduLocationCallback;

    public void setBaiduLocationCallback(GpsLocationCallback callback) {
        mBaiduLocationCallback = callback;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (mBaiduLocationCallback == null) {
            throw new IllegalStateException("使用 BaiduLocationListener 之前需要调用 setBaiduLocationCallback");
        }

        boolean locTypeResult = bdLocation.getLocType() == BDLOCATION_LOC_TYPE_61
            || bdLocation.getLocType() == BDLOCATION_LOC_TYPE_66
            || bdLocation.getLocType() == BDLOCATION_LOC_TYPE_161;

        if (locTypeResult) {
            LocationSuccessBean bean = new LocationSuccessBean();
            bean.setLocationTime(bdLocation.getTime());
            bean.setLocationAltitude(bdLocation.getAltitude());
            bean.setLocationLat(bdLocation.getLatitude());
            bean.setLocationLon(bdLocation.getLongitude());
            bean.setLocationCity(bdLocation.getCity());
            mBaiduLocationCallback.success(bean);
            mBaiduLocationCallback.complete();
        } else {
            LocationFailedBean failedBean = new LocationFailedBean();
            failedBean.setLocType(bdLocation.getLocType());
            failedBean.setLocTypeDes(bdLocation.getLocTypeDescription());
            mBaiduLocationCallback.failed(failedBean);
            mBaiduLocationCallback.complete();
        }
    }
}
