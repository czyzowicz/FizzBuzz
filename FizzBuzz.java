package sda.java9.wpj.advanced.proc;

public class FizzBuzz
{
	public static void main (String [] args)
	{
		int [] array; 
		array = new int [101];
	    for (int i = 1; i < array.length; i++) 
		{
            if ( i % 15 == 0)
			{
				System.out.println("FizzBuzz");
			}
			else if (i % 3 == 0)
			{
				System.out.println("Fizz");
			}
			else if (i % 5 == 0)
			{
				System.out.println("Buzz");
			}
			else
			{
				System.out.println(i);
			}
		}
	}
}

