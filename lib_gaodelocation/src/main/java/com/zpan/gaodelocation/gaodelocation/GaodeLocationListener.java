package com.zpan.gaodelocation.gaodelocation;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.zpan.gaodelocation.R;
import com.zpan.gaodelocation.bean.LocationFailedBean;
import com.zpan.gaodelocation.bean.LocationSuccessBean;
import com.zpan.gaodelocation.callback.GpsLocationCallback;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class GaodeLocationListener implements AMapLocationListener {

    /**
     * GPS定位结果，GPS定位成功
     */
    private static final int GAODE_LOCATION_ERROR_CODE_0 = 0;
    /**
     * GPS定位结果，请对定位传递的参数进行非空判断
     */
    private static final int GAODE_LOCATION_ERROR_CODE_1 = 1;
    /**
     * GPS定位结果，KEY鉴权失败
     */
    private static final int GAODE_LOCATION_ERROR_CODE_7 = 7;
    /**
     * GPS定位结果，请在设备的设置中开启app的定位权限
     */
    private static final int GAODE_LOCATION_ERROR_CODE_12 = 12;

    private GpsLocationCallback mLocationCallback;
    private Context mContext;

    public GaodeLocationListener(Context context) {
        this.mContext = context;
    }

    public void setLocationCallback(GpsLocationCallback callback) {
        this.mLocationCallback = callback;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mLocationCallback == null) {
            throw new IllegalStateException(getResourceString(R.string.map_gaode_location_fail_order));
        }
        if (aMapLocation == null) {
            throw new IllegalStateException(getResourceString(R.string.map_gaode_location_fail_null));
        }
        int errorCode = aMapLocation.getErrorCode();
        if (errorCode == GAODE_LOCATION_ERROR_CODE_1) {
            throw new IllegalStateException(getResourceString(R.string.map_gaode_location_error_code_1));
        }
        if (errorCode == GAODE_LOCATION_ERROR_CODE_7) {
            throw new IllegalStateException(getResourceString(R.string.map_gaode_location_error_code_7));
        }
        if (errorCode == GAODE_LOCATION_ERROR_CODE_12) {
            throw new IllegalStateException(getResourceString(R.string.map_gaode_location_error_code_12));
        }
        if (errorCode == GAODE_LOCATION_ERROR_CODE_0) {
            LocationSuccessBean bean = new LocationSuccessBean();
            bean.setLocationTime(String.valueOf(aMapLocation.getTime()));
            bean.setLocationAltitude(aMapLocation.getAltitude());
            bean.setLocationLat(aMapLocation.getLatitude());
            bean.setLocationLon(aMapLocation.getLongitude());
            bean.setLocationCity(aMapLocation.getCity());
            mLocationCallback.success(bean);
            mLocationCallback.complete();
        } else {
            LocationFailedBean failedBean = new LocationFailedBean();
            failedBean.setLocType(aMapLocation.getLocationType());
            failedBean.setLocTypeDes(aMapLocation.getDescription());
            mLocationCallback.failed(failedBean);
            mLocationCallback.complete();
        }
    }

    private String getResourceString(int stringId) {
        return mContext.getResources().getString(stringId);
    }
}
