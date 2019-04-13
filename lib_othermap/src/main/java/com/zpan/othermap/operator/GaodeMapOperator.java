package com.zpan.othermap.operator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.zpan.othermap.bean.StartAndEndInfo;

/**
 * 调用高德地图的实现类
 * 接口文档：http://lbs.amap.com/api/amap-mobile/guide/android/navigation/
 *
 * @author zpan
 */
public class GaodeMapOperator extends BaseMapOperator {

    /** 高德地图包名 */
    public static final String PACKAGE_NAME = "com.autonavi.minimap";
    /** 高德地图名称 */
    public static final String CHINA_NAME = "高德地图";
    /** 当前应用的名字,可修改 */
    private static final String APPLICATION_NAME = "someOne";

    @Override
    public void location(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("androidamap://myLocation?sourceApplication=" + APPLICATION_NAME));
            intent.setPackage(PACKAGE_NAME);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            mOnFailListener.onFail();
        }
    }

    @Override
    public void navigation(Activity activity, StartAndEndInfo entity) {
        if (entity == null || entity.getEndLocation() == null) {
            throw new RuntimeException("参数错误！缺少必要参数,是否传入目标点坐标?");
        }
        Uri uri;
        if (entity.getStartLocation() == null) {
            uri = Uri.parse(getStartGaodeMapUrl(entity));
        } else {
            uri = Uri.parse(getRouteUrl(entity));
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(PACKAGE_NAME);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            mOnFailListener.onFail();
        }
    }

    private String getStartGaodeMapUrl(StartAndEndInfo entity) {
        StringBuilder sb = new StringBuilder("androidamap://navi?");
        sb.append("sourceApplication=").append(APPLICATION_NAME).append("&");
        if (!TextUtils.isEmpty(entity.getEndName())) {
            // POI 名称
            sb.append("poiname").append(entity.getEndName()).append("&");
        }
        sb.append("lat=").append(entity.getEndLocation().getLatitude()).append("&");
        sb.append("lon=").append(entity.getEndLocation().getLongitude()).append("&");
        // 起终点是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
        sb.append("dev=0&");
        // 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5 不走高速且避免收费；
        // 6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵)
        sb.append("style=2");
        Log.e("dev", sb.toString());
        return sb.toString();
    }

    private String getRouteUrl(StartAndEndInfo entity) {
        StringBuilder sb = new StringBuilder("androidamap://route?");
        sb.append("sourceApplication=").append(APPLICATION_NAME).append("&");
        sb.append("slat=").append(entity.getStartLocation().getLatitude()).append("&");
        sb.append("slon=").append(entity.getStartLocation().getLongitude()).append("&");
        if (!TextUtils.isEmpty(entity.getStartName())) {
            sb.append("sname=").append(entity.getStartName()).append("&");
        }
        sb.append("dlat=").append(entity.getEndLocation().getLatitude()).append("&");
        sb.append("dlon=").append(entity.getEndLocation().getLongitude()).append("&");
        if (!TextUtils.isEmpty(entity.getEndName())) {
            sb.append("dname=").append(entity.getEndName()).append("&");
        }
        // 起终点是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
        sb.append("dev=0&");
        // t = 1(公交) =2（驾车） =4(步行)
        sb.append("t=0");
        Log.e("dev", sb.toString());
        return sb.toString();
    }
}
