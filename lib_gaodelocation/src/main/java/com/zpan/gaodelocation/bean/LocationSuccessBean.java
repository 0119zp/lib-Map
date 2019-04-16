package com.zpan.gaodelocation.bean;

/**
 * @author zpan
 * @date 2019/3/18
 */
public class LocationSuccessBean {

    /** 定位类型 */
    private int locationType;
    /** 定位时间 */
    private String locationTime;
    /** 纬度 */
    private double locationLatitude;
    /** 经度 */
    private double locationLongitude;
    /** 国家 */
    private String country;
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 城市编码 */
    private String cityCode;
    /** 区 */
    private String district;
    /** 区域码 */
    private String adCode;

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public String getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(String locationTime) {
        this.locationTime = locationTime;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }
}
