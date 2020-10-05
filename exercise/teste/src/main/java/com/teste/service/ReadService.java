package com.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teste.dao.ProductDAOService;
import com.teste.operations.ManageRequest;
import com.teste.operations.ReadResponse;
import com.teste.operations.Response;
import com.teste.to.Product;

@Component
public class ReadService extends BaseService
{
	@Autowired
	private ProductDAOService dao;
	
	public Response process(ManageRequest request)
	{
		String checkFieldsResult = null;
		Product product = null;
		
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
		
		System.out.println("Reading product...");
		if (request.getId() != null)
		{
			product = dao.find(request.getId());
		}
		
		else if (!nullOrEmpty(request.getName()))
		{
			product = dao.findByName(request.getName());
		}
		
		if (product != null)
		{
			operationStatus = "Product found!";
		}
		else
		{
			operationStatus = "Product not found!";
		}
		
		return new ReadResponse(operationStatus, product);
	}
	
	private String checkFields(ManageRequest request)
	{
		if (request == null)
		{
			return "Error: The request is null!";
		}
		
		if ((request.getId() == null) && (nullOrEmpty(request.getName())))
		{
			return "Error: The field ID is null!";
		}
		
		else 
		{
			return null;
		}
	}
}
