package com.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teste.operations.ManageRequest;
import com.teste.operations.Response;
import com.teste.service.CreateService;
import com.teste.service.DeleteService;
import com.teste.service.ReadService;
import com.teste.service.UpdateService;

@RestController
public class Controller
{
	private final String CREATE = "CREATE";
	private final String UPDATE = "UPDATE";
	private final String SEARCH = "SEARCH";
	private final String DELETE = "DELETE";
	
	@Autowired
	CreateService createService;
	
	@Autowired
	ReadService readService;
	
	@Autowired
	UpdateService updateService;
	
	@Autowired
	DeleteService deleteService;
	
	@PostMapping("/manageproducts")
	public Response create(@RequestBody ManageRequest request)
	{
		if ((request.getOperation() == null) || (request.getOperation().isEmpty()))
		{
			return new Response("Field 'operation' can't be null.");
		}
		
		if (request.getOperation().contentEquals(CREATE))
		{
			return createService.process(request);
		}
		
		else if (request.getOperation().contentEquals(UPDATE))
		{
			return updateService.process(request);
		}
		
		else if (request.getOperation().contentEquals(SEARCH))
		{
			return readService.process(request);
		}
		
		else if (request.getOperation().contentEquals(DELETE))
		{
			return deleteService.process(request);
		}
		
		else 
		{
			return new Response("Invalid value in field 'operation'.");
		}
	}
}
