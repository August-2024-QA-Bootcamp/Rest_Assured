package product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import base.ApiGetStep;
import base.ApiPostStep;
import dto.Category;
import dto.Product;
import endpoint.IEndpoint;
import io.restassured.http.ContentType;

// JUnit5 Style
@Tag("Regression")
@Tag("Smoke")
@Tag("Product")
public class PostProductTest {

	/*
	 * Get All Categories
	 * Response Map
	 * List <Category> categories
	 * Collections.shuffle(categories)
	 * Random Category ID
	 * 
	 * Product product = new Product();
	 * preoduct.setTitle("Denim Jeans")
	 */
	
	static int categoryId;
	
	@BeforeAll
	public static void testDataSetup() throws Exception 
	{
		ApiGetStep getCategories = new ApiGetStep();
		
		//Get Categories request
		getCategories.get(null, IEndpoint.GET_CATEGORIES, null, 200, null);
		
		// Object mapping
		Category[] categories = getCategories.getResponse().as(Category[].class);
		
		for (Category category : categories) 
		{
			if(category.getName().equalsIgnoreCase("Clothes")) 
			{
				categoryId = category.getId();
			}
		}
	}
	
	@Test
	@Tag("Smoke")
	public void createClothingProductTest() throws Exception 
	{
		Product product = new Product();
		product.setTitle("Denim Jeans");
		product.setPrice(35);
		product.setDescription("Mens We Are Denim · Straight Six Men's Jeans in Authentic and Deep Indigo - BM20457 · Extra 20% off with code EXTRA20 · Relaxed Straight Driven Men's Jeans");
		
		product.setCategoryId(categoryId);
		product.setImages(new String[] {"https://www.gerberchildrenswear.com/cdn/shop/files/Gerber_1-pack-baby-neutral-blue-straight-fit-jeans-evyr-d_image_1.jpg?v=1721762942"});
		
		ApiPostStep postStep = new ApiPostStep();
		postStep.setContentType(ContentType.JSON);
		postStep.post(null, IEndpoint.GET_POST_PRODUCTS, product, null, 201, null);
		
		// Response
		Product denimProduct = postStep.getResponse().as(Product.class);
		
		ApiGetStep getAllProductsStep = new ApiGetStep();
		getAllProductsStep.get(null, IEndpoint.GET_POST_PRODUCTS, null, 200, null);
		
		Product[] products = getAllProductsStep.getResponse().as(Product[].class);
		
		boolean isFound = false;
		
		for(Product expectedProduct : products) 
		{
			if(expectedProduct.getId() == denimProduct.getId()) 
			{
				isFound = true;
			}
		}
		
		Assertions.assertTrue(isFound);
	}
	
}
