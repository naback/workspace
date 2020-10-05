package com.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teste.operations.CreateUpdateRequest;
import com.teste.operations.ReadDeleteRequest;
import com.teste.operations.ReadResponse;
import com.teste.operations.Response;
import com.teste.service.CreateService;
import com.teste.service.DeleteService;
import com.teste.service.ReadService;
import com.teste.service.UpdateService;

@RestController
public class Controller
{
	@Autowired
	CreateService createService;
	
	@Autowired
	ReadService readService;
	
	@Autowired
	UpdateService updateService;
	
	@Autowired
	DeleteService deleteService;
	
	@PostMapping("/create")
	public Response create(@RequestBody CreateUpdateRequest request)
	{
		return createService.process(request);
	}
	
	@PostMapping("/read")
	public Response read(@RequestBody ReadDeleteRequest request)
	{
		return readService.process(request);
	}
	
	@PostMapping("/update")
	public Response update(@RequestBody CreateUpdateRequest request)
	{
		return updateService.process(request);
	}
	
	@PostMapping("/delete")
	public Response delete(@RequestBody ReadDeleteRequest request)
	{
		return deleteService.process(request);
	}
}
