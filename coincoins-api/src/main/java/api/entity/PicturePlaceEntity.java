package api.entity;

import api.businessobject.PicturePlace;

public class PicturePlaceEntity extends AbstractPictureEntity {
	private int idPlace;

	public PicturePlaceEntity(int idPlace, double latitude, double longitude, String svgLink) {
		super(latitude, longitude, svgLink);
		this.idPlace = idPlace;
	}

	public PicturePlaceEntity(int id, int idPlace, double latitude, double longitude, String svgLink) {
		super(id, latitude, longitude, svgLink);
		this.idPlace = idPlace;
	}
	
	public PicturePlaceEntity(PicturePlace picturePlace) {
		super(picturePlace.getId(),picturePlace.getLatitude(), picturePlace.getLongitude(), picturePlace.getSvgLink());
		this.idPlace = picturePlace.getIdPlace();
	}
	
	public int getIdPlace() {
		return this.idPlace;
	}
	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}

	@Override
	public String toString() {
		return "PicturePlaceEntity [idPlace=" + this.idPlace + ", id=" + this.id + ", svgLink=" + this.svgLink + ", latitude="
				+ this.latitude + ", longitude=" + this.longitude + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PicturePlaceEntity other = (PicturePlaceEntity) obj;
		if (this.id != other.id)
			return false;
		if (this.idPlace != other.idPlace)
			return false;
		if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (this.svgLink == null) {
			if (other.svgLink != null)
				return false;
		} else if (!this.svgLink.equals(other.svgLink))
			return false;
		return true;
	}
}
