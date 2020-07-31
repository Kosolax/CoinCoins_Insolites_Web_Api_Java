package api.businessobject;

import java.util.List;

import api.entity.PlaceEntity;

public class Place {
	private String description;
	private int id;
	private Double latitude;
	private List<PicturePlace> listPicturePlace;
	private Double longitude;
	private String title;
	
	public Place(String description, Double latitude, List<PicturePlace> listPicturePlace, Double longitude, String title) {
		this.description = description;
		this.latitude = latitude;
		this.listPicturePlace = listPicturePlace;
		this.longitude = longitude;
		this.title = title;
	}
	
	public Place(int id, String description, Double latitude, List<PicturePlace> listPicturePlace, Double longitude, String title) {
		this.id = id;
		this.description = description;
		this.latitude = latitude;
		this.listPicturePlace = listPicturePlace;
		this.longitude = longitude;
		this.title = title;
	}
	
	public Place(PlaceEntity placeEntity, List<PicturePlace> listPicturePlace) {
		this.id = placeEntity.getId();
		this.listPicturePlace = listPicturePlace;
		this.description = placeEntity.getDescription();
		this.latitude = placeEntity.getLatitude();
		this.longitude = placeEntity.getLongitude();
		this.title = placeEntity.getTitle();
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Double getLatitude() {
		return this.latitude;
	}
	
	public List<PicturePlace> getListPicturePlace() {
		return this.listPicturePlace;
	}
	
	public Double getLongitude() {
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
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Place [description=" + this.description + ", id=" + this.id + ", latitude=" + this.latitude + ", longitude="
				+ this.longitude + ", title=" + this.title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((listPicturePlace == null) ? 0 : listPicturePlace.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Place other = (Place) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (listPicturePlace == null) {
			if (other.listPicturePlace != null)
				return false;
		} else if (!listPicturePlace.equals(other.listPicturePlace))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
