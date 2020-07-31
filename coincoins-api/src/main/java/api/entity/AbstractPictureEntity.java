package api.entity;

public abstract class AbstractPictureEntity {
	protected int id;
	protected double latitude;
	protected double longitude;
	protected String svgLink;

	public AbstractPictureEntity(double latitude, double longitude, String svgLink) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.svgLink = svgLink;
	}

	public AbstractPictureEntity(int id, double latitude, double longitude, String svgLink) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.svgLink = svgLink;
	}

	public int getId() {
		return this.id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public String getSvgLink() {
		return this.svgLink;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setSvgLink(String svgLink) {
		this.svgLink = svgLink;
	}
}
