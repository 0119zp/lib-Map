package com.zpan.othermap.utils;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpan.othermap.R;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zpan
 */
public class BottomSheetUtil {

    public static BottomSheetDialog createTextBottomSheetDialog(Context context, HashMap<String, View.OnClickListener> hashMap) {

        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet_text, null);
        LinearLayout llContent = view.findViewById(R.id.ll_bottom_sheet);

        // 地图按钮
        for (Map.Entry<String, View.OnClickListener> stringOnClickListenerEntry : hashMap.entrySet()) {
            String key = stringOnClickListenerEntry.getKey();
            final View.OnClickListener listener = stringOnClickListenerEntry.getValue();
            if (!TextUtils.isEmpty(key)) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet_item_text, null);
                TextView tvItem = itemView.findViewById(R.id.tv_bottom_sheet_item);

                tvItem.setText(key);
                tvItem.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onClick(v);
                        }
                        dialog.dismiss();
                    }
                });
                llContent.addView(itemView);
            }
        }

        // 取消按钮
        View itemCancelView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet_item_text, null);
        TextView itemCancel = itemCancelView.findViewById(R.id.tv_bottom_sheet_item);
        itemCancel.setText("取消");
        itemCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        llContent.addView(itemCancelView);

        dialog.setContentView(view);
        try {
            // hack bg color of the BottomSheetDialog
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.setBackgroundResource(android.R.color.transparent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }
}
