package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Common 
{
	private int id;
	private String creationAt;
	private String updatedAt;
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getCreationAt() 
	{
		return creationAt;
	}
	
	public void setCreationAt(String creationAt) 
	{
		this.creationAt = creationAt;
	}
	
	public String getUpdatedAt() 
	{
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) 
	{
		this.updatedAt = updatedAt;
	}
}
