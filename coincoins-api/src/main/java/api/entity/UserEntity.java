package api.entity;

import api.businessobject.User;

public class UserEntity {
	private int id;
	private String mail;
	private String password;
	private String pseudonym;
	
	public UserEntity(String mail, String password, String pseudonym) {
		this.mail = mail;
		this.password = password;
		this.pseudonym = pseudonym;
	}
	
	public UserEntity(int id, String mail, String password, String pseudonym) {
		this.id = id;
		this.mail = mail;
		this.password = password;
		this.pseudonym = pseudonym;
	}
	
	public UserEntity(User user) {
		this.id = user.getId();
		this.mail = user.getMail();
		this.password = user.getPassword();
		this.pseudonym = user.getPseudonym();
	}

	public int getId() {
		return id;
	}

	public String getMail() {
		return mail;
	}

	public String getPassword() {
		return password;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setId(int id) {
		this.id = id;
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
		return "UserEntity [id=" + id + ", mail=" + mail + ", password=" + password + ", pseudonym=" + pseudonym + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		UserEntity other = (UserEntity) obj;
		if (id != other.id)
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
