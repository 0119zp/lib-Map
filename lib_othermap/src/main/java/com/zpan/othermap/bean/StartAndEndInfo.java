package com.zpan.othermap.bean;

/**
 * 起始点信息
 *
 * @author zpan
 */
public class StartAndEndInfo {

    /** 起点名称 */
    private String startName;
    /** 起点坐标 */
    private Location startLocation;
    /** 终点名称 */
    private String endName;
    /** 终点坐标 */
    private Location endLocation;

    public StartAndEndInfo() {
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    @Override
    public String toString() {
        return "StartAndEndInfo{"
            + "startName='"
            + startName
            + '\''
            + ", fromLocation="
            + startLocation
            + ", endName='"
            + endName
            + '\''
            + ", to="
            + endLocation
            + '}';
    }
}
