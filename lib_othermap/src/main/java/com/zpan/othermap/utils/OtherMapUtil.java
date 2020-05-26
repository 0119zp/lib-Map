package com.zpan.othermap.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

import com.zpan.othermap.operator.BaiduMapOperator;
import com.zpan.othermap.operator.GaodeMapOperator;
import com.zpan.othermap.operator.GoogleMapOperator;
import com.zpan.othermap.operator.TencentOperator;
import java.util.List;

/**
 * Class description
 *
 * @author zpan
 */
public class OtherMapUtil {

    /**
     * 检查当前手机是否安装对应包名的应用
     *
     * @param context context
     * @param packageName 应用包名
     * @return true or false
     */
    public static boolean checkInstalled(Context context, String packageName) {
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfos) {
            if (packageInfo.packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对应包名的地图中文名，传入错误包名返回 ""
     *
     * @param packageName 地图包名
     * @return 地图中文名
     */
    public static String getMapName(String packageName) {
        String name = "";
        if (!TextUtils.isEmpty(packageName)) {
            switch (packageName) {
                case BaiduMapOperator.PACKAGE_NAME:
                    name = BaiduMapOperator.CHINA_NAME;
                    break;
                case GaodeMapOperator.PACKAGE_NAME:
                    name = GaodeMapOperator.CHINA_NAME;
                    break;
                case GoogleMapOperator.PACKAGE_NAME:
                    name = GoogleMapOperator.CHINA_NAME;
                    break;
                case TencentOperator.PACKAGE_NAME:
                    name = TencentOperator.CHINA_NAME;
                    break;
                default:
                    break;
            }
        }
        return name;
    }

    /**
     * 获取对应包名的地图操作类class,传入错误返回 null
     *
     * @param packageName 地图包名
     * @return 地图操作类class
     */
    public static Class getMapClass(String packageName) {
        if (!TextUtils.isEmpty(packageName)) {
            switch (packageName) {
                case BaiduMapOperator.PACKAGE_NAME:
                    return BaiduMapOperator.class;
                case GaodeMapOperator.PACKAGE_NAME:
                    return GaodeMapOperator.class;
                case GoogleMapOperator.PACKAGE_NAME:
                    return GoogleMapOperator.class;
                case TencentOperator.PACKAGE_NAME:
                    return TencentOperator.class;
                default:
                    return null;
            }
        }
        return null;
    }
}
