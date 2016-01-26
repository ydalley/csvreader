package com.ebsl.data.model;

import java.util.List;

import javax.persistence.*;

import org.apache.struts2.json.annotations.JSON;

@Entity
public class Profile {

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
		      name="PROFILE_OPTIONS",
		      joinColumns={@JoinColumn(name="PROFILE_ID", referencedColumnName="ID")},
		      inverseJoinColumns={@JoinColumn(name="OPTION_ID", referencedColumnName="ID")})
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
