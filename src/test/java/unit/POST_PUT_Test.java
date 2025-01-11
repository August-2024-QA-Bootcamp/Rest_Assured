package unit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_PUT_Test {

	String baseUrl = "https://reqres.in/";
	String basePath = "api/users";
	
	String bodyString = "{\"first_name\":\"john\",\"job\":\"qa\"}";
	
	@Test
	@Disabled
	public void postTestOneGo() 
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
	
}
