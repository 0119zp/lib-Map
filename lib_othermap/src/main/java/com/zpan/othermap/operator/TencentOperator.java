package com.zpan.othermap.operator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.zpan.othermap.bean.StartAndEndInfo;

/**
 * @author zpan
 * @date 2020/5/26 3:16 PM
 * 接口地址：https://lbs.qq.com/webApi/uriV1/uriGuide/uriMobileRoute
 *
 * description: 腾讯地图
 */
public class TencentOperator extends BaseMapOperator {

    /** 腾讯地图包名 */
    public static final String PACKAGE_NAME = "com.tencent.map";
    /** 腾讯地图名称 */
    public static final String CHINA_NAME = "腾讯地图";

    @Override
    public void location(Activity activity) {

    }

    @Override
    public void navigation(Activity activity, StartAndEndInfo entity) {
        if (entity == null || entity.getEndLocation() == null) {
            throw new RuntimeException("参数错误！缺少必要参数,是否传入目标点坐标?");
        }

        try {
            Uri uri = Uri.parse(getStartTencentMapUrl(entity));
            Intent intent = new Intent();
            intent.setData(uri);

            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            mOnFailListener.onFail();
        }
    }

    private String getStartTencentMapUrl(StartAndEndInfo entity) {
        StringBuilder sb = new StringBuilder("qqmap://map/routeplan?");
        sb.append("type=").append("drive").append("&");
        if (!TextUtils.isEmpty(entity.getStartName())) {
            sb.append("from=").append(entity.getStartName()).append("&");
        }
        sb.append("fromcoord=")
            .append(entity.getStartLocation().getLatitude())
            .append(",")
            .append(entity.getStartLocation().getLongitude())
            .append("&");
        if (!TextUtils.isEmpty(entity.getEndName())) {
            sb.append("to=").append(entity.getEndName()).append("&");
        }
        sb.append("tocoord=")
            .append(entity.getEndLocation().getLatitude())
            .append(",")
            .append(entity.getEndLocation().getLongitude())
            .append("&");
        sb.append("referer=").append("key");
        return sb.toString();
    }
}
