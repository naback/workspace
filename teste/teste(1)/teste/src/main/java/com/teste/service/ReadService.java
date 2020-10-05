package com.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teste.dao.AddressDAOService;
import com.teste.operations.ReadDeleteRequest;
import com.teste.operations.ReadResponse;
import com.teste.operations.Response;
import com.teste.to.Address;

@Component
public class ReadService extends BaseService
{
	@Autowired
	private AddressDAOService dao;
	
	public Response process(ReadDeleteRequest request)
	{
		String checkFieldsResult = null;
		Address address = null;
		
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
		
		System.out.println("Reading address...");
		address = dao.find(request.getId());
		
		if (address != null)
		{
			operationStatus = "Address found!";
		}
		else
		{
			operationStatus = "Address not found!";
		}
		
		return new ReadResponse(operationStatus, address);
	}
	
	private String checkFields(ReadDeleteRequest request)
	{
		if (request == null)
		{
			return "Error: The request is null!";
		}
		
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
