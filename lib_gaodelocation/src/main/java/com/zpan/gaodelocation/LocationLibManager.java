package com.zpan.gaodelocation;

import android.content.Context;
import com.zpan.gaodelocation.callback.GpsLocationCallback;
import com.zpan.gaodelocation.gaodelocation.GaodeLocationManager;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class LocationLibManager {

    private LocationLibManager() {
    }

    private static class LocationHolder {
        private static final LocationLibManager INSTANCE = new LocationLibManager();
    }

    public static LocationLibManager getInstance() {
        return LocationHolder.INSTANCE;
    }

    /**
     * 获取定位信息
     *
     * @param context 上下文对象
     * @param callback 定位结果回调
     */
    public void getLocation(Context context, GpsLocationCallback callback) {
        new GaodeLocationManager().getLocation(context, callback);
    }
}
