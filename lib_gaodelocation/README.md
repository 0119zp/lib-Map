### lib_gaodelocation 使用步骤

[高德开发文档](https://lbs.amap.com/api/android-location-sdk/locationsummary/)

#### 一、module的build.gradle中依赖该库
~~~
implementation project(':lib-gaodelocation')
~~~
#### 二、代码中调用
~~~
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
~~~
#### 三、注意项
要特别注意在6.0以上的版本调用定位时需要自己动态配置定位权限，locationlib库不提供动态申请定位权限的功能。

#### 四、返回字段说明
###### LocationSuccessBean

~~~
    // 定位类型
    private int locationType;
    // 定位时间
    private String locationTime;
    // 纬度
    private double locationLatitude;
    // 经度
    private double locationLongitude;
    // 国家
    private String country;
    // 省
    private String province;
    // 市
    private String city;
    // 城市编码
    private String cityCode;
    // 区
    private String district;
    // 区域码
    private String adCode;
~~~

###### LocationFailedBean

~~~
    // 错误码
    private int errorCode;
    // 错误信息
    private String errorInfo;
    // 错误描述
    private String errorDetail;
~~~