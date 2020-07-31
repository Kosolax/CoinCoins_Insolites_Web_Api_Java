package api.entity;

import api.businessobject.PictureUser;

public class PictureUserEntity extends AbstractPictureEntity{
	private int idUser;

	public PictureUserEntity(int idUser, double latitude, double longitude, String svgLink) {
		super(latitude, longitude, svgLink);
		this.idUser = idUser;
	}

	public PictureUserEntity(int id, int idUser, double latitude, double longitude, String svgLink) {
		super(id, latitude, longitude, svgLink);
		this.idUser = idUser;
	}
	
	public PictureUserEntity(PictureUser pictureUser) {
		super(pictureUser.getId(), pictureUser.getLatitude(), pictureUser.getLongitude(), pictureUser.getSvgLink());
		this.idUser = pictureUser.getIdUser();
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
		PictureUserEntity other = (PictureUserEntity) obj;
		if (this.id != other.id)
			return false;
		if (this.idUser != other.idUser)
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
