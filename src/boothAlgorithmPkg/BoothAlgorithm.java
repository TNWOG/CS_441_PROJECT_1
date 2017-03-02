
package boothAlgorithmPkg;

import java.math.*;
import java.util.Random;

public class BoothAlgorithm {
	
	
	public static int binToInt(String bin)
	{
		int sum = 0;
		int sign = 1;
		int count = bin.length()-1;
		if(Character.getNumericValue(bin.charAt(0)) == 1)
		{
			bin = negateBin(bin);
			sign = -1;
		}
		for(int i = 0; i < bin.length(); i++)
		{
			if(Character.getNumericValue(bin.charAt(i)) == 1)
			{
				sum += Math.pow(2, count);
			}
			count--;
		}
		
		return sum*sign;
		
	}
	
	public static String multiply(int a, int b)
	{
		//converts a and b into binary numbers
		String product = createSixteenBit(a);
		String mcand = createEightBit(b);
		
		//post conversion
		System.out.println("Product: " + product + " Multiplicand: " + mcand + "\n");
		
		//initializes product by adding zero
		product = iniProduct(product);
		
		//begins the eight iterations
		for(int i = 0; i < 8; i++)
		{
			//**use this stuff for tabular formatting**
			System.out.println("Iteration: " + (i+1));
			//performs the addition or subtraction
			product = initialOperation(product, mcand);
			System.out.println("           Post mcand operation: " + product);
			//shifts product
			product = aShiftRight(product);
			System.out.println("           Post shift: " + product);
		}
		product = product.substring(0, product.length()-1);
		System.out.println("\n" + "Product in Binary: " + product);
		System.out.println("Product in Decimal: " + binToInt(product));
		return product;
	}
	
	private static String initialOperation(String product, String mcand)
	{
		String operationBit = product.substring(product.length()-2);
		
		if(operationBit.equals("00")||operationBit.equals("11"))
		{
			return product;
		}
		if(operationBit.equals("01"))
		{
			String firstHalf = product.substring(0,product.length()/2);
			firstHalf = addBinary(firstHalf, mcand);
			if(firstHalf.length() > product.length()/2)
			{
				firstHalf = firstHalf.substring(1);
			}
			return firstHalf + product.substring(8);
		}
		if(operationBit.equals("10"))
		{
			String firstHalf = product.substring(0,product.length()/2);
			firstHalf = addBinary(firstHalf, negateBin(mcand));
			if(firstHalf.length() > product.length()/2)
			{
				firstHalf = firstHalf.substring(1);
			}
			return firstHalf + product.substring(8);
		}
		return "FAIL";
	}
	
	private static String aShiftRight(String product)
	{
		String nextDigit = "0";
		if(Character.getNumericValue(product.charAt(0)) == 1)
		{
			nextDigit = "1";
		}
		
		return nextDigit + product.substring(0, product.length()-1);
	}
	
	private static String addBinary(String a, String b)
	{
		String binarySum = "";
		int curSum;
		int carryOn = 0;
		int count = 0;
		if(a.length() != b.length()){
			if(a.length()>8)
			{
				a = a.substring(a.length()-8);
			}
			
			if(b.length()>8)
			{
				b = b.substring(b.length()-8);
			}
		}
		if(a.length()>b.length())
		{
			count = a.length()-1;
		}
		else{
			count = b.length()-1;
		}
		
		for(int i = 0; i < b.length(); i++)
		{
			int aInt = Character.getNumericValue(a.charAt(count));
			int bInt = Character.getNumericValue(b.charAt(count));
			curSum = aInt + bInt + carryOn;
			if(curSum == 1)
			{
				carryOn = 0;
			}
			else if(curSum == 2)
			{
				carryOn = 1;
				curSum = 0;
			}
			else if(curSum == 3)
			{
				carryOn = 1;
				curSum = 1;
			}
			else
			{
				carryOn = 0;
			}
			binarySum = Integer.toString(curSum) + binarySum;
			count--;
		}
		if(carryOn == 1)
		{
			binarySum = Integer.toString(carryOn) + binarySum;
		}
		return binarySum;
		
	}
	
	private static String negateBin(String bin)
	{
		String negation = "";
		for(int i = 0; i < bin.length(); i++)
		{
			if(Character.getNumericValue(bin.charAt(i))==1)
			{
				negation += "0";
			}
			else if(Character.getNumericValue(bin.charAt(i))==0)
			{
				negation += "1";
			}	
		}
		String addOne = "";
		for(int i = 0; i < bin.length()-1; i++)
		{
			addOne += 0;
		}
		addOne += 1;
		negation = addBinary(negation, addOne);
		return negation;
		
	}
	
	private static String iniProduct(String product)
	{
		return product + "0";
	}
	
	private static String createEightBit(int a)
	{
		
		String product = Integer.toBinaryString(a);
		
		if(product.length()<8)
		{
			int count = 8-product.length();
			int sign;
			if(a>=0)
			{
				sign = 0;
			}
			else
			{
				sign = 1;
			}
			for(int i = 0; i<count; i++)
			{
				product = sign + product;
			}
		}
		if(product.length()>8)
		{
			product = product.substring(product.length()-8, product.length());
		}
		return product;
	}
	
	private static String createSixteenBit(int a)
	{
		String product = createEightBit(a);
		
		product = "00000000" + product;
		
		return product;
	}

}
