package com.ebsl.data.model;

import java.util.List;

import javax.persistence.*;

import org.apache.struts2.json.annotations.JSON;

@Entity
public class Profile {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Profile other = (Profile) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long id;
	private String name;
	private String email;
	@ManyToOne
	private Role role;
	@Version
    protected int version;
	@ManyToMany
	@JoinTable(
		      name="profile_options",
		      joinColumns={@JoinColumn(name="profile_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="option_id", referencedColumnName="id")})
	private List<Option> options;
	
	@JSON(name="DT_RowId")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	
}
