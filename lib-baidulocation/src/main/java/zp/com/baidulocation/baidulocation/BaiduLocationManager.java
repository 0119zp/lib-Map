package zp.com.baidulocation.baidulocation;

import android.content.Context;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import zp.com.baidulocation.bean.LocationFailedBean;
import zp.com.baidulocation.bean.LocationSuccessBean;
import zp.com.baidulocation.callback.GpsLocationCallback;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class BaiduLocationManager {

    public void getLocation(Context context, final GpsLocationCallback callback) {
        LocationClientOption option = new LocationClientOption();
        // 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        // 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(0);
        // 可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        // 可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        // 可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(false);
        // 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，
        // 结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(false);
        // 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(false);
        // 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(true);
        // 可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        // 可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);

        final LocationClient locationClient = new LocationClient(context.getApplicationContext());
        locationClient.setLocOption(option);

        // 注入解除注册逻辑
        final BaiduLocationListener baiduLocationListener = new BaiduLocationListener();
        GpsLocationCallback gpsLocationCallback = new GpsLocationCallback() {

            @Override
            public void success(LocationSuccessBean response) {
                callback.success(response);
            }

            @Override
            public void failed(LocationFailedBean response) {
                callback.failed(response);
            }

            @Override
            public void complete() {
                callback.complete();
                locationClient.unRegisterLocationListener(baiduLocationListener);
            }
        };
        baiduLocationListener.setBaiduLocationCallback(gpsLocationCallback);
        // 百度定位
        locationClient.registerLocationListener(baiduLocationListener);
        if (!locationClient.isStarted()) {
            locationClient.start();
        } else {
            locationClient.requestLocation();
        }
    }
}
