package net.cafe.data.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.Column;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name="user")
public class User {

	public static final String USER = "USER_TOKEN";
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private String status;
	private Date expiryDate;
	@Column(unique=true)
	private String loginId;
	@ManyToOne
	private Profile profile;
	@Version
    protected int version;
	@JSON(name="DT_RowId")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public void setId(String id) {
		if(NumberUtils.isNumber(id)){
			setId(NumberUtils.createLong(id));
		}
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
}
