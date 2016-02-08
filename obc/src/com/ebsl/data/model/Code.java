package com.ebsl.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(
	    name="code", 
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"code", "type"})
	)
public class Code implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Field(index=Index.YES, store=Store.NO)
	private String code;
	 @Field(index=Index.YES, store=Store.NO)
	private String description;
	 @Field(index=Index.YES, store=Store.NO)
	private String type;
	private String modification;
	@JSON(name="DT_RowId")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Code other = (Code) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Code [id=" + id + ", code=" + code + ", description="
				+ description + ", type=" + type + "]";
	}

}



