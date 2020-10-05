package com.teste.operations;

import java.io.Serializable;

public class ManageRequest implements Serializable
{
	private static final long serialVersionUID = -512277633359732437L;

	private final Long id;
	private final String name;
	private final String description;
	private final Float price;
	private final String brand;
	private final String operation;

	public ManageRequest()
	{
		super();
		this.id = null;
		this.name = null;
		this.description = null;
		this.price = null;
		this.brand = null;
		this.operation = null;
	}

	public ManageRequest(String name, String description, Float price, String brand, String operation)
	{
		super();
		this.id = null;
		this.name = name;
		this.description = description;
		this.price = price;
		this.brand = brand;
		this.operation = operation;
	}

	public ManageRequest(Long id, String operation)
	{
		super();
		this.id = id;
		this.operation = operation;
		this.name = null;
		this.description = null;
		this.price = null;
		this.brand = null;
	}

	public ManageRequest(String name, String operation)
	{
		super();
		this.name = name;
		this.operation = operation;
		this.id = null;
		this.description = null;
		this.price = null;
		this.brand = null;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Float getPrice()
	{
		return price;
	}

	public String getBrand()
	{
		return brand;
	}

	public String getOperation()
	{
		return operation;
	}

}
