package com.dogstore.dogstore.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Type {
	// Automatic running Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Essential parameters about type.

	// type, which is category of similar things,validation is in product controller
	private String category;
	@NotBlank(message = "Size is mandatory")
	private String size;

	//

	// As mentioned in manufacturer entity, configure CascadeType
	// to extend all CRUD functions performed to Type entity.
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
	private List<Product> products;

	public Type() {
		super();
	}

	public Type(String category, String size) {
		this.category = category;
		this.size = size;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

}
