package com.zpan.othermap.operator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.zpan.othermap.bean.StartAndEndInfo;
import com.zpan.othermap.utils.GpsUtil;

/**
 * 调用谷歌地图的实现类
 *
 * @author zpan
 */
public class GoogleMapOperator extends BaseMapOperator {

    /** 谷歌地图包名 */
    public static final String PACKAGE_NAME = "com.google.android.apps.maps";
    /** 谷歌地图名称 */
    public static final String CHINA_NAME = "Google Map地图";

    @Override
    public void location(Activity activity) {

    }

    @Override
    public void navigation(Activity activity, StartAndEndInfo entity) {
        if (entity == null || entity.getEndLocation() == null) {
            throw new RuntimeException("参数错误！缺少必要参数,是否传入目标点坐标?");
        }

        //火星坐标系转成84坐标
        double[] d = GpsUtil.gcj02_To_Gps84(entity.getEndLocation().getLatitude(), entity.getEndLocation().getLongitude());
        entity.getEndLocation().setLatitude(d[0]);
        entity.getEndLocation().setLongitude(d[1]);
        double[] d2 = GpsUtil.gcj02_To_Gps84(entity.getStartLocation().getLatitude(), entity.getStartLocation().getLongitude());
        entity.getStartLocation().setLatitude(d2[0]);
        entity.getStartLocation().setLongitude(d2[1]);

        String uri = "google.navigation:q=" + entity.getEndLocation().getLatitude() + "," + entity.getEndLocation().getLongitude();
        Log.e("dev", uri);
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage(PACKAGE_NAME);
        activity.startActivity(mapIntent);
    }
}
