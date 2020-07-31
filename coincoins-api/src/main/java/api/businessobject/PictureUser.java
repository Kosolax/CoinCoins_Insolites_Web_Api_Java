package api.businessobject;

import api.entity.PictureUserEntity;

public class PictureUser extends AbstractPicture {
	private int idUser;

	public PictureUser(int idUser, Double latitude, Double longitude, String svgLink) {
		super(latitude, longitude, svgLink);
		this.idUser = idUser;
	}

	public PictureUser(int id, int idUser, Double latitude, Double longitude, String svgLink) {
		super(id, latitude, longitude, svgLink);
		this.idUser = idUser;
	}
	
	public PictureUser(PictureUserEntity pictureUserEntity) {
		super(pictureUserEntity.getId(), pictureUserEntity.getLatitude(), pictureUserEntity.getLongitude(), pictureUserEntity.getSvgLink());
		this.idUser = pictureUserEntity.getIdUser();
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "PictureUserEntity [idUser=" + this.idUser + ", id=" + this.id + ", svgLink=" + this.svgLink + ", latitude="
				+ this.latitude + ", longitude=" + this.longitude + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idUser;
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
		PictureUser other = (PictureUser) obj;
		if (idUser != other.idUser)
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
