package com.zpan.othermap.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;
import com.zpan.othermap.R;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zpan
 * @date 2020/5/27 4:59 PM
 *
 * description: 选择弹框
 */
public class BottomSelectDialog extends DialogFragment {

    private HashMap<String, View.OnClickListener> mapData;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_select_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {
        LinearLayoutCompat mMapList = view.findViewById(R.id.ll_select_map);
        AppCompatTextView mMapCancel = view.findViewById(R.id.tv_bottom_select_cancel);

        mMapCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 地图按钮
        for (Map.Entry<String, View.OnClickListener> stringOnClickListenerEntry : mapData.entrySet()) {
            String key = stringOnClickListenerEntry.getKey();
            final View.OnClickListener listener = stringOnClickListenerEntry.getValue();
            if (!TextUtils.isEmpty(key)) {
                View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_select_item, null);
                TextView tvItem = itemView.findViewById(R.id.tv_bottom_select_item);

                tvItem.setText(key);
                tvItem.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onClick(v);
                        }
                        dismiss();
                    }
                });
                mMapList.addView(itemView);
            }
        }
    }

    public void setMapList(HashMap<String, View.OnClickListener> hashMap) {
        this.mapData = hashMap;
    }
}
