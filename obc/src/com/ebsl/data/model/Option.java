package com.ebsl.data.model;

import javax.persistence.*;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.apache.struts2.json.annotations.JSON;

@Entity
public class Option {
	
	@Id 
	@GeneratedValue
	protected long id;
	private String name;
	private String description;
	@ManyToMany(mappedBy="options")
	private List<Profile>  profiles;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Option [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}

	
}
