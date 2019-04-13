package zp.com.baidumapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zpan.othermap.utils.GPSUtil;
import com.zpan.othermap.bean.Location;
import com.zpan.othermap.bean.NavParamEntity;
import com.zpan.othermap.ThirdPartyMapManager;
import zp.com.baidulocation.LocationLibManager;
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

        findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOtherMap();
            }
        });

        initLocation();
    }

    private void initOtherMap() {
        Location to = new Location(121.53, 31.22);
        Location from = new Location(121.45, 31.23);

        NavParamEntity entity = new NavParamEntity(from, to);
        entity.setFromName("当前位置");
        entity.setToName("上海");
        //百度坐标系转成火星坐标系
        double[] d = GPSUtil.bd09_To_Gcj02(entity.getTo().getLatitude(), entity.getTo().getLongitude());
        entity.getTo().setLatitude(d[0]);
        entity.getTo().setLongitude(d[1]);
        double[] d2 = GPSUtil.bd09_To_Gcj02(entity.getFrom().getLatitude(), entity.getFrom().getLongitude());
        entity.getFrom().setLatitude(d2[0]);
        entity.getFrom().setLongitude(d2[1]);

        ThirdPartyMapManager thirdPartyMapManager = new ThirdPartyMapManager(MainActivity.this, entity);
        thirdPartyMapManager.navigation();
    }

    private void initLocation() {

        LocationLibManager.getInstance().getLocation(this, new GpsLocationCallback() {
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
