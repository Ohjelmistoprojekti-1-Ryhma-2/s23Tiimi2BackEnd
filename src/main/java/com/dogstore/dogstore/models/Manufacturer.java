package com.dogstore.dogstore.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//This entity is for informative list of many manufacturers.
//Manufacturers' company information is for ordering products
//directly from manufacturers to dogstore's storage, 
//now and in future.

@Entity
public class Manufacturer {
	// Automatic running Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Essential informative parameters about manufactures
	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@NotBlank(message = "Address is mandatory")
	private String address;

	@NotBlank(message = "Continent is mandatory")
	private String continent;

	@NotBlank(message = "Phone is mandatory")
	private String phone;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email address")
	private String email;

	// Configured CascadeType to extend ALL CRUD functions performed to Manufacturer
	// entity to apply to all associated products as well (could use .REMOVE in case
	// of just deleting a manufacturer and all its associated Products)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
	private List<Product> products;

	public Manufacturer() {
		super();
	}

	public Manufacturer(String name, String address, String continent, String phone, String email) {
		this.name = name;
		this.address = address;
		this.continent = continent;
		this.phone = phone;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContinent() {
		return continent;

	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
