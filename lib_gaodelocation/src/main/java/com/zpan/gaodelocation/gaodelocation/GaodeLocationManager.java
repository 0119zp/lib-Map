package com.zpan.gaodelocation.gaodelocation;

import android.content.Context;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.zpan.gaodelocation.bean.LocationFailedBean;
import com.zpan.gaodelocation.bean.LocationSuccessBean;
import com.zpan.gaodelocation.callback.GpsLocationCallback;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class GaodeLocationManager {

    public void getLocation(Context context, final GpsLocationCallback callback) {
        AMapLocationClientOption option = getDefaultOption();
        final AMapLocationClient locationClient = new AMapLocationClient(context.getApplicationContext());
        locationClient.setLocationOption(option);

        // 注入解除注册逻辑
        final GaodeLocationListener gaodeLocationListener = new GaodeLocationListener(context);
        gaodeLocationListener.setLocationCallback(new GpsLocationCallback() {

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
                locationClient.unRegisterLocationListener(gaodeLocationListener);
            }
        });
        // 百度定位监听
        locationClient.setLocationListener(gaodeLocationListener);
        if (!locationClient.isStarted()) {
            locationClient.startLocation();
        }
    }

    /**
     * 设置默认参数
     *
     * @return 默认参数
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setGpsFirst(true);
        //可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setHttpTimeOut(30000);
        //可选，设置定位间隔。默认为2秒
        mOption.setInterval(2000);
        //可选，设置是否返回逆地理地址信息。默认是true
        mOption.setNeedAddress(true);
        //可选，设置是否单次定位。默认是false
        mOption.setOnceLocation(true);
        //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mOption.setOnceLocationLatest(false);
        //可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        //可选，设置是否使用传感器。默认是false
        mOption.setSensorEnable(false);
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setWifiScan(true);
        //可选，设置是否使用缓存定位，默认为true
        mOption.setLocationCacheEnable(true);
        //可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        return mOption;
    }
}
