package com.teste.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.teste.to.Product;

@Repository
@Transactional
@Component
public class ProductDAOService
{
	@PersistenceContext
	private EntityManager entityManager;
	
	public Long insert(Product product)
	{
		if (product == null)
		{
			return null;
		}
		
		this.entityManager.persist(product);
		
		return product.getId();
	}
	
	public void delete(Long id)
	{
		if (id == null)
		{
			return;
		}
		
		Product product = find(id);
		
		if (product != null)
		{
			this.entityManager.remove(product);
		}
		else
		{
			System.out.println("Product not found!");
		}
	}
	
	public Product find(Long id)
	{
		if (id == null)
		{
			return null;
		}
		
		System.out.println("Method find - Loading product by id: " + id);
		return this.entityManager.find(Product.class, id);
	}
	
	public Product findByName(String name)
	{
		if (nullOrEmpty(name))
		{
			return null;
		}
		
		Product product = null;
		long i = 1;
		
		//Can't search by name in a H2 Memory Database because I don't know the name of the table it created to write a query, so I had to improvise.
		while(entityManager.find(Product.class, i) != null)
		{
			product = entityManager.find(Product.class, i);
			
			if (product.getName().contentEquals(name))
			{
				return product;
			}
			i++;
		}
		
		return null;
	}
	
	public String update(Product product)
	{
		if (product == null)
		{
			return "No data available to update!";
		}
		
		Product databaseProduct = this.entityManager.find(Product.class, product.getId());
		String updateDataError = updateData(product, databaseProduct);
		
		if (updateDataError == null)
		{
			System.out.println("Updated Product: " + new Gson().toJson(databaseProduct));
			entityManager.merge(databaseProduct);
			return null;
		}
		else
		{
			return updateDataError;
		}
	}
	
	private String updateData(Product product, Product databaseProduct)
	{
		boolean dataUpdated = false;
		
		if (!nullOrEmpty(product.getName()))
		{
			databaseProduct.setName(product.getName());
			dataUpdated = true;
		}
		
		if (product.getDescription() != null)
		{
			databaseProduct.setDescription(product.getDescription());
			dataUpdated = true;
		}
		
		if (product.getPrice() != null)
		{
			databaseProduct.setPrice(product.getPrice());
			dataUpdated = true;
		}
		
		if (!nullOrEmpty(product.getBrand()))
		{
			databaseProduct.setBrand(product.getBrand());
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
