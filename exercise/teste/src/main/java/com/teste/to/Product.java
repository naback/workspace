package com.teste.to;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product implements Serializable
{
	private static final long serialVersionUID = -3229701098032731471L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private Float price;
	private String brand;
	
	public Product()
	{
		super();
		this.id = null;
		this.name = null;
		this.description = null;
		this.price = null;
		this.brand = null;
	}
	
	public Product(Long id, String name, String description, Float price, String brand)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.brand = brand;
	}
	
	public Product(String name, String description, Float price, String brand)
	{
		super();
		this.id = null;
		this.name = name;
		this.description = description;
		this.price = price;
		this.brand = brand;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Float getPrice()
	{
		return price;
	}

	public void setPrice(Float price)
	{
		this.price = price;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	@Override
	public String toString()
	{
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", brand="
				+ brand + "]";
	}
}
