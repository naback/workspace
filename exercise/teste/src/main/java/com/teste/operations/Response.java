package com.teste.operations;

import java.io.Serializable;

public class Response implements Serializable
{
	private static final long serialVersionUID = -1998283284176211557L;

	private final String operationStatus;

	public Response()
	{
		operationStatus = null;
	}
	
	public Response(String operationStatus)
	{
		super();
		this.operationStatus = operationStatus;
	}

	public String getOperationStatus()
	{
		return operationStatus;
	}

}
