package base;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ApiPostStep extends RestStep 
{
	
	public ApiPostStep() 
	{
		init();
	}
	
	/**
	 * Build a GET API Request
	 * @param headers {@link Headers}
	 * @param endpoint API End-point
	 * @param body Pay-load
	 * @param params Path Params
	 * @param stausCode expected status code
	 * @param statusMessage expected status message
	 * @return {@link #response}
	 * @throws Exception if path params do not have same size at end-point
	 */
	public Response post(Headers headers, String endpoint, Object body, Object[] params, 
			int stausCode, String statusMessage) throws Exception 
	{	
		setHeaders(headers);
		setEndpoint(endpoint);
		setParams(endpoint, params);
		
		response = request.log().all().get();
		response.then().log().all();
		
		validateStatusCode(stausCode);
		validateStatusLine(statusMessage);
		
		return response;
	}
}
