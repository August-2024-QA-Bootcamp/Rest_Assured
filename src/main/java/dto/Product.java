package dto;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicNode;

import com.fasterxml.jackson.annotation.JsonInclude;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends Common
{
	public Product(String title, int price, String description, String[] images, Category category) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.images = images;
		this.category = category;
	}

	public Product() {
	}
	
	private String title;
	private int price;
	private String description;
	private String[] images;
	private Category category;
	private int categoryId;
	
	
	@Override
	public boolean equals(Object object) {
		
		if (object instanceof Product) 
		{
			Product product = (Product) object;
			
			if(this.getId() == product.getId()) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	// JUnit5 Style
	public List<DynamicNode> compare(Product product)
	{
		List<DynamicNode> tests = new LinkedList<DynamicNode>();
		
		tests.add(dynamicTest("ID - ["+this.getId()+"]", () -> 
			assertEquals(this.getId(), product.getId(), "ID Mismatched")));
		
		tests.add(dynamicTest("Title - ["+title+"]", () -> 
			assertEquals(title, product.getTitle(), "Title Mismatched")));
		
		tests.add(dynamicTest("Description - ["+description+"]", () -> 
			assertEquals(description, product.getDescription(), "Description Mismatched")));
		
		List<String> actualImages = Arrays.asList(getImages());
		List<String> expectedImages = Arrays.asList(product.getImages());
		
		tests.add(dynamicTest("Images - ["+getImages()+"]", () -> 
			Assertions.assertIterableEquals(expectedImages, actualImages)));
		
		tests.add(dynamicTest("Category - ["+category+"]", () -> 
			assertEquals(category, product.getCategory(), "Category Mismatched")));
		
		tests.add(dynamicTest("CreationAt - ["+this.getCreationAt()+"]", () -> 
			assertEquals(this.getCreationAt(), product.getCreationAt(), "ID Mismatched")));
	
		tests.add(dynamicTest("UpdatedAt - ["+this.getUpdatedAt()+"]", () -> 
			assertEquals(this.getUpdatedAt(), product.getUpdatedAt(), "Title Mismatched")));
		
		// TestNG
		//Assert.assertEquals(this.getId(), product.getId());
		
		return tests;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String[] getImages() {
		return images;
	}
	
	public void setImages(String[] images) {
		this.images = images;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
