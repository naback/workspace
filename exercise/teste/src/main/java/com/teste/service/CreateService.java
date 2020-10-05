package com.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teste.dao.ProductDAOService;
import com.teste.operations.ManageRequest;
import com.teste.operations.Response;
import com.teste.to.Product;

@Component
public class CreateService extends BaseService
{
	@Autowired
	private ProductDAOService dao;
	
	public Response process(ManageRequest request)
	{
		String checkFieldsResult = null;
		
		String operationStatus = "Error during operation";
		
		System.out.println("Checking requests...");
		checkFieldsResult = checkFields(request);
		System.out.println("Requests checked!");
		
		if (checkFieldsResult != null)
		{
			return new Response(checkFieldsResult);
		}
		System.out.println("Requests Fine!");
		
		Gson gson = new Gson();
		System.out.println("Request received: " + gson.toJson(request));
		
		System.out.println("Inserting product...");
		 
		Product productToInsert = mountDatabaseProduct(request);
		Long insertStatus = dao.insert(productToInsert);
		
		if (insertStatus == null)
		{
			operationStatus = "Insertion failed! Product data received is null!";
		}
		
		System.out.println("Product inserted!");
		
		operationStatus = "Product inserted!";
		
		return new Response(operationStatus);
	}

	private String checkFields(ManageRequest request)
	{
		if (nullOrEmpty(request.getName()))
		{
			return "Error: The field Name is null!";
		}
		
		else if (nullOrEmpty(request.getDescription()))
		{
			return "Error: The field Description is null!";
		}
		
		else if (request.getPrice() == null)
		{
			return "Error: The field Price is null!";
		}
		
		else if (nullOrEmpty(request.getBrand()))
		{
			return "Error: The field Brand is null!";
		}
		
		else 
		{
			return null;
		}
	}
}
