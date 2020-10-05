package com.teste.service;

import com.teste.operations.ManageRequest;
import com.teste.to.Product;

public class BaseService
{
	public boolean nullOrEmpty(String value)
	{
		if ((value == null) || (value.isEmpty()))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	protected Product mountDatabaseProduct(ManageRequest request)
	{
		Long id = null;

		String name = request.getName();
		String description = request.getDescription();
		Float price = request.getPrice();
		String brand = request.getBrand();
		
		if (request.getId() != null)
		{
			id = request.getId();
			return new Product(id, name, description, price, brand);
		}
		
		else 
		{
			return new Product(name, description, price, brand);
		}
	}

}
