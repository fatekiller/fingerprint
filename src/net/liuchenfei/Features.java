package net.liuchenfei;

public class Features {
	private String user_agent;
	private String plugins;
	private String fonts;
	private boolean video;
	private String supercookies;
	private String http_accept;
	private int timezone;
	private boolean cookie_enabled;

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

	public boolean isVideo() {
		return video;
	}

	public void setVideo(boolean video) {
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

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public boolean isCookie_enabled() {
		return cookie_enabled;
	}

	public void setCookie_enabled(boolean cookie_enabled) {
		this.cookie_enabled = cookie_enabled;
	}

}
