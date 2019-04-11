package zp.com.baidumapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import zp.com.baidulocation.baidulocation.BaiduLocationManager;
import zp.com.baidulocation.bean.LocationFailedBean;
import zp.com.baidulocation.bean.LocationSuccessBean;
import zp.com.baidulocation.callback.GpsLocationCallback;

/**
 * @author zpan
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLocation();
    }

    private void initLocation() {

        new BaiduLocationManager().getLocation(this, new GpsLocationCallback() {
            @Override
            public void success(LocationSuccessBean response) {
                // 定位成功
            }

            @Override
            public void failed(LocationFailedBean response) {
                // 定位失败
            }

            @Override
            public void complete() {
                // 定位完成
            }
        });
    }
}
