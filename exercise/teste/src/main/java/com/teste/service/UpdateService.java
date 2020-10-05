package com.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teste.dao.ProductDAOService;
import com.teste.operations.ManageRequest;
import com.teste.operations.Response;
import com.teste.to.Product;

@Component
public class UpdateService extends BaseService
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
		
		System.out.println("Updating product...");
		Product product = mountDatabaseProduct(request);
		String updateDataError = dao.update(product);
		
		if (updateDataError != null)
		{
			return new Response(updateDataError);
		}
		else
		{
			operationStatus = "Product updated!";
			return new Response(operationStatus);
		}
	}

	private String checkFields(ManageRequest request)
	{
		if (request.getId() == null)
		{
			return "Error: The field ID is null!";
		}
		
		else 
		{
			return null;
		}
	}
}
