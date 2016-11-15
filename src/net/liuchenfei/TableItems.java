package net.liuchenfei;

/**
 * Created by liuchenfei on 2016/11/14.
 */
public class TableItems {
    private String userName;
    private String deviceName;
    private String data;
    private String fingerprint;
    private String date;
    private String user_agent;
    private String plugins;
    private String fonts;
    private int video;
    private String supercookies;
    private String http_accept;
    private float timezone;
    private int cookie_enabled;

    public TableItems(String userName, String deviceName, String data, String fingerprint, String date, String user_agent, String plugins, String fonts, int video, String supercookies, String http_accept, float timezone, int cookie_enabled) {
        this.userName = userName;
        this.deviceName = deviceName;
        this.data = data;
        this.fingerprint = fingerprint;
        this.date = date;
        this.user_agent = user_agent;
        this.plugins = plugins;
        this.fonts = fonts;
        this.video = video;
        this.supercookies = supercookies;
        this.http_accept = http_accept;
        this.timezone = timezone;
        this.cookie_enabled = cookie_enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getPlugins() {
        return plugins;
    }

    public void setPlugins(String plugins) {
        this.plugins = plugins;
    }

    public String getFonts() {
        return fonts;
    }

    public void setFonts(String fonts) {
        this.fonts = fonts;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public String getSupercookies() {
        return supercookies;
    }

    public void setSupercookies(String supercookies) {
        this.supercookies = supercookies;
    }

    public String getHttp_accept() {
        return http_accept;
    }

    public void setHttp_accept(String http_accept) {
        this.http_accept = http_accept;
    }

    public float getTimezone() {
        return timezone;
    }

    public void setTimezone(float timezone) {
        this.timezone = timezone;
    }

    public int getCookie_enabled() {
        return cookie_enabled;
    }

    public void setCookie_enabled(int cookie_enabled) {
        this.cookie_enabled = cookie_enabled;
    }
}
