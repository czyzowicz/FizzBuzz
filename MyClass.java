package sda.java9.wpj.advanced.proc;

import java.io.*;
import java.nio.file.*;

public class MyClass
{
	public static void main (String [] args)
	{
		String userHome = System.getProperty ("user.home");
		Path userPath = Paths.get(userHome);
		Path myFilePath = userPath.resolve("dane.txt");
		System.out.println(myFilePath);
		
		File myFile = myFilePath.toFile();
		try (FileInputStream fis = new FileInputStream (myFile); BufferedInputStream bis = new BufferedInputStream (fis)) 
		
		{
			int byt;
			while ((byt = fis.read()) != -1)
			{
				System.out.print((char)byt);
			}
		}
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}