package unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import base.ApiGetStep;
import endpoint.IEndpoint;
import util.EnvConfiguration;
import util.EnvKey;

public class MiscTest
{
	
	char i;
	//String aString;
	
	@Test
	@Disabled
	public void default_value_test() {
		System.out.println(i); // Primitive type
		//System.out.println(aString); // Object
	}
	
	@Test
	@Disabled
	public void env_configuration() 
	{
		EnvConfiguration envConfiguration = new EnvConfiguration();
		System.out.println(envConfiguration.getUrl());
	}
	

	@Test
	@Disabled
	public void envKeyTest() 
	{
		EnvKey[] envKeys = EnvKey.values();
		
		for(EnvKey key : envKeys) 
		{
			System.out.println(key.getKey());
		}
		
		System.out.println(EnvKey.BASE_URL.getKey());
	}
	
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
	@Disabled
	public void stringRegexTest() {
		String string = "abcd%?123DftS*";
		System.out.println(parseStringOnly(string));
	}
	
	@Test
	@Disabled
	public void randomTest() 
	{
		int randomNum = new Random(5).nextInt();
		
		System.out.println(Math.abs(randomNum));
	}
	
	
	@Test
	@Disabled
	public void collectionShuffleTest() 
	{
		List<Integer> list = new ArrayList<Integer>(List.of(2, 3, 5, 7, 4, 8, 9, 7));
		
		System.out.println("Original List - " + list);
		
		Collections.shuffle(list);
		
		System.out.println("After Shuffling - " + list);
		
		Collections.sort(list);
		
		System.out.println("Colection Sort - " + list);
		
		Collections.sort(list, Comparator.reverseOrder());
		
		System.out.println("Collection Reverse Sort - " + list);
		
		list.sort(Comparator.naturalOrder());
		
		System.out.println("list sort - " + list);
		
		list.sort(Comparator.reverseOrder());
		
		System.out.println("list reverse - " + list);
	}
	
	@Test
	public void testSetParamMethod() throws Exception {
		ApiGetStep step = new ApiGetStep();
		step.setParams(IEndpoint.GET_PRODUCT_BY_ID, new Object[] {51});
		
		String endString = "{id}";
		System.out.println(endString.replaceAll("[^A-Za-z0-9]", ""));
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
