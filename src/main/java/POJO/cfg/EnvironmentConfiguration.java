package POJO.cfg;

import javax.annotation.Nullable;

public class EnvironmentConfiguration {
		
	private String browser;
	private boolean headless;
	@Nullable private HeadlessOptions headlessOptions;
	private boolean windowMaximize;
	private String driverPath;
	private int implicitlyWait;
	private String driverKey;
	private String baseUrl;
	
	public EnvironmentConfiguration() {}
	
	public EnvironmentConfiguration(String browser, boolean headless, @Nullable HeadlessOptions headlessOptions,
			boolean windowMaximize, String driverPath, int implicitlyWait, String driverKey, String baseUrl) {
		super();
		this.browser = browser;
		this.headless = headless;
		this.headlessOptions = headlessOptions;
		this.windowMaximize = windowMaximize;
		this.driverPath = driverPath;
		this.implicitlyWait = implicitlyWait;
		this.driverKey = driverKey;
		this.baseUrl = baseUrl;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public boolean isHeadless() {
		return headless;
	}
	public void setHeadless(boolean headless) {
		this.headless = headless;
	}
	public HeadlessOptions getHeadlessOptions() {
		return headlessOptions;
	}
	public void setHeadlessOptions(HeadlessOptions headlessOptions) {
		this.headlessOptions = headlessOptions;
	}
	public boolean isWindowMaximize() {
		return windowMaximize;
	}
	public void setWindowMaximize(boolean windowMaximize) {
		this.windowMaximize = windowMaximize;
	}
	public String getDriverPath() {
		return driverPath;
	}
	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}
	public int getImplicitlyWait() {
		return implicitlyWait;
	}
	public void setImplicitlyWait(int implicitlyWait) {
		this.implicitlyWait = implicitlyWait;
	}
	public String getDriverKey() {
		return driverKey;
	}
	public void setDriverKey(String driverKey) {
		this.driverKey = driverKey;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
