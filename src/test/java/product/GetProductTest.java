package product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import base.ApiGetStep;
import dto.Product;
import endpoint.IEndpoint;

public class GetProductTest
{
	
	/*
	 * 1. Happy Path / Positive Scenario -  Priority 1 : 200
	 * 2. Negative - Entity not available : 400 / 404 not found
	 * 3. Negative - param missing
	 * 4. param null
	 * 5. param empty
	 * 6. no auth
	 * 7. missing auth
	 * 8. Invalid resources/endpoint
	 */
	
	/*
	 * Test Data - DONE
	 * Request build - DONE
	 * response parse / mapping
	 * assertion
	 */
	
	static int productId;
	static Product expectedProduct;
	
	@BeforeAll
	public static void testSetup() throws Exception 
	{
		/*
		 * GetProducts API request
		 * response mapping > list / array of product
		 * list of ids
		 * randomize the ids
		 * random id = productId
		 */
		
		ApiGetStep getProductsStep = new ApiGetStep();
		
		//Get Products request
		getProductsStep.get(null, IEndpoint.GET_POST_PRODUCTS, null, 200, null);
		
		// Object mapping
		Product[] products = getProductsStep.getResponse().as(Product[].class);
		
		// Java Stream API
//		List<Integer> ids = Arrays.asList(products).stream().map(e-> e.getId()).collect(Collectors.toList());
		
		List<Integer> ids = new ArrayList<Integer>();
		
		for (Product product : products) 
		{
			ids.add(product.getId());
		}
		
		// Randomize
		Collections.shuffle(ids);
		
		// Assign id
		productId = ids.get(0);
		
		// Java Stream API
//		expectedProduct = Arrays.asList(products).stream().filter(e-> e.getId() == productId).findFirst().orElse(null);
		
		for(Product product:products) 
		{
			if(product.getId() == productId) 
			{
				expectedProduct = product;
			}
		}
	}
	
	
	/*
	 * TestNG vs JUnit4/5
	 * - XML Test Suite - TestRunner.class
	 * - @BeforeTest/@BeforeSuite - N/A
	 * - @BeforeMethod - @BeforeEach
	 * - @BeforeClass - @BeforeAll
	 * 
	 * 
	 */
	
	/*
	 * @TestFactory - Dynamic Testing
	 * DynamicNode - test / test suite (test container)
	 * DynamiceTest - Test
	 * DynamiceContainer - Test Container / List of DynamicTest
	 */
	
	@TestFactory
	@Order(1)
	@DisplayName("Get Product By ID - Happy Path")
	public List<DynamicNode> getProductHappyPath() throws Exception 
	{
		List<DynamicNode> tests = new LinkedList<DynamicNode>();
		
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT_BY_ID, new Object[] {productId}, 200, null);
		
		Product product = getProductStep.getResponse().as(Product.class);
		
		// JUnit5 Dynamic Testing
		tests.add(DynamicTest.dynamicTest("ID Should Match", ()-> // Lambda
			Assertions.assertEquals(productId, product.getId())));
		
		tests.add(DynamicTest.dynamicTest("Price Should Match", ()->
			Assertions.assertEquals(expectedProduct.getPrice(), product.getPrice())));
		
		tests.add(DynamicTest.dynamicTest("Both Product are same", ()-> 
			Assertions.assertTrue(product.equals(expectedProduct))));
		
		// Dynamic Container
		tests.add(DynamicContainer.dynamicContainer("Comapring", product.compare(expectedProduct)));
		
		
		// TestNG Style > Soft Assert > softAssert.assertAll()
		
//		Assert.assertEquals(productId, product.getId());
//		Assert.assertEquals(expectedProduct.getPrice(), product.getPrice());
//		Assert.assertEquals(expectedProduct.getTitle(), product.getTitle());
//		Assert.assertEquals(expectedProduct.getDescription(), product.getDescription());
//		Assert.assertTrue(product.equals(expectedProduct));
		
		
		System.out.println(product.hashCode());
		System.out.println(expectedProduct.hashCode());
		
		return tests;
	}
	
	@Test
	@Order(2)
	@DisplayName("Get Product By ID - Entity not Found")
	public void validateEntityNotFound() throws Exception 
	{
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT_BY_ID, new Object[] {0}, 400, "HTTP/1.1 400 Bad Request");
	}
	
	@Test
	@Order(3)
	@DisplayName("Get Product By ID - Param Missing")
	public void validateParamMissing() throws Exception 
	{
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT_BY_ID, new Object[] {""}, 400, "HTTP/1.1 400 Bad Request");
	}
	
	@Test
	@DisplayName("Get Product By ID - Param Null")
	public void validateParamNull() throws Exception 
	{
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT_BY_ID, new Object[] {"null"}, 400, "HTTP/1.1 400 Bad Request");
	}
	
	@Test
	@DisplayName("Get Product By ID - No Auth")
	public void validateNoAuth() throws Exception 
	{
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT_BY_ID, new Object[] {productId}, 401, "HTTP/1.1 401 Unauthorized");
	}
	
	@Test
	@DisplayName("Get Product By ID - Invalid Endpoint")
	public void validateInvalidEndpoint() throws Exception 
	{
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT_BY_ID + "/_Invalid", new Object[] {productId}, 404, "HTTP/1.1 404 Not Found");
	}
}
