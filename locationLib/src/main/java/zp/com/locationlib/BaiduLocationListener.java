package zp.com.locationlib;

import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

public class BaiduLocationListener  extends BDAbstractLocationListener {

    private static final String TAG = BaiduLocationListener.class.getSimpleName();

    BaiduLocationManager.GPSLocationCallback mBaiduLocationCallback;

    public void setBaiduLocationCallback(BaiduLocationManager.GPSLocationCallback baiduLocationCallback) {
        mBaiduLocationCallback = baiduLocationCallback;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (mBaiduLocationCallback == null) {
            Log.e(TAG, "使用 BaiduLocationListener 之前需要调用 setBaiduLocationCallback");
            return;
        }
        Log.d(TAG, "onReceiveLocation: " + bdLocation.getCity() + " " + bdLocation.getLatitude() + " " + bdLocation.getLongitude() + " " + bdLocation.getLocType() + ":" + bdLocation.getLocTypeDescription());
        if (bdLocation.getLocType() == 61 || bdLocation.getLocType() == 66 || bdLocation.getLocType() == 161) {

            //61	GPS定位结果，GPS定位成功
            //66	离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果
            //161	网络定位结果，网络定位成功
            BaiduLocation bean = new BaiduLocation();
            bean.time = bdLocation.getTime();
            bean.altitude = bdLocation.getAltitude();
            bean.latitude = bdLocation.getLatitude();
            bean.longitude = bdLocation.getLongitude();
            bean.city = bdLocation.getCity();
            mBaiduLocationCallback.success(bean);
            mBaiduLocationCallback.complete();
        } else {
            BaiduLocationFailed bean = new BaiduLocationFailed();
            bean.locType = bdLocation.getLocType();
            bean.locTypeDescription = bdLocation.getLocTypeDescription();
            mBaiduLocationCallback.failed(bean);
            mBaiduLocationCallback.complete();
        }

    }

}
