package zp.com.baidumapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zp.com.locationlib.BaiduLocation;
import zp.com.locationlib.BaiduLocationFailed;
import zp.com.locationlib.BaiduLocationManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLocation();
    }

    private void initLocation() {

        new BaiduLocationManager().getLocation(this, new BaiduLocationManager.GPSLocationCallback() {
            @Override
            public void success(BaiduLocation response) {
                // 定位成功
            }

            @Override
            public void failed(BaiduLocationFailed response) {
                // 定位失败
            }

            @Override
            public void complete() {
                // 定位失败
            }
        });

    }
}
