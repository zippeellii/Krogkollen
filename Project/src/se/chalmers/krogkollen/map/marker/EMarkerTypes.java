package se.chalmers.krogkollen.map.marker;

/**
 * EMarkerTypes (UTF-8)
 * <p/>
 * Author: Johan Backman
 * Date: 2013-09-25
 */

public enum EMarkerTypes {

    GRAY("@drawable/gray_marker_bg"),

    GREEN("@drawable/gray_marker_bg"),

    YELLOW("@drawable/gray_marker_bg"),

    RED("@drawable/gray_marker_bg");

    private String value;

    private EMarkerTypes(String value) {
        this.value = value;
    }

    private String getMarkerType() {
        return value;
    }
}
