package com.zpan.othermap.operator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.zpan.othermap.bean.StartAndEndInfo;
import com.zpan.othermap.utils.GpsUtil;

/**
 * 调用百度地图实现类
 * 接口文档：http://lbsyun.baidu.com/index.php?title=uri/api/android
 *
 * @author zpan
 */
public class BaiduMapOperator extends BaseMapOperator {

    /** 百度地图包名 */
    public static final String PACKAGE_NAME = "com.baidu.BaiduMap";
    /** 百度地图名称 */
    public static final String CHINA_NAME = "百度地图";

    @Override
    public void location(Activity activity) {
        try {
            Intent i = new Intent();
            i.setData(Uri.parse("baidumap://map"));
            activity.startActivity(i);
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
        // 火星坐标系转成百度坐标系
        double[] d = GpsUtil.gcj02_To_Bd09(entity.getEndLocation().getLatitude(), entity.getEndLocation().getLongitude());
        entity.getEndLocation().setLatitude(d[0]);
        entity.getEndLocation().setLongitude(d[1]);
        if (entity.getStartLocation() != null) {
            double[] d2 = GpsUtil.gcj02_To_Bd09(entity.getStartLocation().getLatitude(), entity.getStartLocation().getLongitude());
            entity.getStartLocation().setLatitude(d2[0]);
            entity.getStartLocation().setLongitude(d2[1]);
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getStartBaiduMapUrl(entity)));
            intent.setPackage(PACKAGE_NAME);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            mOnFailListener.onFail();
        }
    }

    /**
     * 拼接启动百度地图的 url
     *
     * @param entity 起点终点信息数据
     * @return url
     */
    private String getStartBaiduMapUrl(StartAndEndInfo entity) {
        StringBuilder sb = new StringBuilder("baidumap://map/direction?");
        if (entity.getStartLocation() != null) {
            sb.append("origin=name:我的位置|latlng:")
                .append(entity.getStartLocation().getLatitude())
                .append(",")
                .append(entity.getStartLocation().getLongitude())
                .append("&");
        }
        sb.append("destination=name:")
            .append(entity.getEndName())
            .append("|latlng:")
            .append(entity.getEndLocation().getLatitude())
            .append(",")
            .append(entity.getEndLocation().getLongitude());
        Log.e("dev", sb.toString());
        return sb.toString();
    }
}
