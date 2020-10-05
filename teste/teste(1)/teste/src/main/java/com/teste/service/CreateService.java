package com.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.teste.dao.AddressDAOService;
import com.teste.operations.CreateUpdateRequest;
import com.teste.operations.Response;

@Component
public class CreateService extends BaseService
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
		
		System.out.println("Inserting address...");
		Long insertStatus = dao.insert(request.getAddress());
		
		if (insertStatus == null)
		{
			operationStatus = "Insertion failed! Address received is null!";
		}
		
		System.out.println("Address inserted!");
		
		operationStatus = "Address inserted!";
		
		return new Response(operationStatus);
	}

	private String checkFields(CreateUpdateRequest request)
	{
		if (request.getAddress() == null)
		{
			return "Error: The field Address is null!";
		}
		
		else if (nullOrEmpty(request.getAddress().getStreetName()))
		{
			return "Error: The field StreetName is null!";
		}
		
		else if (request.getAddress().getNumber() == null)
		{
			return "Error: The field Number is null!";
		}
		
		else if (nullOrEmpty(request.getAddress().getNeighbourhood()))
		{
			return "Error: The field Neighbourhood is null!";
		}
		
		else if (nullOrEmpty(request.getAddress().getCity()))
		{
			return "Error: The field City is null!";
		}
		
		else if (nullOrEmpty(request.getAddress().getState()))
		{
			return "Error: The field State is null!";
		}
		
		else if (nullOrEmpty(request.getAddress().getCountry()))
		{
			return "Error: The field Country is null!";
		}
		
		else if (nullOrEmpty(request.getAddress().getZipcode()))
		{
			return "Error: The field ZipCode is null!";
		}
		
		else 
		{
			return null;
		}
	}
}
