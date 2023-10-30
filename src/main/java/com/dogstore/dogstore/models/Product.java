package com.dogstore.dogstore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	// Automatic running Id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Other parameters
	private String product_type;
	private String color;
	private String size;
	private double price;

	@ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

	public Product() {

	}

	public Product(String product_type, String color, String size, double price, Manufacturer manufacturer) {
		this.product_type = product_type;
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
	 * @return String return the product_type
	 */
	public String getProduct_type() {
		return product_type;
	}

	/**
	 * @param product_type the product_type to set
	 */
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
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
