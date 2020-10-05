package com.teste.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.teste.to.Address;

@Repository
@Transactional
@Component
public class AddressDAOService
{
	@PersistenceContext
	private EntityManager entityManager;
	
	public Long insert(Address address)
	{
		if (address == null)
		{
			return null;
		}
		
		this.entityManager.persist(address);
		
		return address.getId();
	}
	
	public void delete(Long id)
	{
		if (id == null)
		{
			return;
		}
		
		Address address = find(id);
		
		if (address != null)
		{
			this.entityManager.remove(address);
		}
		else
		{
			System.out.println("Address not found!");
		}
	}
	
	public Address find(Long id)
	{
		if (id == null)
		{
			return null;
		}
		
		return this.entityManager.find(Address.class, id);
	}
	
	public String update(Address address)
	{
		if (address == null)
		{
			return "No data available to update!";
		}
		
		Address databaseAddress = this.entityManager.find(Address.class, address.getId());
		String updateDataError = updateData(address, databaseAddress);
		
		if (updateDataError == null)
		{
			System.out.println("Updated Address: " + new Gson().toJson(databaseAddress));
			entityManager.merge(databaseAddress);
			return null;
		}
		else
		{
			return updateDataError;
		}
	}
	
	private String updateData(Address address, Address databaseAddress)
	{
		boolean dataUpdated = false;
		
		if (!nullOrEmpty(address.getStreetName()))
		{
			databaseAddress.setStreetName(address.getStreetName());
			dataUpdated = true;
		}
		
		if (address.getNumber() != null)
		{
			databaseAddress.setNumber(address.getNumber());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getComplement()))
		{
			databaseAddress.setComplement(address.getComplement());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getNeighbourhood()))
		{
			databaseAddress.setNeighbourhood(address.getNeighbourhood());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getCity()))
		{
			databaseAddress.setCity(address.getCity());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getState()))
		{
			databaseAddress.setState(address.getState());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getCountry()))
		{
			databaseAddress.setCountry(address.getCountry());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getZipcode()))
		{
			databaseAddress.setZipcode(address.getZipcode());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getLatitude()))
		{
			databaseAddress.setLatitude(address.getLatitude());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(address.getLongitude()))
		{
			databaseAddress.setLongitude(address.getLongitude());
			dataUpdated = true;
		}
		
		if (!dataUpdated)
		{
			return "No data available to update!";
		}
		else 
		{
			return null;
		}
	}

	private boolean nullOrEmpty(String value)
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
