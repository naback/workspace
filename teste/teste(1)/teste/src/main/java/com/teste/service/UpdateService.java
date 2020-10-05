package com.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teste.dao.AddressDAOService;
import com.teste.operations.CreateUpdateRequest;
import com.teste.operations.Response;

@Component
public class UpdateService extends BaseService
{
	@Autowired
	private AddressDAOService dao;
	
	public Response process(CreateUpdateRequest request)
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
		
		System.out.println("Updating address...");
		String updateDataError = dao.update(request.getAddress());
		
		if (updateDataError != null)
		{
			return new Response(updateDataError);
		}
		else
		{
			operationStatus = "Address updated!";
			return new Response(operationStatus);
		}
	}

	private String checkFields(CreateUpdateRequest request)
	{
		if (request.getAddress() == null)
		{
			return "Error: The field Address is null!";
		}
		
		if (request.getAddress().getId() == null)
		{
			return "Error: The field ID is null!";
		}
		
		else 
		{
			return null;
		}
	}
}
