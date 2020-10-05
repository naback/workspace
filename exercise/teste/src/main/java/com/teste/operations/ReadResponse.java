package com.teste.operations;

import com.teste.to.Product;

public class ReadResponse extends Response
{
	private static final long serialVersionUID = -385477399908927319L;
	
	private final Product product;

	public ReadResponse()
	{
		super();
		product = null;
	}
	
	public ReadResponse(String operationStatus, Product product)
	{
		super(operationStatus);
		this.product = product;
	}

	public Product getProduct()
	{
		return product;
	}
}
