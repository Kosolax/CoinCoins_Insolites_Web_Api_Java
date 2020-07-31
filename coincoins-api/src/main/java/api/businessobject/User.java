package api.businessobject;

import java.util.List;

import api.entity.UserEntity;

public class User {
	private int id;
	private List<PictureUser> listPictureUser;
	private String mail;
	private String password;
	private String pseudonym;
	
	public User(List<PictureUser> listPictureUser, String mail, String password, String pseudonym) {
		super();
		this.listPictureUser = listPictureUser;
		this.mail = mail;
		this.password = password;
		this.pseudonym = pseudonym;
	}
	
	public User(int id, List<PictureUser> listPictureUser, String mail, String password, String pseudonym) {
		super();
		this.id = id;
		this.listPictureUser = listPictureUser;
		this.mail = mail;
		this.password = password;
		this.pseudonym = pseudonym;
	}
	
	public User(UserEntity userEntity, List<PictureUser> listPictureUser) {
		this.id = userEntity.getId();
		this.listPictureUser = listPictureUser;
		this.mail = userEntity.getMail();
		this.password = userEntity.getPassword();
		this.pseudonym = userEntity.getPseudonym();
	}
	
	public int getId() {
		return this.id;
	}
	
	public List<PictureUser> getListPictureUser() {
		return this.listPictureUser;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getPseudonym() {
		return this.pseudonym;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setListPictureUser(List<PictureUser> listPictureUser) {
		this.listPictureUser = listPictureUser;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", listPictureUser=" + listPictureUser + ", mail=" + mail + ", password=" + password
				+ ", pseudonym=" + pseudonym + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((listPictureUser == null) ? 0 : listPictureUser.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pseudonym == null) ? 0 : pseudonym.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (listPictureUser == null) {
			if (other.listPictureUser != null)
				return false;
		} else if (!listPictureUser.equals(other.listPictureUser))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pseudonym == null) {
			if (other.pseudonym != null)
				return false;
		} else if (!pseudonym.equals(other.pseudonym))
			return false;
		return true;
	}
}
