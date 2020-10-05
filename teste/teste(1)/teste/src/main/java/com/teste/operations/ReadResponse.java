package com.teste.operations;

import com.teste.to.Address;

public class ReadResponse extends Response
{
	private static final long serialVersionUID = -385477399908927319L;
	
	private final Address address;

	public ReadResponse()
	{
		super();
		address = null;
	}
	
	public ReadResponse(String operationStatus, Address address)
	{
		super(operationStatus);
		this.address = address;
	}

	public Address getAddress()
	{
		return address;
	}
}
