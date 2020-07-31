package api.businessobject;

public abstract class AbstractPicture {
	protected int id;
	protected Double latitude;
	protected Double longitude;
	protected String svgLink;

	public AbstractPicture(Double latitude, Double longitude, String svgLink) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.svgLink = svgLink;
	}

	public AbstractPicture(int id, Double latitude, Double longitude, String svgLink) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.svgLink = svgLink;
	}

	public int getId() {
		return this.id;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public String getSvgLink() {
		return this.svgLink;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setSvgLink(String svgLink) {
		this.svgLink = svgLink;
	}	
}
