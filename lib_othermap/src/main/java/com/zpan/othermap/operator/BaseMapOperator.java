package com.zpan.othermap.operator;

import android.app.Activity;
import com.zpan.othermap.bean.NavParamEntity;

/**
 * @author zpan
 */
public abstract class BaseMapOperator {

    protected OnFailListener mOnFailListener;

    abstract void location(Activity activity);

    public abstract void navigation(Activity activity, NavParamEntity entity);

    public void setOnFailListener(OnFailListener listener) {
        mOnFailListener = listener;
    }

    public interface OnFailListener {

        void onFail();
    }
}
