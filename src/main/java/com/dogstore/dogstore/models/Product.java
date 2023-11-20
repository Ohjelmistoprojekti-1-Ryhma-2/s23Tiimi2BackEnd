package com.dogstore.dogstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
	// Automatic running Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Other parameters

	// Note that values for type are three: clothes, food or toy.
	// If clothes, then there are also three additional types: S, M or L.

	// Values are written in Finnish.

	@NotBlank(message = "Name is mandatory!")
	private String name;
	private String type;
	@NotBlank(message = "Color is mandatory!")
	private String color;
	private String size;
	@NotNull(message = "Price is mandatory!")
	private double price;

	@ManyToOne(fetch = FetchType.LAZY) // According to Juha Hinkula's book "Hands-On Full Stack Development with Spring
										// Boot 2 and React - Second Edition", Chapter 'Relationships between tables'
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	public Product() {
		this.type = "";
	}

	public Product(String name, String type, String color, String size, double price, Manufacturer manufacturer) {
		this.name = name;
		this.type = type;
		this.color = color;
		this.size = size;
		this.price = price;
		this.manufacturer = manufacturer;
	}

	/**
	 * @return Long return the id
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the product_type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return String return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return String, return the size
	 */

	public String getSize() {
		return size;
	}

	/**
	 * @param size, return the size
	 */

	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return double return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

}
