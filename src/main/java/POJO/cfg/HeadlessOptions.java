package POJO.cfg;

public class HeadlessOptions {
	
	private int viewportWidth;
	private int viewportHeight;
	
	public HeadlessOptions() {}
	
	public HeadlessOptions(int viewportWidth, int viewportHeight) {
		super();
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
	}

	public int getViewportWidth() {
		return viewportWidth;
	}

	public void setViewportWidth(int viewportWidth) {
		this.viewportWidth = viewportWidth;
	}

	public int getViewportHeight() {
		return viewportHeight;
	}

	public void setViewpoerHeight(int viewportHeight) {
		this.viewportHeight = viewportHeight;
	}
}
