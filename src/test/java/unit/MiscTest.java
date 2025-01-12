package unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class MiscTest 
{

	@Test
	@Disabled
	public void stringSplitTest() {
		String endpoint = "api/users/{id}/accounts/{accountId}";
		String[] arr = endpoint.split("/");
		
		List<String> params = new ArrayList<String>();
		
		for(String s : arr) {

			if(s.startsWith("{") && s.endsWith("}")) 
			{
				params.add(s.replaceAll("[^A-Za-z0-9]", ""));
				System.out.println(s.replaceAll("[^A-Za-z0-9]", ""));
			}
		}
	}
	
	
	@Test
	public void stringRegexTest() {
		String string = "abcd%?123DftS*";
		System.out.println(parseStringOnly(string));
	}
	
	
	/**
	 * This parseStringOnly method will parse string char from mixed string containing numbers and whitespace
	 * 
	 * @param input String with any char
	 * @return only string char
	 */
	public static String parseStringOnly(String input) 
	{
		return input.toLowerCase().replaceAll("[^a-z]", "");
	}
}
