package com.teste.operations;

import java.io.Serializable;

public class ReadDeleteRequest implements Serializable
{
	private static final long serialVersionUID = -249580075004329114L;

	private final Long id;

	public ReadDeleteRequest()
	{
		super();
		this.id = null;
	}
	
	public ReadDeleteRequest(long id)
	{
		super();
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

}
