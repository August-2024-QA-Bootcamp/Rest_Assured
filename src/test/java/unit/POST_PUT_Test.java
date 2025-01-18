package unit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dto.User;
import groovy.json.JsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_PUT_Test {

	
	/*
	 * 1. Test Data Creation (USER)
	 * 2. Request building
	 * 3. Response object Mapping (USER)
	 * 4. Assertion
	 */
	
	String baseUrl = "https://reqres.in/";
	String basePath = "api/users";
	
	String bodyString = "{\"first_name\":\"john\",\"job\":\"qa\"}";
	
	Map<String, Object> userMap = new HashMap<String, Object>();
	
	
	@Test
	//@Disabled
	public void postTestOneGo_pojo_object_dto() 
	{
		// Test Data creation / preparation
		User user = new User();
		user.setFirstName("Fatema");
		user.setJob("housewife");
		
		// Request building
		Response response = RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(user)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post();
		
		response.then().log().all();
		
		Assertions.assertEquals(201, response.getStatusCode());
		
		User actualUser = response.as(User.class); // Object Mapping
		
		Assertions.assertEquals(user.getFirstName(), actualUser.getFirstName(), "Name did not match");
		Assertions.assertEquals(user.getJob() + "2", actualUser.getJob());
	}
	
	@Test
	@Disabled
	public void postTestOneGo_json_builder() 
	{
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(jsonBuilder())
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo("frost"));
	}
	
	@Test
	@Disabled
	public void postTestOneGo_json_body() 
	{
		File file = new File("src/test/resources/user.json");
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(file)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo("frost"));
	}
	
	@Test
	@Disabled
	public void postTestOneGo_map_body() 
	{
		userMap.put("first_name", "Jean");
		userMap.put("job", "lead");
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(userMap)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo(userMap.get("first_name")));
	}
	
	@Test
	@Disabled
	public void postTestOneGo_str_body() 
	{
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(bodyString)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo("john"));
			
	}
	
	@Test
	@Disabled
	public void putTestOneGo() 
	{
		//String basePath = "api/users/{id}/accounts/{accountId}";
		String basePath = "api/users/{id}";
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(bodyString)
			.pathParam("id", 300)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			.put()
			.then()
			.log().all()
			.statusCode(200)
			.body("first_name", Matchers.equalTo("john"));
	}
	
	
	@Test
	@Disabled
	public void patchTest() {
		String basePath = "api/users/{id}";
		String bodyString = "{\"job\":\"qa\"}";
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(bodyString)
			.pathParam("id", 300)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			.patch()
			.then()
			.log().all()
			.statusCode(200)
			.body("job", Matchers.equalTo("qa"));
	}
	
	
	private String jsonBuilder() {
		JSONObject json = new JSONObject();
		json.put("first_name", "eva");
		json.put("job", "supervisor");
		
		return json.toString();
	}
	
}
