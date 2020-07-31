package api.businessobject;

import api.entity.PicturePlaceEntity;

public class PicturePlace extends AbstractPicture {
	private int idPlace;

	public PicturePlace(int idPlace, Double latitude, Double longitude, String svgLink) {
		super(latitude, longitude, svgLink);
		this.idPlace = idPlace;
	}

	public PicturePlace(int id, int idPlace, Double latitude, Double longitude, String svgLink) {
		super(id, latitude, longitude, svgLink);
		this.idPlace = idPlace;
	}
	
	public PicturePlace(PicturePlaceEntity picturePlaceEntity) {
		super(picturePlaceEntity.getId(), picturePlaceEntity.getLatitude(), picturePlaceEntity.getLongitude(), picturePlaceEntity.getSvgLink());
		this.idPlace = picturePlaceEntity.getIdPlace();
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
		int result = 1;
		result = prime * result + id;
		result = prime * result + idPlace;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((svgLink == null) ? 0 : svgLink.hashCode());
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
		PicturePlace other = (PicturePlace) obj;
		if (idPlace != other.idPlace)
			return false;
		if (id != other.id)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (svgLink == null) {
			if (other.svgLink != null)
				return false;
		} else if (!svgLink.equals(other.svgLink))
			return false;
		return true;
	}
}
