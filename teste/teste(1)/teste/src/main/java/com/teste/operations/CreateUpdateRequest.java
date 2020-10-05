package com.teste.operations;

import java.io.Serializable;

import com.teste.to.Address;

public class CreateUpdateRequest implements Serializable
{
	private static final long serialVersionUID = 4075461262694910731L;

	private final Address address;

	public CreateUpdateRequest()
	{
		super();
		this.address = null;
	}
	
	public CreateUpdateRequest(Address address)
	{
		super();
		this.address = address;
	}

	public Address getAddress()
	{
		return address;
	}

}
