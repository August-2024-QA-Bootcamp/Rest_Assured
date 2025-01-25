package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends Common
{
	private String name;
	private String image;
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getImage() 
	{
		return image;
	}
	
	public void setImage(String image) 
	{
		this.image = image;
	}
}
