package api.entity;

import api.businessobject.Place;

public class PlaceEntity {
	private String description;
	private int id;
	private double latitude;
	private double longitude;
	private String title;
	
	public PlaceEntity(String description, double latitude, double longitude, String title) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
	}
	
	public PlaceEntity(int id, String description, double latitude, double longitude, String title) {
		this.id = id;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
	}
	
	public PlaceEntity(Place place) {
		this.id = place.getId();;
		this.description = place.getDescription();
		this.latitude = place.getLatitude();
		this.longitude = place.getLongitude();
		this.title = place.getTitle();
	}
	
	public String getDescription() {
		return this.description;
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
	
	public String getTitle() {
		return this.title;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "PlaceEntity [description=" + this.description + ", id=" + this.id + ", latitude=" + this.latitude + ", longitude="
				+ this.longitude + ", title=" + this.title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + this.id;
		long temp;
		temp = Double.doubleToLongBits(this.latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceEntity other = (PlaceEntity) obj;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.id != other.id)
			return false;
		if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		return true;
	}
}
