package com.zpan.othermap.operator;

import android.app.Activity;
import com.zpan.othermap.bean.StartAndEndInfo;

/**
 * 操作第三方地图基类
 *
 * @author zpan
 */
public abstract class BaseMapOperator {

    OnFailListener mOnFailListener;

    public abstract void location(Activity activity);

    public abstract void navigation(Activity activity, StartAndEndInfo entity);

    public void setOnFailListener(OnFailListener listener) {
        mOnFailListener = listener;
    }

    public interface OnFailListener {

        void onFail();
    }
}
