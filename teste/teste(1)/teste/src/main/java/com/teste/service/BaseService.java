package com.teste.service;

public class BaseService
{
	public boolean nullOrEmpty(String value)
	{
		if ((value == null) || (value.isEmpty()))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
}
